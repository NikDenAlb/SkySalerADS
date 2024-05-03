package selfConstructed.SkySalerADS.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ad")
public class Ad {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    /**
     * Author of the ad.
     */
    @ManyToOne
    @JoinColumn(name = "author_user_id")
    private User author;

    /**
     * Link to ad image.
     */
    private byte[] image;

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