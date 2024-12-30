package org.race.service;

import org.race.model.Race;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Service;
import org.stat.model.Stat;
import org.utils.JsonReader;

import java.io.IOException;
import java.util.*;


@Service
public class RaceServiceImpl implements RaceService {

    public final Set<String> validRaces = new HashSet<>(Arrays.asList("Dragonborn", "Dwarf", "Elf", "Gnome", "Half-Elf", "Halfling", "Half-Orc", "Human", "Tiefling"));

    public void listRaces() throws IOException {
        try {
            List<Race> races = JsonReader.readJsonFile("src/main/resources/races/phb_races.json", Race.class);

            for (Race race : races) {
                System.out.println("race: " + race.getName());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Stat[] getStatsByName(String selectedRace) {
        return Objects.requireNonNull(checkRace(selectedRace)).getStats();
    }

    public Race getAllValuesByName(String selectedRace){
        return checkRace(selectedRace);
    }

    private Race checkRace(String selectedRace){
        try {
            List<Race> races = JsonReader.readJsonFile("src/main/resources/races/phb_races.json", Race.class);
            Optional<Race> r = races.stream()
                    .filter(race -> race.getName().equalsIgnoreCase(selectedRace))
                    .findFirst();

            if(validRaces.contains(selectedRace) && r.isPresent()){
                return new Race(r.get().getLanguages(), r.get().getName(), r.get().getSpeed(), r.get().getStats(), r.get().getTraits());
            }
            return null;
        } catch (IOException | JsonParseException e) {
            e.printStackTrace();
            return null; // Devuelve null en caso de error
        }
    }

    public Set<String> getValidRaces() {
        return validRaces;
    }
}
