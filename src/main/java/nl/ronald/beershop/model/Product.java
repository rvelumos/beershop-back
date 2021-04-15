package nl.ronald.beershop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "category_id")
    private long categoryId;

    @Column
    private long manufacturer_id;

    @Column
    private String name;

    @Column
    private String taste;

    @Column
    private double price;

    @Column
    private Integer stock;

    @Column
    private String description;

    @Column
    private long type;

    @Column
    private String discount;

    @Column
    private Date created_at;

    @Column
    private Date updated_at;

}
