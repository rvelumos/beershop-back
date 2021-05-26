package nl.ronald.beershop.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
class OrderProductKey implements Serializable {

    @Column(name = "order_id")
    Long orderId;

    @Column(name = "product_id")
    Long productId;
    
}