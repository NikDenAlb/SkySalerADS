package selfConstructed.SkySalerADS.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель для создания или обновления объявления.
 */
@Data
@NoArgsConstructor
public class CreateOrUpdateAd {

    /**
     * Headline.
     */
    private String title;

    /**
     * Ad price.
     */
    private Double price;

    /**
     * Description of the ad.
     */
    private String description;
}