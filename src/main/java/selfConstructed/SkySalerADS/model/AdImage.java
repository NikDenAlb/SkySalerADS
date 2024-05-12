package selfConstructed.SkySalerADS.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "ad_image")
public class AdImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ads_image_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad ads;
    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_size")
    private Long filesize;

    @Column(name = "media_type")
    private String mediaType;

    @Lob
    @Column(name = "preview")
    private byte[] data;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AdImage adsImage = (AdImage) o;
        return id != null && Objects.equals(id, adsImage.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}