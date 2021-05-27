package nl.ronald.beershop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name="manufacturer_id", referencedColumnName = "id")
    private Manufacturer manufacturer;

    @ManyToMany(mappedBy = "productOrders")
    Set<Order> ordered;

    @Column
    private String name;

    @Column
    private String image;

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

}