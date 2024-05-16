package selfConstructed.SkySalerADS.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String type;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] image;

    @OneToOne
    private User user;
}