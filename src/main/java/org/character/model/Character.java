package org.character.model;

import org.springframework.stereotype.Component;
import org.stat.model.Stat;
import org.trait.Trait;

import java.util.List;

@Component
public class Character {

    private String name;

    private String race;

    private String characterClass;

    private Stat[] stats;

    private int speed;

    private List<String> languages;

    private Trait[] traits;

    public Character(String name, String race, String characterClass, Stat[] stats) {
        this.name = name;
        this.race = race;
        this.characterClass = characterClass;
        this.stats = stats;
    }

    public Character(String name, String race, String characterClass, Stat[] stats, int speed, List<String> languages, Trait[] traits) {
        this.name = name;
        this.race = race;
        this.characterClass = characterClass;
        this.stats = stats;
        this.speed = speed;
        this.languages = languages;
        this.traits = traits;
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

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Stat[] getStats() {
        return stats;
    }

    public void setStats(Stat[] stats) {
        this.stats = stats;
    }

    public Trait[] getTraits() {
        return traits;
    }

    public void setTraits(Trait[] traits) {
        this.traits = traits;
    }
}
