//package selfConstructed.SkySalerADS.controller;
//
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import selfConstructed.SkySalerADS.dto.AdsAllDTO;
//import selfConstructed.SkySalerADS.service.AdService;
//
//@Slf4j
//@CrossOrigin(value = "http://localhost:3000")
//@RestController
//@RequestMapping("/ads")
//@RequiredArgsConstructor
//public class AdsController {
//    private final AdService adService;
//
//    /**
//     * get All ads
//     */
//    @ApiResponses({
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "Got all ads",
//                    content = @Content(
//                            mediaType = MediaType.APPLICATION_JSON_VALUE,
//                            schema = @Schema(implementation = AdsAllDTO.class)
//                    )
//            )
//    })
//    @GetMapping
//    public ResponseEntity<AdsAllDTO> getAllAds() {
//        return new ResponseEntity<>(adService.getAllAdsDTO(), HttpStatus.OK);
//    }
//
//    /**
//     * Create new ads
//     */
//    @ApiResponses({
//
//            @ApiResponse(
//                    responseCode = "201",
//                    description = "Created new Ads",
//                    content = {@Content(
//                            mediaType = MediaType.APPLICATION_JSON_VALUE,
//                            schema = @Schema(implementation = AdDTO.class)
//                    )}
//            ),
//            @ApiResponse(
//                    responseCode = "401",
//                    description = "Unauthorized User"
//            ),
//            @ApiResponse(
//                    responseCode = "403",
//                    description = "Action Forbidden"
//            ),
//            @ApiResponse(
//                    responseCode = "404",
//                    description = "Ads Not Found"
//            )
//    })
//
//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @PreAuthorize("hasAuthority('user_basic_access')")
//    public ResponseEntity<AdDTO> createAdDTO(@RequestPart(value = "properties") PreAdDTO preAdDTO,
//                                             @RequestParam(value = "image") MultipartFile[] files) {
//        return new ResponseEntity<>(adService.createAd(preAdDTO, files), HttpStatus.CREATED);
//    }
//}
