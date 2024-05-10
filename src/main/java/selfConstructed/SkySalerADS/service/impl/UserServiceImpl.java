package selfConstructed.SkySalerADS.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import selfConstructed.SkySalerADS.dto.RegisterDTO;
import selfConstructed.SkySalerADS.dto.UserDTO;
import selfConstructed.SkySalerADS.exception.UserAlreadyHereException;
import selfConstructed.SkySalerADS.exception.UserNotFoundException;
import selfConstructed.SkySalerADS.mapper.UserEntity;
import selfConstructed.SkySalerADS.model.User;
import selfConstructed.SkySalerADS.repository.UserRepository;
import selfConstructed.SkySalerADS.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserEntity userMapper;

    @Override
    public UserDTO createUser(RegisterDTO registerDTO) {
        log.info("Trying to create and save new user");
        User newUser = userRepository.save(userMapper.toModel(registerDTO));
        log.info("The user with id = {} was saved ", newUser.getId());
        return userMapper.toDTO(newUser);
    }

    @Override
    public boolean checkUserForRegisterOk(String login) {
        if (userRepository.findUsersByUsernameIgnoreCase(login).isPresent()) {
            throw new UserAlreadyHereException("User is found");
        }
        return true;
    }

    @Override
    public boolean checkUserExists(String login) {
        log.info("Try to check whether the login is used or not");
        userRepository.findUsersByUsernameIgnoreCase(login)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return true;
    }

    @Transactional
    @Override
    public UserDTO getUserMe() {
        return userMapper.toDTO(getUserFromAuthentication());
    }

    @Transactional
    @Override
    public User getUserFromAuthentication() {
        log.info("Try to get user from authentication");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return getUser(authentication.getName());
    }

    @Transactional
    @Override
    public User getUser(String login) {
        return userRepository.findUsersByUsernameIgnoreCase(login)
                .orElseThrow(() -> new UserNotFoundException("No User with login " + login + " in DB"));
    }

}
