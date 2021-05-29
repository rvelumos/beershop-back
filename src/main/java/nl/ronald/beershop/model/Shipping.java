package nl.ronald.beershop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "order_id")
    private long orderId;

    @Column
    private String note;

    @OneToOne(mappedBy = "shipping")
    @JsonIgnore
    private Order order;

    @Column
    private String street;

    @Column(name="street_add")
    private String streetAdd;

    @Column
    private String number;

    @Column(name="postal_code")
    private String postalCode;

    @Column
    private String city;

    @Column
    private String province;

    @Column
    private String country;

    @Column
    private String username;
}

