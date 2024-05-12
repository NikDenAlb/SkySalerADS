package selfConstructed.SkySalerADS.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class PreAdDTO {
    @NotNull(message = "description must be not null")
    private String description;
    @NotNull(message = "price must be not null")
    @Positive(message = "price must be positive")
    private Long price;
    @NotBlank(message = "title must be not blank")
    private String title;
}
