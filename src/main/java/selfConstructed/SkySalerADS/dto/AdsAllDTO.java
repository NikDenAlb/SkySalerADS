package selfConstructed.SkySalerADS.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdsAllDTO {
    private Integer count;
    private List<AdDTO> results;
}
