package selfConstructed.SkySalerADS.service;

import org.springframework.web.multipart.MultipartFile;
import selfConstructed.SkySalerADS.dto.AdDTO;
import selfConstructed.SkySalerADS.dto.AdsAllDTO;
import selfConstructed.SkySalerADS.dto.PreAdDTO;

/**
 * The {@code AdService} interface provides methods for managing advertisements.
 * Implementing classes should define the behavior for creating, updating, deleting,
 * and retrieving advertisements.
 *
 * @author shinkevich oleg
 */
public interface AdService {

    /**
     * Create adDTO from PreAdDTO
     */
    AdDTO createAd(PreAdDTO preAdDto, MultipartFile[] files);

//    /**
//     * Creates a new advertisement.
//     *
//     * @param adDTO  the DTO object containing the information about the advertisement to be created
//     * @param userId the identifier of the user creating the advertisement
//     * @return the created advertisement as a DTO object
//     */
//    AdDTO createAd(AdDTO adDTO, Long userId);
//
//    /**
//     * Updates an advertisement.
//     *
//     * @param adDTO the DTO object containing the information about the advertisement to be updated
//     * @return the updated advertisement as a DTO object
//     */
//    AdDTO updateAd(AdDTO adDTO);
//
//    /**
//     * Deletes an advertisement by its identifier.
//     *
//     * @param adId   the identifier of the advertisement to delete
//     * @param userId the identifier of the user attempting to delete the advertisement
//     */
//    void deleteAd(Long adId, Long userId);
//
//    /**
//     * Retrieves an advertisement by its identifier.
//     *
//     * @param adId the identifier of the advertisement
//     * @return the advertisement as a DTO object
//     */
//    AdDTO getAdById(Long adId);
//
//    /**
//     * Retrieves all advertisements.
//     *
//     * @return the list of all advertisements as DTO objects
//     */
//    List<AdDTO> getAllAds();

    AdsAllDTO getAllAdsDTO();

//    /**
//     * Retrieves advertisements created by the user with the specified identifier.
//     *
//     * @param userId the identifier of the user
//     * @return the list of advertisements created by the specified user as DTO objects
//     */
//    List<AdDTO> getAdsByUserId(Long userId);
}
