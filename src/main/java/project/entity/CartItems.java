package project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "cart_items")
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "cart_id",
            referencedColumnName = "id"
    )
    private Cart cart;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id"
    )
    private Product product;

    private int quantity;
}
