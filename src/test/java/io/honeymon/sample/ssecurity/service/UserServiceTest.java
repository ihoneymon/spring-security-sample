package io.honeymon.sample.ssecurity.service;

import io.honeymon.sample.ssecurity.domain.User;
import io.honeymon.sample.ssecurity.domain.dto.UserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author jiheon.kim on 2019-07-12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void createUser() {
        //given
        String username = "test@woowahan.com";
        String password = "password";
        UserRequest userRequest = new UserRequest(username, password);

        //when
        User savedUser = userService.save(userRequest.toEntity(passwordEncoder));

        //then
        assertThat(savedUser.getUsername()).isEqualTo(username);
        assertThat(passwordEncoder.matches(password, savedUser.getPassword())).isTrue();
        assertThat(savedUser.isEnabled()).isFalse();
    }


}
