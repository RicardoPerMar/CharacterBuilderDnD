package org.application;

import org.application.utils.DataLoader;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ApplicationInitializer implements ApplicationRunner {

    private final DataLoader dataLoader;

    public ApplicationInitializer(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dataLoader.loadAllData();
    }
}
