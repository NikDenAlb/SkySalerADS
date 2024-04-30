package selfConstructed.SkySalerADS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateAdDTO {
    private UUID id;
    private String title;
    private int price;
    private String description;
}
