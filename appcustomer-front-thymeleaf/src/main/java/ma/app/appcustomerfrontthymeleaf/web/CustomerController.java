package ma.app.appcustomerfrontthymeleaf.web;

import ma.app.appcustomerfrontthymeleaf.model.Product;
import ma.app.appcustomerfrontthymeleaf.repository.CustomerRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.List;
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
    //En va envoyer une requette qu microservice pour recuperer les produits
    @GetMapping("/products")


    public String products(Model model) {
        //1 - il faut recuperer JWT d'utilisateur
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        //On suppose qu'on a fait l'authentification avec une client Oauth2 (Keycloack ou Google)
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        DefaultOidcUser oidcUser = (DefaultOidcUser) oAuth2AuthenticationToken.getPrincipal();
        //Recuperer le Token
        String jwtTokenValue = oidcUser.getIdToken().getTokenValue();
        System.out.println(jwtTokenValue);
        RestClient restClient = RestClient.create("http://localhost:8098");
        List<Product> products = restClient.get()
                .uri("/products")
                .headers(httpHeaders -> httpHeaders.set(httpHeaders.AUTHORIZATION, "Bearer "+jwtTokenValue))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {})
                ;
        model.addAttribute("products", products);
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
