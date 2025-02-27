package ma.app.productinventoryservice;

import ma.app.productinventoryservice.entities.Product;
import ma.app.productinventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class ProductInventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductInventoryServiceApplication.class, args);
    }
   // @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
          productRepository.save(Product.builder()
                          .id(UUID.randomUUID().toString())
                          .name("Iphone XS")
                          .price(4000.00)
                          .quantity(10)
                  .build());
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Iphone 16 Mini")
                    .price(12000.00)
                    .quantity(7)
                    .build());
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Iphone 16 Pro Max")
                    .price(14500.00)
                    .quantity(4)
                    .build());
           // productRepository.save(new Product(UUID.randomUUID().toString(), "Iphone X", 4500.00,11));
        };
    }
}
