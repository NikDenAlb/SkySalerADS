package selfConstructed.SkySalerADS.dto;

import lombok.Data;

@Data
public class AdDTO {
    private UserDTO author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;
}
