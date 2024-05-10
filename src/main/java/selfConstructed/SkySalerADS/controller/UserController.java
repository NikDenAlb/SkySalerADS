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
import selfConstructed.SkySalerADS.dto.UserDTO;
import selfConstructed.SkySalerADS.service.UserService;

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

}