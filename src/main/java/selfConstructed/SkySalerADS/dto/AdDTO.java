package selfConstructed.SkySalerADS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdDTO {
    private int author;
    private String image;
    private int pk;
    private int price;
    private String title;
}
