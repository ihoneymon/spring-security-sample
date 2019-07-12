package io.honeymon.sample.ssecurity.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.List;

/**
 * @author jiheon.kim on 2019-07-12
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    private String url;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "menu_authority", joinColumns = @JoinColumn(name = "menu_id"))
    @Column(name = "authority")
    private List<UserAuthority> authorities; // 메뉴와 관련된 권한



    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }



    public Menu changeName(String name) {
        Assert.hasText(name, "메뉴명은 필수입력값입니다.");

        this.name = name;

        return this;
    }

    public Menu changeUrl(String url) {
        Assert.hasText(url, "URL 은 필수입력값입니다.");

        this.url = url;

        return this;
    }
}