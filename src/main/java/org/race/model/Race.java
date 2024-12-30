package org.race.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import org.stat.model.Stat;
import org.trait.Trait;

import java.util.List;

@Component
public class Race {

    String name;
    Stat[] stats;
    Trait[] traits;
    List<String> languages;
    int speed;

    @JsonCreator
    public Race(@JsonProperty("languages") List<String> languages, @JsonProperty("name") String name, @JsonProperty("speed") int speed, @JsonProperty("stats") Stat[] stats, @JsonProperty("traits") Trait[] traits) {
        this.languages = languages;
        this.name = name;
        this.speed = speed;
        this.stats = stats;
        this.traits = traits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
