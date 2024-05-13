package selfConstructed.SkySalerADS.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "ads",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<AdImage> image;

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