package nl.ronald.beershop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long order_id;

    @Column
    private String customer_name;

    @Column
    private String address_type;

    @Column
    private String street;

    @Column
    private String street_add;

    @Column
    private String number;

    @Column
    private String postal_code;

    @Column
    private String city;

    @Column
    private String province;

    @Column
    private String country;
}

