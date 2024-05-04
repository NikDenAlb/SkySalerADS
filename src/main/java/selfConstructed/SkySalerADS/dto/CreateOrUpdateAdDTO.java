package selfConstructed.SkySalerADS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateAdDTO {
    @Size(min = 4, max = 32, message = "The number must be at least 4 characters and no more than 32")
    private String title;
    @Size(max = 1000, message = "The number must be at least 0 characters and no more than 10000000")
    private Integer price;
    @Size(min = 8, max = 64, message = "The number must be at least 8 characters and no more than 64")
    private String description;
}
