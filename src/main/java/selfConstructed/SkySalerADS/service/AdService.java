package selfConstructed.SkySalerADS.service;

import selfConstructed.SkySalerADS.dto.AdDTO;

import java.util.List;

public interface AdService {
    AdDTO createAd(AdDTO adDTO, Integer userId);

    AdDTO updateAd(AdDTO adDTO);

    void deleteAd(Integer adId, Integer userId);

    AdDTO getAdById(Integer adId);

    List<AdDTO> getAllAds();

    List<AdDTO> getAdsByUserId(Integer userId);
}
