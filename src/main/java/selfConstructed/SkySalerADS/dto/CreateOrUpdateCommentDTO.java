package selfConstructed.SkySalerADS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateCommentDTO {
    @Size(min = 8, max = 64, message = "The number must be at least 8 characters and no more than 64")
    private String text;
}
