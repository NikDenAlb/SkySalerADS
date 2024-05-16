package selfConstructed.SkySalerADS.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdsDTO {
    private Integer count;
    private List<AdDTO> results;

    public AdsDTO(List<AdDTO> results) {
        this.results = results;
        this.count = results.size();
    }
}
