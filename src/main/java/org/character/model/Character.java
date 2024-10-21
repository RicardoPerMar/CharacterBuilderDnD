package org.character.model;

import org.springframework.stereotype.Component;

@Component
public class Character {

    private String name;
    private String race;
    private String characterClass;
    private Stat[] stats;

    public Character(String name, String race, String characterClass, Stat[] stats) {
        this.name = name;
        this.race = race;
        this.characterClass = characterClass;
        this.stats = stats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public Stat[] getAttributes() {
        return stats;
    }

    public void setAttributes(Stat[] stats) {
        this.stats = stats;
    }

}
