package ra.ss17.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_cart")
public class ProductCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Transient
    private int totalPrice;
    private int customerId;
    private int productId;
    private int quantity;

    public void setProductId(Product product) {
    }

    public void setProductId(int productId) {
    }

    // Getters và Setters
}
