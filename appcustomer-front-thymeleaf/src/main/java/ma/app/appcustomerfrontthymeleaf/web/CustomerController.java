package ma.app.appcustomerfrontthymeleaf.web;

import ma.app.appcustomerfrontthymeleaf.repository.CustomerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomerController {
    private CustomerRepository customerRepository;
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @GetMapping("/customers")
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

}
