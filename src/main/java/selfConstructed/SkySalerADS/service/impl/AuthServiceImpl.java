package selfConstructed.SkySalerADS.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import selfConstructed.SkySalerADS.dto.RegisterDTO;
import selfConstructed.SkySalerADS.model.Role;
import selfConstructed.SkySalerADS.service.AuthService;
import selfConstructed.SkySalerADS.service.UserService;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager manager;
    private final PasswordEncoder encoder;
    private final UserService userService;

    @Transactional
    @Override
    public boolean login(String login, String password) {
        log.info("try to log in to the user's system");
        userService.checkUserExists(login);

        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication.isAuthenticated();
    }

    @Override
    public boolean register(RegisterDTO registerDTO, Role role) {
        log.info("user registration");
        if (userService.checkUserForRegisterOk(registerDTO.getUsername())) {
            registerDTO.setRole(role);
            registerDTO.setPassword(encoder.encode(registerDTO.getPassword()));
            userService.createUser(registerDTO);
        }
        return true;
    }
}
