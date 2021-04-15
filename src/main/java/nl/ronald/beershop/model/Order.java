package nl.ronald.beershop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class Order {

    public enum Invoice_status {
        PAID,
        UNPAID
    }

    public enum Order_status {
        NEW_ADDED,
        PROCESSING,
        SENT,
        RECEIVED,
        CANCELLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@ManyToOne
   // @JoinColumn(name = "customer")
    //private Customer customer;

    @Column(name="customer_id")
    private long customerId;

    @Column
    private long shipping_id;

    @Column
    private Date order_date;

    @Column
    private Date order_sent;

    @Column
    private Long price_total;

    @Enumerated(EnumType.STRING)
    private Order_status order_status;

    @Enumerated(EnumType.STRING)
    private Invoice_status invoice_status;

}
