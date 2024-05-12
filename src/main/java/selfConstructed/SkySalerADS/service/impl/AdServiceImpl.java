package selfConstructed.SkySalerADS.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import selfConstructed.SkySalerADS.dto.AdDTO;
import selfConstructed.SkySalerADS.dto.AdsAllDTO;
import selfConstructed.SkySalerADS.mapper.AdMapper;
import selfConstructed.SkySalerADS.mapper.AdsAllMapper;
import selfConstructed.SkySalerADS.model.Ad;
import selfConstructed.SkySalerADS.model.User;
import selfConstructed.SkySalerADS.repository.AdRepository;
import selfConstructed.SkySalerADS.service.AdService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code AdServiceImpl} class implements the {@link AdService} interface,
 * providing methods for managing advertisements.
 *
 * @author shinkevich oleg
 */
@Service
@AllArgsConstructor
public class AdServiceImpl implements AdService {


    private AdRepository adRepository;

    private AdMapper adMapper;

    /**
     * Creates a new advertisement.
     *
     * @param adDTO  the DTO object containing the information about the advertisement to be created
     * @param userId the identifier of the user creating the advertisement
     * @return the created advertisement as a DTO object
     */
    @Override
    public AdDTO createAd(AdDTO adDTO, Long userId) {
        Ad ad = adMapper.toModel(adDTO);
        User user = new User();
        user.setId(userId);
        ad.setAuthor(user);
        return adMapper.toDto(adRepository.save(ad));
    }


    /**
     * Updates an advertisement.
     *
     * @param adDTO the DTO object containing the information about the advertisement to be updated
     * @return the updated advertisement as a DTO object
     */
    @Override
    public AdDTO updateAd(AdDTO adDTO) {
        Ad existingAd = adRepository.findById(adDTO.getPk())
                .orElseThrow(() -> new IllegalArgumentException("Ad not found"));

        Ad updatedAd = adMapper.toModel(adDTO);
        updatedAd.setTitle(existingAd.getTitle());
        updatedAd.setPrice(existingAd.getPrice());
        updatedAd.setImage(existingAd.getImage());

        return adMapper.toDto(adRepository.save(updatedAd));
    }

    /**
     * Deletes an advertisement by its identifier.
     *
     * @param adId   the identifier of the advertisement to delete
     * @param userId the identifier of the user attempting to delete the advertisement
     */
    @Override
    public void deleteAd(Long adId, Long userId) {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new IllegalArgumentException("Ad not found"));

        if (!ad.getAuthor().getId().equals(userId)) {
            throw new IllegalArgumentException("User is not authorized to delete this ad");
        }

        adRepository.deleteById(adId);
    }

    /**
     * Retrieves an advertisement by its identifier.
     *
     * @param adId the identifier of the advertisement
     * @return the advertisement as a DTO object
     */
    @Override
    public AdDTO getAdById(Long adId) {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new IllegalArgumentException("Ad not found"));
        return adMapper.toDto(ad);
    }

    /**
     * Retrieves all advertisements.
     *
     * @return the list of all advertisements as DTO objects
     */
    @Override
    public List<AdDTO> getAllAds() {
        List<Ad> ads = adRepository.findAll();
        return ads.stream()
                .map(adMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdsAllDTO getAllAdsDTO() {
        List<AdDTO> ad = getAllAds();
       return new AdsAllDTO(ad.size(),ad);
    }

    /**
     * Retrieves advertisements created by the user with the specified identifier.
     *
     * @param userId the identifier of the user
     * @return the list of advertisements created by the specified user as DTO objects
     */
    @Override
    public List<AdDTO> getAdsByUserId(Long userId) {
        List<Ad> ads = adRepository.findByAuthorId(userId);
        return ads.stream()
                .map(adMapper::toDto)
                .collect(Collectors.toList());
    }
}

