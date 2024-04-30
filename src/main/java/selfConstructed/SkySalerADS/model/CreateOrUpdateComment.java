package selfConstructed.SkySalerADS.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Model for creating or updating a comment.
 */
@Data
@NoArgsConstructor
@Entity
public class CreateOrUpdateComment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    /**
     * Comment text.
     */
    private String text;
}