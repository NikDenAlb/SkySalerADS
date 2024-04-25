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
     * Заголовок объявления.
     */
    private String title;

    /**
     * Цена объявления.
     */
    private double price;

    /**
     * Описание объявления.
     */
    private String description;
}