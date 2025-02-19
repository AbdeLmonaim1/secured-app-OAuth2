package ma.app.productinventoryservice.web;

import ma.app.productinventoryservice.entities.Product;
import ma.app.productinventoryservice.repository.ProductRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductRestController {
    private ProductRepository productRepository;
    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping("/products")
    @PreAuthorize("hasAuthority('ADMiN')")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
