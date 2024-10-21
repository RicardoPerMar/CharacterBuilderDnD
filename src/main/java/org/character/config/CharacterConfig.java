package org.character.config;

import org.character.model.Stat;
import org.character.service.impl.CharacterCreatorServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CharacterConfig {

    @Bean
    public Stat STR() {
        return new Stat("STR", 10);
    }

    @Bean
    public Stat DEX() {
        return new Stat("DEX", 10);
    }

    @Bean
    public Stat CON() {
        return new Stat("CON", 10);
    }

    @Bean
    public Stat INT() {
        return new Stat("INT", 10);
    }

    @Bean
    public Stat WIS() {
        return new Stat("WIS", 10);
    }

    @Bean
    public Stat CHA() {
        return new Stat("CHA", 10);
    }

    @Bean
    public CharacterCreatorServiceImpl characterCreator(
            @Qualifier("STR") Stat STR,
            @Qualifier("DEX") Stat DEX,
            @Qualifier("CON") Stat CON,
            @Qualifier("INT") Stat INT,
            @Qualifier("WIS") Stat WIS,
            @Qualifier("CHA") Stat CHA) {
        return new CharacterCreatorServiceImpl(new Stat[]{STR, DEX, CON, INT, WIS, CHA});
    }
}
