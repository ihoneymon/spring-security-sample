package io.honeymon.sample.ssecurity.service;

import io.honeymon.sample.ssecurity.domain.UserGroup;
import io.honeymon.sample.ssecurity.domain.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jiheon.kim on 2019-07-12
 */
@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {
    private final UserGroupRepository userGroupRepository;

    public UserGroup save(UserGroup userGroup) {
        return userGroupRepository.save(userGroup);
    }

    public void delete(UserGroup userGroup) {
        userGroupRepository.delete(userGroup);
    }
}
