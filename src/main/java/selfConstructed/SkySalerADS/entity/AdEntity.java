package selfConstructed.SkySalerADS.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdEntity {
    private int author;
    private String image;
    private int pk;
    private int price;
    private String title;
}
