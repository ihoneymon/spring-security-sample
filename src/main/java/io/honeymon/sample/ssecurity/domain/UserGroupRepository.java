package io.honeymon.sample.ssecurity.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jiheon.kim on 2019-07-12
 */
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
}
