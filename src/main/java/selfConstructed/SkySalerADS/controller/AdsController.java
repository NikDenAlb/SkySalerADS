package selfConstructed.SkySalerADS.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import selfConstructed.SkySalerADS.dto.*;
import selfConstructed.SkySalerADS.service.AdService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdsController {
//
    private final AdService adService;

    /**
     * get All ads
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Got all ads",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdsAllDTO.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<?> getAllAds() {
        return new ResponseEntity<>(adService.getAllAds(), HttpStatus.OK);
    }

    /**
     * Create new ads
     */
    @ApiResponses({

            @ApiResponse(
                    responseCode = "201",
                    description = "Created new Ads",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdDTO.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized User"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Action Forbidden"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ads Not Found"
            )
    })

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('user_basic_access')")
    public ResponseEntity<AdDTO> createAdDTO(@RequestPart(value = "properties") PreAdDTO preAdDTO,
                                             @RequestParam(value = "image") MultipartFile files) {
        return new ResponseEntity<>(adService.createAd(preAdDTO, files), HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Ads was deleted",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "User Unauthorized"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Action Forbidden"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ads Not Found"
            )
    })
    @DeleteMapping("/{adsId}")
    @PreAuthorize("hasAuthority('user_basic_access')")
    public ResponseEntity<AdDTO> removeAds(@PathVariable long adsId) {
        return new ResponseEntity<>(adService.removeAds(adsId), HttpStatus.NO_CONTENT);
    }

    /**
     * Get FullAds by adsId<br>
     * Use method of service {@link adService#getFullAds(long)}
     *
     * @return Full data Ads
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ads was gotten",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = FullAdDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized User"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Action Forbidden"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ads Not Found"
            )
    })
    @GetMapping("/{adsId}")
    @PreAuthorize("hasAuthority('user_basic_access')")
    public ResponseEntity<FullAdDTO> getFullAds(@PathVariable long adsId) {
        return new ResponseEntity<>(adService.getFullAds(adsId), HttpStatus.OK);
    }

    /**
     * Update ads<br>
     * Use method of service {@link adService#updateAds(long, CreateAdDTO)}
     *
     * @return Updated ads
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ads was updated ",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "User Unauthorized"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Action Forbidden"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ads Not Found"
            )
    })
    @PatchMapping("{adsId}")
    @PreAuthorize("hasAuthority('user_basic_access')")
    public ResponseEntity<?> updateAds(@PathVariable long adsId,
                                            @RequestBody CreateAdDTO AdDTO) {
        return new ResponseEntity<>(adService.updateAd(adsId, AdDTO), HttpStatus.OK);
    }


    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ads was updated ",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "User Unauthorized"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ads Not Found"
            )
    })
    @PatchMapping(value = "{adsId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('user_basic_access')")
    public ResponseEntity<AdDTO> updateAdsImage(@PathVariable long adsId,
                                                 @RequestParam(value = "image") MultipartFile file
    ) {
        return new ResponseEntity<>(adService.updateAdsImage(adsId, file), HttpStatus.OK);
    }



    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Got my ads",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdsAllDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized User"
            ),
    })
    @GetMapping("/me")
    @PreAuthorize("hasAuthority('user_basic_access')")
    public ResponseEntity<?> getAdsMe() {
        return new ResponseEntity<>(adService.getAllAds(), HttpStatus.OK);
    }
}
