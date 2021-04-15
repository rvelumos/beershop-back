package nl.ronald.beershop.model;

import lombok.Data;
import javax.persistence.*;

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

   // @OneToMany(mappedBy = "orders")
    //private List<Order> order = new ArrayList<>();

    //@OneToMany(mappedBy="customer")
    //private Set<Order> Orders;

    @Enumerated(EnumType.STRING)
    private sex sex;

    @Column
    private long user_id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String email;

    @Column
    private String birth_date;

    @Column
    private boolean newsletter;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    private String customer_points;

}
