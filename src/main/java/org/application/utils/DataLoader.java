package org.application.utils;

//import org.application.strategies.DataLoaderStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.application.model.Race;
import org.application.model.RaceStat;
import org.application.model.Trait;
import org.application.model.dto.RaceDTO;
import org.application.repository.RaceRepository;
import org.application.repository.RaceStatRepository;
import org.application.repository.TraitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DataLoader{

    private final RaceRepository raceRepository;
    private final RaceStatRepository raceStatRepository;
    private final ObjectMapper objectMapper;
    private final TraitRepository traitRepository;

    public DataLoader(ObjectMapper objectMapper, RaceRepository raceRepository, RaceStatRepository raceStatRepository,
                      TraitRepository traitRepository) {
        this.objectMapper = objectMapper;
        this.raceRepository = raceRepository;
        this.raceStatRepository  = raceStatRepository;
        this.traitRepository = traitRepository;
    }

    @PostConstruct
    public void init() throws IOException {
        loadAllData();
    }

    public void loadAllData() throws IOException {
        loadRaces();
    }

    private void loadRaces() throws IOException {
        File file = new File("src/main/resources/races/phb_races.json");
        Race[] races = objectMapper.readValue(file, Race[].class);
        for (Race race : races){
            if (!raceRepository.existsByName(race.getName())){
                raceRepository.save(race);
                for (RaceStat stat : race.getRaceStat()) {
                    stat.setRace(race);
                    raceStatRepository.save(stat);
                }
                for (Trait trait : race.getTraits()) {
                    trait.setRace(race);
                    traitRepository.save(trait);
                }
                System.out.println("Saved race: " + race.getName());
            } else {
                System.out.println("Race already exists: " + race.getName());
            }


        }
    }
}
