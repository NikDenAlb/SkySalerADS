package selfConstructed.SkySalerADS.dto;

import lombok.Data;
import selfConstructed.SkySalerADS.model.Ad;
import selfConstructed.SkySalerADS.model.AdImage;
import selfConstructed.SkySalerADS.model.User;

/**
 * @field author ({@link User} id)
 * @field image ({@link AdImage} address)
 * @field pk ({@link Ad})
 * @field price ({@link Ad})
 * @field title ({@link Ad})
 */
@Data
public class AdDTO {
    private Integer author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;
}
