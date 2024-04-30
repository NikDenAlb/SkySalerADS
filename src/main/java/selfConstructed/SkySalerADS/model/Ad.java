package selfConstructed.SkySalerADS.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    /**
     * Author of the ad.
     */
    private String author;

    /**
     * Link to ad image.
     */
    private String image;

    /**
     * Declaration primary key.
     */
    private Long pk;

    /**
     * Ad price.
     */
    private Double price;

    /**
     * Headline.
     */
    private String title;
}