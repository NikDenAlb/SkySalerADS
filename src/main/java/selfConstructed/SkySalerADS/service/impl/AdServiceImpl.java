package selfConstructed.SkySalerADS.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import selfConstructed.SkySalerADS.dto.*;
import selfConstructed.SkySalerADS.mapper.AdMapper;
import selfConstructed.SkySalerADS.mapper.CommentMapper;
import selfConstructed.SkySalerADS.mapper.ImageMapper;
import selfConstructed.SkySalerADS.model.Ad;
import selfConstructed.SkySalerADS.model.AdImage;
import selfConstructed.SkySalerADS.model.User;
import selfConstructed.SkySalerADS.repository.AdImageRepository;
import selfConstructed.SkySalerADS.repository.AdRepository;
import selfConstructed.SkySalerADS.repository.CommentRepository;
import selfConstructed.SkySalerADS.service.AdService;
import selfConstructed.SkySalerADS.service.UserService;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The {@code AdServiceImpl} class implements the {@link AdService} interface,
 * providing methods for managing advertisements.
 *
 * @author shinkevich oleg
 */
@Slf4j
@Service
@AllArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final AdImageRepository adImageRepository;
    private final AdMapper adMapper;
    private final ImageMapper imageMapper;
    private final CommentMapper commentMapper;
    private final UserService userService;
    private final CommentRepository commentRepository;

    @Transactional
    @Override
    public AdsDTO getAllAds() {
        log.info("Getting all ads");
        List<AdDTO> preOut = (adRepository.findAll()).stream()
                .map(adMapper::toDTO)
                .collect(Collectors.toList());
        return new AdsDTO(preOut);
    }

    @Transactional
    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO inAdDTO, MultipartFile file) {

        log.info("adding ad");
        User user = userService.getUserFromAuthentication();
        log.info("getting user from authentication");
        try {
            Ad newAd = adRepository.save(adMapper.toModel(inAdDTO, user));
            AdImage newAdImage = imageMapper.toAdImage(file);
            newAdImage.setAd(newAd);
            newAdImage.setType("image/jpeg");

            adImageRepository.save(newAdImage);
            newAd.setUser(user);
            newAd.setAdImage(newAdImage);
            adRepository.save(newAd);

            return adMapper.toDTO(newAd);
        } catch (IOException e) {
            log.warn("ads hasn't saved");
            throw new RuntimeException("ads hasn't saved");
        }
    }

    @Transactional
    @Override
    public FullAdDTO getFullAdDTO(Integer id) {
        log.info("getting full ad");
        Optional<Ad> ad = adRepository.findById(id);
        if (!ad.isPresent()) {
            log.warn("ad not found");
            throw new RuntimeException("ad not found");
        }
        return adMapper.toFullAdDTO(ad.get());
    }

    @Transactional
    @Override
    public void removeAd(Integer id) {
        log.info("removing ad with id {}", id);
        User user = userService.getUserFromAuthentication();
        chekAdandUser(id, user);

        adRepository.deleteById(id);
        log.info("ad with ad.pk=={} removed", id);
    }

    @Transactional
    @Override
    public AdDTO updateAd(Integer id, CreateOrUpdateAdDTO inAdDTO) {
        log.info("trying to update ads");
        if (inAdDTO.getDescription() == null || inAdDTO.getPrice() == null || inAdDTO.getTitle() == null) {
            throw new NullPointerException("description and price and title are required");
        }
        User user = userService.getUserFromAuthentication();
        log.info("try to find ads by id");
        chekAdandUser(id, user);
        Ad ad = adRepository.findById(id).get();
        Ad adUpdate = adMapper.toModel(inAdDTO, user);
        ad.setTitle(adUpdate.getTitle());
        ad.setPrice(adUpdate.getPrice());
        ad.setDescription(adUpdate.getDescription());
        log.info("The ad with id = {} is updated ", id);
        return adMapper.toDTO(adRepository.save(ad));
    }

    @Transactional
    @Override
    public AdsDTO getAdsMe() {
        log.info("try to get all ads of one user");
        User user = userService.getUserFromAuthentication();

        List<AdDTO> preOut = adRepository.findAllByUser(user).stream()
                .map(adMapper::toDTO)
                .collect(Collectors.toList());

        return new AdsDTO(preOut);
    }

    @Transactional
    @Override
    public byte[] updateAdImage(Integer id, MultipartFile file) {
        log.info("try to update ad image");
        User user = userService.getUserFromAuthentication();
        chekAdandUser(id, user);
        Ad ad = adRepository.findById(id).get();
        try {
            AdImage adImage = imageMapper.toAdImage(file);
            adImageRepository.deleteAdImageByAd(ad);
            adImage.setAd(ad);
            adImage.setType("image/jpeg");
            ad.setAdImage(adImage);
            adImageRepository.save(adImage);
        } catch (IOException e) {
            log.warn("unable to save image");
            throw new RuntimeException("unable to save image");
        }
        return ad.getAdImage().getImage();
    }

    @Transactional
    @Override
    public Ad getAd(int pk) {
        Optional<Ad> ad = adRepository.findById(pk);
        if (!ad.isPresent()) {
            log.warn("ad with pk={} not found", pk);
            throw new RuntimeException("ad not found");
        }
        return ad.get();
    }

    @Override
    public AdImage getAdImageByAd(Ad ad) {
        Optional<AdImage> adImage = adImageRepository.findByAd(ad);
        if (!adImage.isPresent()) {
            log.warn("adImage ia not found");
            throw new RuntimeException("adImage not found");
        }
        return adImage.get();
    }

    @Override
    public CommentsDTO getAdComments(int id) {
        log.info("try to get ad");
        if (!adRepository.findById(id).isPresent()) {
            throw new RuntimeException("ad not found");
        }
        Ad ad = adRepository.findById(id).get();
        log.info("try to get ads comments");


        List<CommentDTO> commentsDTO = commentRepository.findAllByAd(ad).stream()
                .map(commentMapper::toDto)
                .sorted(Comparator.comparing(CommentDTO::getCreatedAt))
                .collect(Collectors.toList());
        return new CommentsDTO(commentsDTO);

    }

    @Override
    public AdImage getAdImageById(int id) {
        Optional<AdImage> adImage = adImageRepository.findById(id);
        if (!adImage.isPresent()) {
            log.warn("adImage ia not  by id {}", id);
            throw new RuntimeException("adImage not found");
        }
        return adImage.get();
    }

    private void chekAdandUser(Integer id, User user) {
        Optional<Ad> optionalAd = adRepository.findById(id);
        if (!optionalAd.isPresent()) {
            log.warn("ad not found");
            throw new RuntimeException("ad not found");
        }
        Ad ad = optionalAd.get();
        if (!ad.getUser().equals(user) && !userService.isAdmin()) {
            log.warn("Request denied. ad's author = {}, but user = {}", ad.getUser().getUsername(), user.getUsername());
            throw new RuntimeException();
        }
    }
}