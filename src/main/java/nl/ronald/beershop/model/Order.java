package nl.ronald.beershop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "orders")
public class Order {

    public enum InvoiceStatus {
        PAID,
        UNPAID
    }

    public enum OrderStatus {
        NEW_ADDED,
        PROCESSING,
        SENT,
        RECEIVED,
        CANCELLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_id", referencedColumnName = "id")
    private Shipping shipping;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @Column(name="customer_id")
    private long customerId;

//    @ManyToMany
//    @JoinTable(
//            name = "orders_product",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id"))
//    Set<Product> productOrders;

    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name="order_date")
    private Date orderDate;

    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name="order_sent")
    private Date orderSent;

    @Column(name="price_total")
    private double priceTotal;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus invoiceStatus;

    @Column
    private String username;
}