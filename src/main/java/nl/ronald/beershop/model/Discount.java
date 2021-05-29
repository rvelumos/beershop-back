package nl.ronald.beershop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;
import java.util.Set;

@Data
@Entity
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_id")
    private long productId;

    @Column
    private String name;

    @Column
    private String code;

    @Column
    private String amount;

    @Column
    private Date expiration_date;

    @Column
    private Integer uses;

    @Column
    private String allowed_usages;

    @Column
    private String username;
}
