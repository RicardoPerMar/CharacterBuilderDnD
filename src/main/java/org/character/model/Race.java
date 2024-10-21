package org.character.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Race {

    String race;
    Stat[] stats;
    Trait[] traits;
    List<String> languages;
    int speed;

    public Race(List<String> languages, String race, int speed, Stat[] stats, Trait[] traits) {
        this.languages = languages;
        this.race = race;
        this.speed = speed;
        this.stats = stats;
        this.traits = traits;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
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
}
