package nl.ronald.beershop.payload;

import lombok.Data;

@Data
public class ConfirmationRequest {
    public long id;
    public String firstname;
    public String lastname;
    public String email;

    public String street;
    public String streetAdd;
    public String postalCode;
    public String number;
    public String city;
    public String country;
    public String province;

    public String name;
    public String amount;
    public String totalPrice;
    public Integer orderId;
}
