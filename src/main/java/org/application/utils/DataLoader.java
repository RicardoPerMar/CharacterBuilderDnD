package org.application.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import org.application.model.*;
import org.application.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DataLoader {

    @Value("${file.character.path}")
    private String CHARACTER_JSON_PATH;

    @Value("${file.race.path}")
    private String RACE_JSON_PATH;

    @Value("${class.paths}")
    private String CLASS_JSON_PATH;

    private final RaceRepository raceRepository;
    private final RaceStatRepository raceStatRepository;
    private final TraitRepository traitRepository;
    private final CharacterClassRepository characterClassRepository;
    private final LevelRepository levelRepository;
    private final ProficiencyRepository proficiencyRepository;

    @Autowired
    private JsonFileService jsonFileService;

    public DataLoader(RaceRepository raceRepository, RaceStatRepository raceStatRepository,
                      TraitRepository traitRepository, CharacterClassRepository characterClassRepository, CharacterClassRepository characterClassRepository1,
                      LevelRepository levelRepository, ProficiencyRepository proficiencyRepository) {
        this.raceRepository = raceRepository;
        this.raceStatRepository = raceStatRepository;
        this.traitRepository = traitRepository;
        this.characterClassRepository = characterClassRepository1;
        this.levelRepository = levelRepository;
        this.proficiencyRepository = proficiencyRepository;
    }

    public void loadAllData() throws IOException {
        loadRaces();
        loadCharacterClasses();
    }

    private void loadRaces() throws IOException {
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

    private void loadCharacterClasses() throws IOException {
        String[] paths = CLASS_JSON_PATH.split(",");

        for (String path : paths) {
            CharacterClass[] characterClasses = jsonFileService.readJsonFile(path, new TypeReference<List<CharacterClass>>() {
            }).toArray(new CharacterClass[0]);

            for (CharacterClass characterClass : characterClasses) {
                if (!characterClassRepository.existsByName(characterClass.getName())) {
                    characterClassRepository.save(characterClass);

                    for (Proficiency proficiency : characterClass.getProficiencies()) {
                        proficiency.setCharacterClass(characterClass);
                        proficiency.setProficiencyType(proficiency.getProficiencyType());
                        proficiency.setProficiencyDescription(proficiency.getProficiencyDescription());
                        proficiencyRepository.save(proficiency);
                    }

                    for (Level level : characterClass.getLevel()) {
                        level.setCharacterClass(characterClass);
                        level.setLevel(level.getLevel());
                        level.setFeatures(level.getFeatures());
                        levelRepository.save(level);
                    }
                }
            }
        }
    }

    /* TODO: Check if this is necessary
    public List<Character> loadCharactersJSON() throws IOException {
        // TODO Create character.json when program is being executed not here
        return jsonFileService.readJsonFile(CHARACTER_JSON_PATH, new TypeReference<List<Character>>(){});
    }

    public void saveCharactersJSON(List<Character> characters) throws IOException {
        jsonFileService.writeJsonFile(CHARACTER_JSON_PATH, characters);
    }
    */

}