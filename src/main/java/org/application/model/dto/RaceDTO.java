package org.application.model.dto;

import java.util.List;

public class RaceDTO {
    private String name;
    private int speed;
    private List<RaceStatDTO> stats;
    private List<TraitDTO> traits;
    private List<String> languages;

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public List<RaceStatDTO> getRaceStats() {
        return stats;
    }

    public void setRaceStats(List<RaceStatDTO> stats) {
        this.stats = stats;
    }

    public List<TraitDTO> getTraits() {
        return traits;
    }

    public void setTraits(List<TraitDTO> traits) {
        this.traits = traits;
    }
}
