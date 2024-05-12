package selfConstructed.SkySalerADS.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdsAllDTO {
    private Integer count;
    private List<AdDTO> results;

    public AdsAllDTO(Integer count, List<AdDTO> results) {
        this.count = count;
        this.results = results;
    }

    public AdsAllDTO(int size, List<AdDTO> ad) {
    }
}
