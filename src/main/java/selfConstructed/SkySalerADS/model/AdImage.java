package selfConstructed.SkySalerADS.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table
public class AdImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer Id;

    private String type;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] image;

    @OneToOne
    private Ad ad;
}