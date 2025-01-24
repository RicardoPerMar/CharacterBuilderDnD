package org.application.service.implementation;

import org.application.model.Race;
import org.application.model.RaceStat;
import org.application.model.Trait;
import org.application.repository.RaceRepository;
import org.application.service.RaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RaceServiceImpl implements RaceService {

    private final RaceRepository raceRepository;

    // private final Set<String> validRaces = new HashSet<>(Arrays.asList("Dragonborn", "Dwarf", "Elf", "Gnome", "Half-Elf", "Halfling", "Half-Orc", "Human", "Tiefling"));

    public RaceServiceImpl(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Transactional
    public void showAllRaces(){
        List<Race> races = raceRepository.findAll();
        for(Race race : races) {
            System.out.println("Race: " + race.getName());
            System.out.println("\nStat:");
            for (RaceStat raceStat : race.getRaceStat()){
                System.out.println("\t" + raceStat.getName() + ": " + raceStat.getValue());
            }
            System.out.println("\nTraits: ");
            for (Trait trait : race.getTraits()){
                System.out.println("\t" + trait.getName() + ": " + trait.getDescription());
            }
            System.out.println();
        }
        System.out.println("=".repeat(200) + "\n");
    }
}
