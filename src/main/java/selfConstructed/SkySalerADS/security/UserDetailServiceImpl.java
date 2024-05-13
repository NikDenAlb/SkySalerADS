package selfConstructed.SkySalerADS.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import selfConstructed.SkySalerADS.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return SecurityUser.fromUser(userRepository.findUsersByUsernameIgnoreCase(username)
                .orElseThrow(RuntimeException::new));
    }
}
