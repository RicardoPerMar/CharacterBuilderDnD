package org.application.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.application.model.Race;
import org.application.model.RaceStat;
import org.application.model.Trait;
import org.application.repository.RaceRepository;
import org.application.repository.RaceStatRepository;
import org.application.repository.TraitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.application.model.Character;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class DataLoader {

    @Value("${file.character.path}")
    private String CHARACTER_JSON_PATH;

    @Value("${file.race.path}")
    private String RACE_JSON_PATH;

    private final RaceRepository raceRepository;
    private final RaceStatRepository raceStatRepository;
    private final ObjectMapper objectMapper;
    private final TraitRepository traitRepository;

    @Autowired
    private JsonFileService jsonFileService;

    public DataLoader(ObjectMapper objectMapper, RaceRepository raceRepository, RaceStatRepository raceStatRepository,
                      TraitRepository traitRepository) {
        this.objectMapper = objectMapper;
        this.raceRepository = raceRepository;
        this.raceStatRepository = raceStatRepository;
        this.traitRepository = traitRepository;
    }

    public void loadAllData() throws IOException {
        loadRaces();
    }

    private void loadRaces() throws IOException {
        File file = new File(RACE_JSON_PATH);
        //Race[] races = objectMapper.readValue(file, Race[].class);
        Race[] races = jsonFileService.readJsonFile(RACE_JSON_PATH, new TypeReference<List<Race>>() {}).toArray(new Race[0]);
        for (Race race : races) {
            if (!raceRepository.existsByName(race.getName())) {
                raceRepository.save(race);
                for (RaceStat stat : race.getRaceStat()) {
                    stat.setRace(race);
                    raceStatRepository.save(stat);
                }
                for (Trait trait : race.getTraits()) {
                    trait.setRace(race);
                    traitRepository.save(trait);
                }
            }
        }
    }

    public List<Character> loadCharactersJSON() throws IOException {
        /*File file = new File(CHARACTER_JSON_PATH);
        if (!file.exists()) {
            // TODO Create character.json when program is being executed not here
            file.createNewFile();
            return new java.util.ArrayList<>();
        }
        return objectMapper.readValue(file, new TypeReference<List<Character>>() {
        });*/
        return jsonFileService.readJsonFile(CHARACTER_JSON_PATH, new TypeReference<List<Character>>(){});
    }

    public void saveCharactersJSON(List<Character> characters) throws IOException {
        jsonFileService.writeJsonFile(CHARACTER_JSON_PATH, characters);
    }
}