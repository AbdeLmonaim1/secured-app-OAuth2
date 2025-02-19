package ma.app.appcustomerfrontthymeleaf.model;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Builder @Getter @Setter
public class Product {
    private String id;
    private String name;
    private Double price;
    private int quantity;
}
