package project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.enums.PaymentStatus;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(
            name = "order_id",
            referencedColumnName = "id"
    )
    private Order order;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PROCESSING;


    public Payment(Order order, PaymentStatus status) {
        this.order = order;
        this.status = status;
    }
}
