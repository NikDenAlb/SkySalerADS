package selfConstructed.SkySalerADS.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ad {

    /**
     * Автор объявления.
     */
    private String author;

    /**
     * Ссылка на изображение объявления.
     */
    private String image;

    /**
     * Первичный ключ объявления.
     */
    private Long pk;

    /**
     * Цена объявления.
     */
    private Double price;

    /**
     * Заголовок объявления.
     */
    private String title;
}