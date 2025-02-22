package project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_items")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "order_id",
            referencedColumnName = "id"
    )
    @JsonIgnore
    private Order order;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id"
    )
    private Product product;

    private Integer quantity;

    private BigDecimal itemPrice;


    public OrderItems(Order order, Product product, Integer quantity, BigDecimal itemPrice) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }
}
