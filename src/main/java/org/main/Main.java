package org.main;

import org.character.config.CharacterConfig;
import org.character.service.CharacterCreatorServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CharacterConfig.class)
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);

        CharacterCreatorServiceImpl characterCreatorServiceImpl = context.getBean(CharacterCreatorServiceImpl.class);
        characterCreatorServiceImpl.menu();
    }
}