package selfConstructed.SkySalerADS.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ad")
public class Ad {
    /**
     * Author of the ad.
     */
    @ManyToOne
    @JoinColumn(name = "author_user_id")
    private User author;

    /**
     * Link to ad image.
     */
    private String image;

    /**
     * Declaration primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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