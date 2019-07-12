package io.honeymon.sample.ssecurity.api;

import io.honeymon.sample.ssecurity.domain.User;
import io.honeymon.sample.ssecurity.domain.UserGroup;
import io.honeymon.sample.ssecurity.domain.UserGroupRepository;
import io.honeymon.sample.ssecurity.domain.dto.UserRequest;
import io.honeymon.sample.ssecurity.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author jiheon.kim on 2019-07-12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserApiControllerTest {

    @Autowired
    WebApplicationContext wac;
    @Autowired
    UserService userService;
    @Autowired
    UserGroupRepository userGroupRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    private MockMvc mockMvc;
    private User user;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).alwaysDo(print()).build();

        //given
        String username = "test@woowahan.com";
        String password = "password";
        user = userService.save(new UserRequest(username, password).toEntity(passwordEncoder));

        UserGroup group = new UserGroup("테스트");
        userGroupRepository.save(group);

        user.changeGroup(group);
        user.enable();
        userService.save(user);
    }

    @Test
    @WithMockUser(username = "superAdmin", password = "superAdminPw", roles = "SUPER_ADMIN")
    public void testDisable() throws Exception {
        //when
        mockMvc.perform(
                put("/users/{user}/disable", user.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "adminPw", roles = "ADMIN")
    public void testDisableOccurForbidden() {
        //when
        Assertions.assertThatThrownBy(() -> mockMvc.perform(
                put("/users/{user}/disable", user.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)))
                .isExactlyInstanceOf(NestedServletException.class);
    }
}
