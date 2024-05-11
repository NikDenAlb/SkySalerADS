package selfConstructed.SkySalerADS.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import selfConstructed.SkySalerADS.dto.NewPasswordDTO;
import selfConstructed.SkySalerADS.dto.RegisterDTO;
import selfConstructed.SkySalerADS.dto.UserDTO;
import selfConstructed.SkySalerADS.exception.*;
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

    @Transactional
    @Override
    public NewPasswordDTO setPassword(NewPasswordDTO newPasswordDTO) {
        log.info("start setting new password");
        if (newPasswordDTO.getCurrentPassword().equals(newPasswordDTO.getNewPassword())) {
            throw new SamePasswordException("Новый и старый пароли совпадают");
        }
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User user = getUserFromAuthentication();
        if (!encoder.matches(newPasswordDTO.getCurrentPassword(), user.getPassword())) {
            log.debug("пароли не совпадают");
            throw new WrongOldPasswordException("Некорректный текущий пароль");
        }
        String newPass = encoder.encode(newPasswordDTO.getNewPassword());
        user.setPassword(newPass);
        User out = userRepository.save(user);
        log.info("The user with login = {} was updated ", out.getUsername());
        return newPasswordDTO;
    }

    @Transactional
    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        log.info("updating user with username = {}", userDTO.getUsername());
        if (userDTO.getFirstName() == null || userDTO.getLastName() == null || userDTO.getPhone() == null) {
            throw new NullNewDataException("Все поля должны быть заполнены");
        }
        User user = getUserFromAuthentication();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhone(userDTO.getPhone());
        User out = userRepository.save(user);
        log.info("The userDTO with id = {} is updated ", out.getId());
        return userMapper.toDTO(out);
    }
}
