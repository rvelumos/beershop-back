package nl.ronald.beershop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OrderProduct {
    @EmbeddedId
    OrderProductKey id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    Product product;

    int quantity;
}
