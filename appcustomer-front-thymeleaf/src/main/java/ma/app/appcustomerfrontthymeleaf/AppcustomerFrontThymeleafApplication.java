package ma.app.appcustomerfrontthymeleaf;

import ma.app.appcustomerfrontthymeleaf.entities.Customer;
import ma.app.appcustomerfrontthymeleaf.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppcustomerFrontThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppcustomerFrontThymeleafApplication.class, args);
    }
    //@Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(Customer.builder().name("Monaim").email("monaim1@gmail.com").build());
            customerRepository.save(Customer.builder().name("Amine").email("amine2@gmail.com").build());
            customerRepository.save(Customer.builder().name("Mouad").email("mouad3@gmail.com").build());
        };
    }
}
