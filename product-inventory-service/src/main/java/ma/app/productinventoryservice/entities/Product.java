package ma.app.productinventoryservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Builder @Getter @Setter
public class Product {
    @Id
    private String id;
    private String name;
    private Double price;
    private int quantity;
}
