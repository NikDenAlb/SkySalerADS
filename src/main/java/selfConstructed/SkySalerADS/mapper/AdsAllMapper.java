package selfConstructed.SkySalerADS.mapper;

import selfConstructed.SkySalerADS.dto.AdsDTO;
import selfConstructed.SkySalerADS.model.Ad;

public interface AdsAllMapper {
    AdsDTO toDto(Ad ad);

    Ad toModel(AdsDTO adsDTO);
}
