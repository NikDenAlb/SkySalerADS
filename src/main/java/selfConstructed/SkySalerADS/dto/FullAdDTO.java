package selfConstructed.SkySalerADS.dto;

import lombok.Data;

@Data
public class FullAdDTO {

    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String[] image;
    private String phone;
    private long pk;
    private long price;
    private String title;

}