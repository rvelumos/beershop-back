package nl.ronald.beershop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "address")
    @JsonIgnore
    private Set<Shipping> Shipping;

//    @Column(name="customer_id")
//    private Long customerId;

    @Column(name="address_type")
    private String addressType;

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

}