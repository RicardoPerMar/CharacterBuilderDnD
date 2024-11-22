package org.race.service;

import org.race.model.Race;
import org.stat.model.Stat;

import java.io.IOException;
import java.util.Set;

public interface RaceService {
    void listRaces() throws IOException;
    Stat[] getStatsByName(String selectedRace);
    Set<String> getValidRaces();
    Race getAllValuesByName(String selectedRace);
}
