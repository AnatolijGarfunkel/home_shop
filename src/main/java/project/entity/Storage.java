package project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "storage")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id"
    )
    private Product product;

    private Integer quantity;

    public Storage(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
