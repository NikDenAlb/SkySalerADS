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
import selfConstructed.SkySalerADS.dto.NewPasswordDTO;
import selfConstructed.SkySalerADS.dto.UserDTO;
import selfConstructed.SkySalerADS.model.Avatar;
import selfConstructed.SkySalerADS.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Get users
     * Use method of service {@link UserService#getUserMe()}
     *
     * @return Founded user
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User was gotten ",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized User"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User Not Found"
            )
    })
    @GetMapping("/me")
    @PreAuthorize("hasAuthority('user_basic_access')")
    public ResponseEntity<UserDTO> getUserMe() {
        return new ResponseEntity<>(userService.getUserMe(), HttpStatus.OK);
    }

    /**
     * Change password for user
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User's password was changed",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = NewPasswordDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized User"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden Action"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User Not Found"
            )
    })
    @PostMapping("/set_password")
    @PreAuthorize("hasAuthority('user_basic_access')")
    public ResponseEntity<NewPasswordDTO> setPassword(@RequestBody NewPasswordDTO newPassword) {
        return new ResponseEntity<>(userService.setPassword(newPassword), HttpStatus.OK);
    }

    /**
     * Update user
     *
     * @return Updated user
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User was updated ",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized User"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden Action"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User Not Found"
            )
    })
    @PatchMapping("/me")
    @PreAuthorize("hasAuthority('user_basic_access')")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUser(userDTO), HttpStatus.OK);
    }

    /**
     * Update user Avatar
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User Avatar was updated ",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized User"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden Action"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User Not Found"
            )
    })
    @PatchMapping(value = "me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('user_basic_access')")
    public ResponseEntity<String> updateUserAvatar(@RequestParam(value = "image") MultipartFile file) {
        userService.updateUserAvatar(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/avatar/{id}")
    @PreAuthorize("hasAuthority('user_basic_access')")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable String id) {
        Optional<Avatar> avatarIn = userService.getAvatarByUserId(userService.getUser(Integer.parseInt(id)));
         if (!avatarIn.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Avatar avatar = avatarIn.get();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }
}