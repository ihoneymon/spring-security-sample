package io.honeymon.sample.ssecurity.api;

import io.honeymon.sample.ssecurity.domain.User;
import io.honeymon.sample.ssecurity.domain.dto.UserDto;
import io.honeymon.sample.ssecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiheon.kim on 2019-07-12
 */
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @Secured("ROLE_SUPER_ADMIN")
    @PutMapping("/{user}/disable")
    public  UserDto disable(@PathVariable User user) {
        return userService.disable(user);
    }
}
