package nl.ronald.beershop.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Review {

    public enum Review_status {
        UNAPPROVED,
        APPROVED,
        REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long product_id;

    @Column
    private long customer_id;

    @Column
    private float stars_taste;

    @Column
    private float stars_overall;

    @Column
    private float stars_price;

    @Column
    private String message;

    @Enumerated(EnumType.STRING)
    private Review_status review_status;
}
