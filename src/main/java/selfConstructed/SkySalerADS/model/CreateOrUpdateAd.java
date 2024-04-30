package selfConstructed.SkySalerADS.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Модель для создания или обновления объявления.
 */
@Data
@NoArgsConstructor
@Entity
public class CreateOrUpdateAd {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
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