package ma.app.appcustomerfrontthymeleaf.web;

import ma.app.appcustomerfrontthymeleaf.repository.CustomerRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CustomerController {
    private final ClientRegistrationRepository clientRegistrationRepository;
    private CustomerRepository customerRepository;
    public CustomerController(CustomerRepository customerRepository, ClientRegistrationRepository clientRegistrationRepository) {
        this.customerRepository = customerRepository;
        this.clientRegistrationRepository = clientRegistrationRepository;
    }
    @GetMapping("/customers")
    //Dans un premier temps sa marche pas car le JWT ne contient pas des roles
    @PreAuthorize("hasAuthority('ADMIN')")
    public String customers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customers";
    }
    @GetMapping("/products")
    public String products(Model model) {
        return "products";
    }
    @GetMapping("/home")
    public String home(){
        return "template";
    }
    @GetMapping("/auth")
    @ResponseBody
    public Authentication getAuthentication(Authentication authentication) {
        //Objet de Spring Security qui contient la session, les informations sur utilisateur authentifier
        return authentication;
    }
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/notAuthorized")
    public String notAuthorized(){
        return "notAuthorized";
    }
    @GetMapping("/oauth2Login")
    public String oauth2Login(Model model){
        String authorizationRequestBaseUri = "oauth2/authorization";
        Map<String, String> oauth2AuthenticationUrls = new HashMap<>();
        Iterable<ClientRegistration> clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        clientRegistrations.forEach(registration -> {
            oauth2AuthenticationUrls.put(registration.getClientName(), authorizationRequestBaseUri+"/"+registration.getRegistrationId());
        });
        model.addAttribute("urls", oauth2AuthenticationUrls);
        return "oauth2Login";
    }
}
