package io.honeymon.sample.ssecurity.domain.dto;

import io.honeymon.sample.ssecurity.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author jiheon.kim on 2019-07-12
 */
@Getter
@Setter
public class UserRequest {
    private String username;
    private String password;

    public UserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public User toEntity(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(this.password));
    }
}
