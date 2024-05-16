package selfConstructed.SkySalerADS.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import selfConstructed.SkySalerADS.dto.AdDTO;
import selfConstructed.SkySalerADS.dto.AdsDTO;
import selfConstructed.SkySalerADS.dto.CreateOrUpdateAdDTO;
import selfConstructed.SkySalerADS.dto.FullAdDTO;
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
    private final UserService userService;

    @Override
    public AdsDTO getAllAds() {
        log.info("Getting all ads");
        List<AdDTO> preOut = (adRepository.findAll()).stream()
                .map(adMapper::toDTO)
                .collect(Collectors.toList());
        return new AdsDTO(preOut);
    }

    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO inAdDTO, MultipartFile file) {

        log.info("adding ad");
        User user = userService.getUserFromAuthentication();
        log.info("getting user from authentication");
        try {
            Ad newAd = adRepository.save(adMapper.toModel(inAdDTO, user));
            AdImage newAdImage = imageMapper.toAdImage(file);

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
}