package selfConstructed.SkySalerADS.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table
public class AdImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ads_image_id")
    private Integer Id;

    private String type;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] image;

    @OneToOne
    private Ad ad;
}