package nl.ronald.beershop.payload;

import lombok.Data;

@Data
public class NewsLetterRequest {
    public String email;
    public String title;
    public String body;
}
