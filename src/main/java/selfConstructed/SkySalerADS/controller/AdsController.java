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
import selfConstructed.SkySalerADS.dto.AdDTO;
import selfConstructed.SkySalerADS.dto.AdsDTO;
import selfConstructed.SkySalerADS.dto.CreateOrUpdateAdDTO;
import selfConstructed.SkySalerADS.dto.FullAdDTO;
import selfConstructed.SkySalerADS.service.AdService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdsController {
    private final AdService adService;

    /**
     * All ads from DB
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Got all ads",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdsDTO.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<AdsDTO> getAllAds() {
        return new ResponseEntity<>(adService.getAllAds(), HttpStatus.OK);
    }

    /**
     * Create new Ad
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
    public ResponseEntity<AdDTO> addAd(@RequestPart(value = "properties") CreateOrUpdateAdDTO inAdDTO,
                                       @RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(adService.addAd(inAdDTO, file), HttpStatus.CREATED);
    }

    /**
     * Ad info from Ad.pk
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
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user_basic_access')")
    public ResponseEntity<FullAdDTO> getFullAds(@PathVariable Integer id) {
        return new ResponseEntity<>(adService.getFullAdDTO(id), HttpStatus.OK);
    }
}