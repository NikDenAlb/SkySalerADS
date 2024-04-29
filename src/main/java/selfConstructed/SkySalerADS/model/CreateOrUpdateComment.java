package selfConstructed.SkySalerADS.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model for creating or updating a comment.
 */
@Data
@NoArgsConstructor
public class CreateOrUpdateComment {

    /**
     * Comment text.
     */
    private String text;
}