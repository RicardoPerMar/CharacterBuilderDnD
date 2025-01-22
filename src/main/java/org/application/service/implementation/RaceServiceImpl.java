package org.application.service.implementation;

import org.application.repository.RaceRepository;
import org.application.service.RaceService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;


@Service
public class RaceServiceImpl implements RaceService {

    private final RaceRepository raceRepository;

    private final Set<String> validRaces = new HashSet<>(Arrays.asList("Dragonborn", "Dwarf", "Elf", "Gnome", "Half-Elf", "Halfling", "Half-Orc", "Human", "Tiefling"));

    public RaceServiceImpl(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    public void readAndSaveRaces() throws IOException {

    }
/*
    @Override
    public void readAndSaveRaces() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<RaceDTO> races = objectMapper.readValue(new File("src/main/resources/races/phb_races.json"), new TypeReference<List<RaceDTO>>(){});

        // Convertir el DTO a entidades y guardar en la base de datos
        for (RaceDTO raceDTO : races){
            Race race = new Race();
            race.setName(raceDTO.getName());
            race.setSpeed(raceDTO.getSpeed());
            race.setLanguages(raceDTO.getLanguages());

            List<RaceStat> raceStats = new ArrayList<>();
            for (StatDTO statDTO : raceDTO.getStats()){
                RaceStat raceStat = new RaceStat();
                raceStat.setName(statDTO.getName());
                raceStat.setValue(statDTO.getValue());
                raceStat.setRace(race);
                raceStats.add(raceStat);
            }
            race.setStat(raceStats);

            List<Trait> traits = new ArrayList<>();
            for (TraitDTO traitDTO : raceDTO.getTraits()){
                Trait trait = new Trait();
                trait.setName(traitDTO.getName());
                trait.setDescription(traitDTO.getDescription());
                trait.setRace(race);
                traits.add(trait);
            }
            race.setTraits(traits);

            raceRepository.save(race);
        }

    }

    public Set<String> getValidRaces() {
        return validRaces;
    }*/
}
