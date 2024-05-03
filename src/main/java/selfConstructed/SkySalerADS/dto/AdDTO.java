package selfConstructed.SkySalerADS.dto;

import lombok.Data;
import selfConstructed.SkySalerADS.model.User;

@Data
public class AdDTO {
    private User author;
    private byte[] image;
    private int pk;
    private int price;
    private String title;
}
