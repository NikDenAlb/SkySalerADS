package selfConstructed.SkySalerADS.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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