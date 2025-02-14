package ma.app.appcustomerfrontthymeleaf.web;

import ma.app.appcustomerfrontthymeleaf.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
