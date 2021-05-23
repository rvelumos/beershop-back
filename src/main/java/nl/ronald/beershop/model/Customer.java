package nl.ronald.beershop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Customer {

    public enum sex {
        F,
        M
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@OneToMany(mappedBy = "orders")
    //private List<Order> order = new ArrayList<>();

//    @OneToMany(mappedBy="customer")
//    private Set<Discount> Discounts;
//
//    @OneToMany(mappedBy="customer")
//    private Set<Order> Orders;

    @Enumerated(EnumType.STRING)
    private sex sex;

    @Column(name="user_id")
    private long userId;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String username;

    @Column
    private String email;

    @Column(name="birth_date")
    private String birthDate;

    @Column
    private boolean newsletter;

    private String message;

    @Column
    private String phone;

//    @ManyToOne
//    @JoinColumn(name = "address_id")
//    private Customer customer;

    @OneToOne(mappedBy = "customer")
    @JsonIgnore
    private Address address;

}
