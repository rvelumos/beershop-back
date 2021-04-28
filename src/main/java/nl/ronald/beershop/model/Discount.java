package nl.ronald.beershop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

@Data
@Entity
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public enum Discount_type {
        DISCOUNT_CASH,
        DISCOUNT_PERCENTAGE,
        GIFT_CARD
    }

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "product_id")
    private long productId;

    @Column
    private String name;

    @Column
    private String code;

    @Enumerated(EnumType.STRING)
    private Discount_type discount_type;

    @Column
    private String amount;

    @Column
    private Date expiration_date;

    @Column
    private Integer uses;

    @Column
    private String allowed_usages;

    //random code
    public String randomCodeGenerator(String discount_type) {

        String type = "";
        switch (discount_type) {
            case "GIFT_CARD":
                type = "GC";
                break;
            case "DISCOUNT_CASH":
                type = "DC";
                break;
            case "DISCOUNT_PERCENTAGE":
                type = "DP";
                break;
            default:
                type = "UK";
                break;
        }

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 18;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        buffer.append(type);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            if(i % 5 == 0){
                buffer.append("-");
            }

            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString().toUpperCase();

        return(generatedString);
    }
}
