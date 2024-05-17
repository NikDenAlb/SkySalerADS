package selfConstructed.SkySalerADS.service;

import org.springframework.web.multipart.MultipartFile;
import selfConstructed.SkySalerADS.dto.*;
import selfConstructed.SkySalerADS.model.Ad;
import selfConstructed.SkySalerADS.model.AdImage;

/**
 * The {@code AdService} interface provides methods for managing advertisements.
 * Implementing classes should define the behavior for creating, updating, deleting,
 * and retrieving advertisements.
 *
 * @author shinkevich oleg
 */
public interface AdService {

    AdsDTO getAllAds();

    AdDTO addAd(CreateOrUpdateAdDTO inAdDTO, MultipartFile file);

    FullAdDTO getFullAdDTO(Integer id);

    void removeAd(Integer id);

    AdDTO updateAd(Integer id, CreateOrUpdateAdDTO inAdDTO);

    AdsDTO getAdsMe();

    byte[] updateAdImage(Integer id, MultipartFile file);

    Ad getAd(int pk);

    CommentsDTO getAdComments(int id);

    AdImage getAdImageById(int id);

    CommentDTO createComment(Integer id, CreateOrUpdateCommentDTO createOrUpdateCommentDTO);

    void deleteComment(int adId, int commentId);

    CommentDTO updateComment(int adsId, int commentId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO);
}