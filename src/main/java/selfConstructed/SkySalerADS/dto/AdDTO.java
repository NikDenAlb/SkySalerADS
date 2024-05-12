package selfConstructed.SkySalerADS.dto;

import lombok.Data;

@Data
public class AdDTO {
    private Long author;
    private String[] image;
    private Long pk;
    private Double price;
    private String title;
}
