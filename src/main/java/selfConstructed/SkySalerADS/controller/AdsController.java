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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import selfConstructed.SkySalerADS.dto.AdsAllDTO;
import selfConstructed.SkySalerADS.service.AdService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdsController {

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
    public ResponseEntity<AdsAllDTO> getAllAds() {
        return new ResponseEntity<>(adService.getAllAdsDTO(), HttpStatus.OK);
    }
}
