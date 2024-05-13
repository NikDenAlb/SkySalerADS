package selfConstructed.SkySalerADS.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import selfConstructed.SkySalerADS.dto.*;
import selfConstructed.SkySalerADS.exception.AdsNotFoundException;
import selfConstructed.SkySalerADS.exception.BrokenImageUpdateException;
import selfConstructed.SkySalerADS.mapper.AdMapper;
import selfConstructed.SkySalerADS.mapper.ImageMapper;
import selfConstructed.SkySalerADS.model.Ad;
import selfConstructed.SkySalerADS.model.AdImage;
import selfConstructed.SkySalerADS.model.User;
import selfConstructed.SkySalerADS.repository.AdImageRepository;
import selfConstructed.SkySalerADS.repository.AdRepository;
import selfConstructed.SkySalerADS.service.AdService;
import selfConstructed.SkySalerADS.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    private final UserService userService;
    private AdRepository adRepository;
    private final ImageMapper imageMapper;
    private final AdImageRepository adImageRepository;

    private AdMapper adMapper;

    @Override
    public AdDTO createAd(PreAdDTO preAdDto, MultipartFile[] files) {
        log.info("try to add ads");
        User user = userService.getUserFromAuthentication();
        log.info("try to get user from authentication");
        try {
            Ad newAds = adRepository.save(adMapper.toModel(preAdDto, user));

            List<AdImage> adsImageList = new ArrayList<>();
            for (MultipartFile file : files) {
                AdImage newAdsImage = imageMapper.toAdsImage(file);
                newAdsImage.setAds(newAds);
                adImageRepository.save(newAdsImage);
                adsImageList.add(newAdsImage);
            }
            Ad updAds = findAd(newAds.getPk());
            updAds.setImage(adsImageList);
            Ad response = adRepository.save(updAds);

            return adMapper.toDto(response);
        } catch (IOException e) {
            throw new BrokenImageUpdateException("ads hasn't saved");
        }
    }

    private Ad findAd(long adId) {
        return adRepository.findAdByPk(adId)
                .orElseThrow(AdsNotFoundException::new);
    }

    @Override
    public AdDTO createAd(PreAdDTO preAdDto, MultipartFile files) {
        return null;
    }

    @Override
    public AdDTO createAd(AdDTO adDTO, Long userId) {
        return null;
    }


    @Override
    public AdDTO createAd(AdDTO adDTO, Integer userId) {
        Ad ad = adMapper.toModel(adDTO);
        User user = new User();
        user.setId(userId);
        ad.setAuthor(user);
        return adMapper.toDto(adRepository.save(ad));
    }


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


    @Override
    public void deleteAd(Long adId, Long userId) {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new IllegalArgumentException("Ad not found"));

        throw new IllegalArgumentException("User is not authorized to delete this ad");

    }

    @Override
    public AdDTO getAdById(Long adId) {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new IllegalArgumentException("Ad not found"));
        return adMapper.toDto(ad);
    }


    public List<AdDTO> getAllAds() {
        List<Ad> ads = adRepository.findAll();
        return ads.stream()
                .map(adMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdsAllDTO getAllAdsDTO() {
        List<AdDTO> ad = getAllAds();
        return new AdsAllDTO(ad.size(), ad);
    }

    @Override
    public List<AdDTO> getAdsByUserId(Long userId) {
        return List.of();
    }


    @Transactional
    @Override
    public FullAdDTO getFullAds(long adsId) {
        return adMapper.toFullAdsDto(findAd(adsId));
    }

    @Transactional
    @Override
    public AdDTO removeAds(long adsId) {
        log.info("try to remove ads if it's found by id");
        Ad adsForRemove = findAd(adsId);
        User user = userService.getUserFromAuthentication();
        checkThisIsYourAdsOrYouAdmin(adsForRemove, user);
        adRepository.deleteById(adsId);
        return adMapper.toDto(adsForRemove);
    }

    private void checkThisIsYourAdsOrYouAdmin(Ad ads, User user) {
        if (!ads.getAuthor().equals(user) && !userService.isAdmin()) {
            log.warn("Unavailable to update. It's not your ads! ads author = {}, login = {}", ads.getAuthor().getUsername(), user.getUsername());
            throw new RuntimeException();
        }
    }

    @Override
    public boolean isAdmin() {
        log.info("Try to check whether the user is an administrator or not");
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("admin_full_access"));
    }

    @Override
    public AdDTO updateAdsImage(long adsId, MultipartFile file) {
        return null;
    }

    @Override
    public Object updateAd(long adsId, CreateAdDTO adDTO) {
        return null;
    }
}

