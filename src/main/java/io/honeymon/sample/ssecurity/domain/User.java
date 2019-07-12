package io.honeymon.sample.ssecurity.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

/**
 * @author jiheon.kim on 2019-07-12
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails, Persistable<Long> {
    private final static int LIMIT_LOGINED_DAYS = 90;

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @OneToOne
    private UserGroup group;


    private Boolean enabled;

    private LocalDateTime loggedIn;

    @CreatedDate
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime modified;
    @LastModifiedBy
    private User modifiedBy;


    public User(String username, String encryptedPassword) {
        this.username = username;
        this.password = encryptedPassword;
        this.enabled = false;
    }


    @Override
    public boolean isNew() {
        return Objects.isNull(this.id);
    }

    @Override
    public Collection<UserAuthority> getAuthorities() {
        return getGroup().getAuthorities();
    }

    /**
     * 등록된 계정에 대한 유효기간(언제까지 사용하라)는 기한을 둘 것인가?
     * 생성일자 기준?
     * 특정일자 지정?
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /*
     * 비밀번호 변경기한을 둘 것인가?? 변경기한을 둘 것이면 비밀번호 변경정책도 필요함
     * 비밀번호 변경일을 저장할 것인가?
     */

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return true: LIMIT_LOGINED_DAYS 이 경과하지 않은 경우 / false: LIMIT_LOGINED_DAYS 초과
     */
    @Override
    public boolean isAccountNonLocked() {
        return loggedIn.isAfter(LocalDateTime.now().minusDays(LIMIT_LOGINED_DAYS));
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }


    public User login() {
        this.loggedIn = LocalDateTime.now();

        return this;
    }

    public User changeGroup(UserGroup group) {
        Assert.notNull(group, "그룹은 필수항목입니다.");

        this.group = group;

        return this;
    }

    public User enable() {
        this.enabled = true;
        this.loggedIn = LocalDateTime.now();

        return this;
    }

    public User disable() {
        this.enabled = false;
        this.loggedIn = LocalDateTime.now();
        return this;
    }
}
