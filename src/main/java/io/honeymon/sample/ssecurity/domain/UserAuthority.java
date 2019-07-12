package io.honeymon.sample.ssecurity.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author jiheon.kim on 2019-07-12
 */
public enum UserAuthority implements GrantedAuthority {
    SUPER_ADMIN("최고관리자"),
    MANUAL_P_REQUEST("포인트 수동 적립요청"),
    MANUAL_P_APPROVAL("포인트 수동 적립승인"),
    USER("사용자");

    private String description;

    /**
     * 화면에 노출될 메뉴명에 따라 변경
     * @param description
     */
    UserAuthority(String description) {
        this.description = description;
    }


    @Override
    public String getAuthority() {
        return this.name();
    }

    public String getDescription() {
        return description;
    }
}
