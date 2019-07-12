package io.honeymon.sample.ssecurity.api;

import io.honeymon.sample.ssecurity.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.acl.Group;

/**
 * @author jiheon.kim on 2019-07-12
 */
@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupApiController {
    private final GroupService groupService;
}
