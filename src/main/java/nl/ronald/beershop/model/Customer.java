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

    @Enumerated(EnumType.STRING)
    private sex sex;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String company;

    @Column(name="birth_date")
    private String birthDate;

    @Column
    private boolean newsletter;

    private String message;

    @Column
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
