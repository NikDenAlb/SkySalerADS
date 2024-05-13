package selfConstructed.SkySalerADS.service;

import org.springframework.web.multipart.MultipartFile;
import selfConstructed.SkySalerADS.dto.*;

import java.util.List;

/**
 * The {@code AdService} interface provides methods for managing advertisements.
 * Implementing classes should define the behavior for creating, updating, deleting,
 * and retrieving advertisements.
 *
 * @author shinkevich oleg
 */
public interface AdService {

    AdDTO createAd(PreAdDTO preAdDto, MultipartFile[] files);

    /**
     * Create adDTO from PreAdDTO
     */
    AdDTO createAd(PreAdDTO preAdDto, MultipartFile files);

    /**
     * Creates a new advertisement.
     *
     */
    AdDTO createAd(AdDTO adDTO, Long userId);

    AdDTO createAd(AdDTO adDTO, Integer userId);

    /**
     * Updates an advertisement.
     *
     * @param adDTO the DTO object containing the information about the advertisement to be updated
     * @return the updated advertisement as a DTO object
     */
    AdDTO updateAd(AdDTO adDTO);

    /**
     * Deletes an advertisement by its identifier.
     *
     * @param adId   the identifier of the advertisement to delete
     * @param userId the identifier of the user attempting to delete the advertisement
     */
    void deleteAd(Long adId, Long userId);

    /**
     * Retrieves an advertisement by its identifier.
     *

     */
    AdDTO getAdById(Long adId);

    /**
     * Retrieves all advertisements.
     *
     */
    List<AdDTO> getAllAds();

    AdsAllDTO getAllAdsDTO();

    /**
     * Retrieves advertisements created by the user with the specified identifier.
     *
     * @param userId the identifier of the user
     * @return the list of advertisements created by the specified user as DTO objects
     */
    List<AdDTO> getAdsByUserId(Long userId);

    FullAdDTO getFullAds(long adsId);

    AdDTO removeAds(long adsId);

    boolean isAdmin();

    AdDTO updateAdsImage(long adsId, MultipartFile file);

    Object updateAd(long adsId, CreateAdDTO adDTO);
}
