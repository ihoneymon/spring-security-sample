package io.honeymon.sample.ssecurity.domain.dto;

import io.honeymon.sample.ssecurity.domain.User;
import io.honeymon.sample.ssecurity.domain.UserAuthority;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiheon.kim on 2019-07-12
 */
@Getter
public class UserDto {
    private String username;
    private List<String> meunUrls;
    private List<UserAuthority> authorities;

    public UserDto(String username, List<String> menuUrls, List<UserAuthority> authorities) {
        this.username = username;
        this.meunUrls = menuUrls;
        this.authorities = authorities;
    }

    public static UserDto of(User user) {
        return new UserDto(user.getUsername(), user.getGroup().getMenuUrls(), new ArrayList<>(user.getAuthorities()));
    }
}
