package nl.ronald.beershop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long user_id;

    @OneToMany(mappedBy = "manufacturer", cascade=CascadeType.ALL)
    @JsonIgnore
    private Set<Product> Product;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phone;

}
