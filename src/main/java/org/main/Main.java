package org.main;

import org.character.config.CharacterConfig;
import org.character.service.impl.CharacterCreatorServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CharacterConfig.class);

        CharacterCreatorServiceImpl characterCreatorServiceImpl = context.getBean(CharacterCreatorServiceImpl.class);
        characterCreatorServiceImpl.createCharacter();
    }
}