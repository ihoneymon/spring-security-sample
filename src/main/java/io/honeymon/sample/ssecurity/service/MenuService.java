package io.honeymon.sample.ssecurity.service;

import io.honeymon.sample.ssecurity.domain.Menu;
import io.honeymon.sample.ssecurity.domain.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jiheon.kim on 2019-07-12
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    public void delete(Menu menu) {
        menuRepository.delete(menu);
    }
}
