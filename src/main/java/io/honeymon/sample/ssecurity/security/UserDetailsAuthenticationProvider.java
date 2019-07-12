package io.honeymon.sample.ssecurity.security;

import io.honeymon.sample.ssecurity.domain.User;
import io.honeymon.sample.ssecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author jiheon.kim on 2019-07-12
 */
@Slf4j
@RequiredArgsConstructor
public class UserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException("No Credentials");
        }

        if (!passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
            throw new BadCredentialsException("잘못된 인증요청입니다.");
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        try {
            User user = userService.loadUserByUsername(username);

            //TODO 필요한 처리로직 등록

            return user;
        } catch (IllegalArgumentException iae) {
            if(hideUserNotFoundExceptions) {
                throw new BadCredentialsException("잘못된 인증요청입니다.");
            }

            throw new UsernameNotFoundException(iae.getMessage());
        }
    }
}
