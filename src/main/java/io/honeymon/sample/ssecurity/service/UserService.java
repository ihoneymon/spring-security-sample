package io.honeymon.sample.ssecurity.service;

import io.honeymon.sample.ssecurity.domain.User;
import io.honeymon.sample.ssecurity.domain.UserRepository;
import io.honeymon.sample.ssecurity.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jiheon.kim on 2019-07-12
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("사용자(사용자명: %s)를 찾지 못했습니다.", username)));

        return user;
    }

    public UserDto disable(User user) {
        user.disable();
        return UserDto.of(user);
    }
}
