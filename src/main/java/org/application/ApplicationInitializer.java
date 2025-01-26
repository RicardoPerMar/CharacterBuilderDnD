package org.application;

import org.application.service.MenuService;
import org.application.utils.DataLoader;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ApplicationInitializer implements ApplicationRunner {

    private final DataLoader dataLoader;

    private final MenuService menuService;

    public ApplicationInitializer(DataLoader dataLoader, MenuService menuService) {
        this.dataLoader = dataLoader;
        this.menuService = menuService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dataLoader.loadAllData();
        menuService.showMenu();
    }
}
