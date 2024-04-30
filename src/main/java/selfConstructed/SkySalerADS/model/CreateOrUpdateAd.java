package selfConstructed.SkySalerADS.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Модель для создания или обновления объявления.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "create_or_update_ad")
public class CreateOrUpdateAd {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
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