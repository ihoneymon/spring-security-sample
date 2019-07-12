package io.honeymon.sample.ssecurity.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiheon.kim on 2019-07-12
 */
@Getter
@Entity
@NoArgsConstructor
public class UserGroup {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany
    @JoinTable(name = "group_menu", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "menu")
    private List<Menu> menus;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER) // 스프링 시큐리티에서 권한(Authority)은 Eager 로 선언되어 있어야 한다.
    @CollectionTable(name = "user_group_authority", joinColumns = @JoinColumn(name = "user_group_id"))
    @Column(name = "authority")
    private List<UserAuthority> authorities;



    public UserGroup(String name) {
        this.name = name;
        this.menus = new ArrayList<>();
        this.authorities = new ArrayList<>();
    }



    public UserGroup changeMenus(List<Menu> menus) {
        //TODO 빈값도 허용해야할까??
        this.menus = menus;

        return this;
    }

    public UserGroup changeAuthorities(List<UserAuthority> authorities) {
        //TODO 빈값도 허용해야할까??

        this.authorities = authorities;

        return this;
    }

    public List<String> getMenuUrls() {
        return menus.stream().map(Menu::getUrl).collect(Collectors.toList());
    }
}
