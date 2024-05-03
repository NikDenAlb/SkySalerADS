package selfConstructed.SkySalerADS.mapper;

import selfConstructed.SkySalerADS.dto.AdsAllDTO;
import selfConstructed.SkySalerADS.model.Ad;

public interface AdsAllMapper {
    AdsAllDTO toDto(Ad ad);

    Ad toModel(AdsAllDTO adsAllDTO);
}
