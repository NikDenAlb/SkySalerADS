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
@Table
public class Ad {

    @ManyToOne
    private User user;

    @OneToOne
    private AdImage adImage;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    private Integer price;

    private String title;
}