package org.classes.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import org.trait.Trait;

import java.util.Map;

@Component
public class Class {

    private String name;
    private String hd;
    private int level;
    private Map<String, String> proficiencies;
    private Trait[] traits;

    @JsonCreator
    public Class(@JsonProperty("hd")String hd,
                 @JsonProperty("level")int level,
                 @JsonProperty("name")String name,
                 @JsonProperty("proficiencies")Map<String, String> proficiencies,
                 @JsonProperty("traits")Trait[] traits) {
        this.hd = hd;
        this.level = level;
        this.name = name;
        this.proficiencies = proficiencies;
        this.traits = traits;
    }

    public String getHd() {
        return hd;
    }

    public void setHd(String hd) {
        this.hd = hd;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(Map<String, String> proficiencies) {
        this.proficiencies = proficiencies;
    }

    public Trait[] getTraits() {
        return traits;
    }

    public void setTraits(Trait[] traits) {
        this.traits = traits;
    }
}
