package selfConstructed.SkySalerADS.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import selfConstructed.SkySalerADS.dto.*;
import selfConstructed.SkySalerADS.model.AdImage;
import selfConstructed.SkySalerADS.service.AdService;

import java.util.Objects;

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
                                       @RequestPart(value = "image") MultipartFile file) {
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
    public ResponseEntity<FullAdDTO> getFullAds(@PathVariable Integer id) {
        return new ResponseEntity<>(adService.getFullAdDTO(id), HttpStatus.OK);
    }

    /**
     * Delete ad by Ad.pk
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Ads was deleted"
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
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user_basic_access')")
    public ResponseEntity<?> removeAds(@PathVariable Integer id) {
        adService.removeAd(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Update ad by ad.pk
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
    @PatchMapping("{id}")
    @PreAuthorize("hasAuthority('user_basic_access')")
    public ResponseEntity<AdDTO> updateAds(@PathVariable Integer id,
                                           @RequestBody CreateOrUpdateAdDTO inAdDTO) {
        return new ResponseEntity<>(adService.updateAd(id, inAdDTO), HttpStatus.OK);
    }

    /**
     *
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Got my ads",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdsDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized User"
            ),
    })
    @GetMapping("/me")
    @PreAuthorize("hasAuthority('user_basic_access')")
    public ResponseEntity<AdsDTO> getAdsMe() {
        return new ResponseEntity<>(adService.getAdsMe(), HttpStatus.OK);
    }

    /**
     * Update adImage
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ads was updated ",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                            schema = @Schema(implementation = byte[].class)
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
    @PatchMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('user_basic_access')")
    public ResponseEntity<byte[]> updateAdImage(@PathVariable Integer id,
                                                @RequestParam(value = "image") MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(Objects.requireNonNull(file.getContentType())));
        headers.setContentLength(file.getSize());
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(adService.updateAdImage(id, file));
    }

    @GetMapping(value = "/image/{id}")
    public ResponseEntity<byte[]> downloadAdImage(@PathVariable String id) {
        AdImage adImage = adService.getAdImageById(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(adImage.getType()));
        headers.setContentLength(adImage.getImage().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(adImage.getImage());
    }

//////////////////////////////////////////comments/////////////////////////////////

    /**
     * Get comment from ad
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Get comment from ads",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CommentsDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized User"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ads Not Found"
            )
    })
    @GetMapping("{id}/comments")
    @PreAuthorize("hasAuthority('user_basic_access')")
    public ResponseEntity<CommentsDTO> getAdsComments(@PathVariable Integer id) {
        return new ResponseEntity<>(adService.getAdComments(id), HttpStatus.OK);
    }

    /**
     * Create comment to ad
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Created comment to ads",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CommentsDTO.class)
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
    @PostMapping("{id}/comments")
    @PreAuthorize("hasAuthority('user_basic_access')")
    public ResponseEntity<CommentDTO> addAdsComment(@PathVariable Integer id,
                                                    @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        return new ResponseEntity<>(adService.createComment(id, createOrUpdateCommentDTO), HttpStatus.CREATED);
    }

    /**
     * Delete comment from ad
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Comment was deleted",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CommentsDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content"
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
                    description = "Not Found"
            )
    })
    @DeleteMapping("{adId}/comments/{commentId}")
    @PreAuthorize("hasAuthority('user_basic_access')")
    public ResponseEntity<CommentDTO> deleteAdsComment(@PathVariable int adId,
                                                       @PathVariable int commentId) {
        adService.deleteComment(adId, commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Update ads comment
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ads comment was updated ",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CommentDTO.class)
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
                    description = "Not Found"
            )
    })
    @PatchMapping("{adId}/comments/{commentId}")
    @PreAuthorize("hasAuthority('user_basic_access')")
    public ResponseEntity<CommentDTO> updateAdsComment(@PathVariable int adId,
                                                       @PathVariable int commentId,
                                                       @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO
    ) {
        return new ResponseEntity<>(adService.updateComment(adId, commentId, createOrUpdateCommentDTO), HttpStatus.OK);
    }
}