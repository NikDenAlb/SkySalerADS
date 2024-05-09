package selfConstructed.SkySalerADS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import selfConstructed.SkySalerADS.dto.AdDTO;
import selfConstructed.SkySalerADS.mapper.AdMapper;
import selfConstructed.SkySalerADS.model.Ad;
import selfConstructed.SkySalerADS.model.Role;
import selfConstructed.SkySalerADS.model.User;
import selfConstructed.SkySalerADS.repository.AdRepository;
import selfConstructed.SkySalerADS.service.impl.AdServiceImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the {@link AdServiceImpl} class.
 * @author shinkevich oleg
 */
class AdServiceImplTest {

    @Mock
    private AdRepository adRepository;

    @Mock
    private AdMapper adMapper;

    @InjectMocks
    private AdServiceImpl adService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the {@link AdServiceImpl#createAd(AdDTO, Long)} method with valid input.
     */
    @Test
    void createAd_ValidInput_ReturnsCreatedAdDTO() {

        AdDTO adDTO = new AdDTO();
        adDTO.setTitle("Test Ad");
        adDTO.setPrice(100.0);
        adDTO.setImage("test_image.jpg");
        Long userId = 1L;
        Ad ad = new Ad();
        when(adMapper.toModel(adDTO)).thenReturn(ad);
        when(adRepository.save(ad)).thenReturn(ad);
        when(adMapper.toDto(ad)).thenReturn(adDTO);

        AdDTO createdAdDTO = adService.createAd(adDTO, userId);

        assertNotNull(createdAdDTO);
        assertEquals(adDTO.getTitle(), createdAdDTO.getTitle());
        assertEquals(adDTO.getPrice(), createdAdDTO.getPrice());
        assertEquals(adDTO.getImage(), createdAdDTO.getImage());
    }

    /**
     * Tests the {@link AdServiceImpl#updateAd(AdDTO)} method with an existing advertisement.
     */
    @Test
    void updateAd_ExistingAd_ReturnsUpdatedAdDTO() {
        User user = new User();
        user.setId(1L);
        user.setImage("test.jpg");
        user.setPhone("+123");
        user.setLogin("TestLogin");
        user.setPassword("TestPassword");
        user.setRole(Role.USER);
        user.setFirstName("TestName");

        Ad existingAd = new Ad();
        existingAd.setPk(1L);
        existingAd.setTitle("Test Ad");
        existingAd.setPrice(100.0);
        existingAd.setImage("test_image.jpg");
        existingAd.setAuthor(user);

        AdDTO adDTO = new AdDTO();
        adDTO.setPk(1L);
        adDTO.setTitle("Updated Test Ad");
        adDTO.setPrice(150.0);
        adDTO.setImage("updated_test_image.jpg");
        adDTO.setAuthor(1);

        Ad updatedAd = new Ad(user, adDTO.getImage(), adDTO.getPk(), adDTO.getPrice(), adDTO.getTitle());

        when(adRepository.findById(1L)).thenReturn(Optional.of(existingAd));
        when(adMapper.toDto(existingAd)).thenReturn(adDTO);
        when(adMapper.toModel(adDTO)).thenReturn(updatedAd);
        when(adRepository.save(any(Ad.class))).thenReturn(updatedAd);

        AdDTO updatedAdDTO = adService.updateAd(adDTO);

        assertNotNull(updatedAdDTO);
        assertEquals(adDTO.getTitle(), updatedAdDTO.getTitle());
        assertEquals(adDTO.getPrice(), updatedAdDTO.getPrice());
        assertEquals(adDTO.getImage(), updatedAdDTO.getImage());
    }

    /**
     * Tests the {@link AdServiceImpl#deleteAd(Long, Long)} method with valid advertisement and user identifiers.
     */
    @Test
    void deleteAd_ValidAdIdAndUserId_DeletesAd() {

        Long adId = 1L;
        Long userId = 1L;
        Ad ad = new Ad();
        ad.setPk(adId);
        User user = new User();
        user.setId(userId);
        ad.setAuthor(user);
        when(adRepository.findById(adId)).thenReturn(Optional.of(ad));

        adService.deleteAd(adId, userId);

        verify(adRepository, times(1)).deleteById(adId);
    }

    /**
     * Tests the {@link AdServiceImpl#getAdById(Long)} method with a valid advertisement identifier.
     */
    @Test
    void getAdById_ValidAdId_ReturnsAdDTO() {

        Long adId = 1L;
        Ad ad = new Ad();
        ad.setPk(adId);
        when(adRepository.findById(adId)).thenReturn(Optional.of(ad));
        AdDTO adDTO = new AdDTO();
        when(adMapper.toDto(ad)).thenReturn(adDTO);

        AdDTO retrievedAdDTO = adService.getAdById(adId);

        assertNotNull(retrievedAdDTO);
        assertEquals(adDTO, retrievedAdDTO);
    }

    /**
     * Tests the {@link AdServiceImpl#getAllAds()} method when no advertisements exist.
     */
    @Test
    void getAllAds_NoAds_ReturnsEmptyList() {

        when(adRepository.findAll()).thenReturn(Collections.emptyList());

        List<AdDTO> allAds = adService.getAllAds();

        assertNotNull(allAds);
        assertTrue(allAds.isEmpty());
    }

    /**
     * Tests the {@link AdServiceImpl#getAdsByUserId(Long)} method with a valid user identifier.
     */
    @Test
    void getAdsByUserId_ValidUserId_ReturnsAdDTOList() {

        Long userId = 1L;
        Ad ad1 = new Ad();
        Ad ad2 = new Ad();
        List<Ad> ads = Arrays.asList(ad1, ad2);
        when(adRepository.findByAuthorId(userId)).thenReturn(ads);
        AdDTO adDTO1 = new AdDTO();
        AdDTO adDTO2 = new AdDTO();
        when(adMapper.toDto(ad1)).thenReturn(adDTO1);
        when(adMapper.toDto(ad2)).thenReturn(adDTO2);

        List<AdDTO> userAds = adService.getAdsByUserId(userId);

        assertNotNull(userAds);
        assertEquals(2, userAds.size());
    }
}
