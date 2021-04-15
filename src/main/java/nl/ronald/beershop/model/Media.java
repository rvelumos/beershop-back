package nl.ronald.beershop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private long product_id;

    @Column
    private String mediatype;

    @Column
    private String url;

    @Column
    private String filesize;

}
