package selfConstructed.SkySalerADS.service;

import selfConstructed.SkySalerADS.dto.AdDTO;

import java.util.List;

public interface AdService {
    AdDTO createAd(AdDTO adDTO, Long userId);

    AdDTO updateAd(AdDTO adDTO);

    void deleteAd(Long adId, Long userId);

    AdDTO getAdById(Long adId);

    List<AdDTO> getAllAds();

    List<AdDTO> getAdsByUserId(Long userId);
}
