package nl.ronald.beershop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Search {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String keyword;

    @Column
    private Integer amount;
}