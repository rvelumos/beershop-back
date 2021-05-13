package nl.ronald.beershop.payload;

import lombok.Data;

@Data
public class SimpleEmailRequest {
    public String firstname;
    public String lastname;
    public String email;
    public String message;
}
