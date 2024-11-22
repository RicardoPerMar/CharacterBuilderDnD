package org.character.service;

import org.race.model.Race;
import org.race.service.RaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stat.model.Stat;
import org.character.model.Character;
import org.stat.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trait.model.Trait;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class CharacterCreatorServiceImpl implements CharacterCreatorService {

    private static final Logger log = LoggerFactory.getLogger(CharacterCreatorServiceImpl.class);
    private final Stat[] stats;
    private final Scanner scanner = new Scanner(System.in);

    private final StatService statService;
    private final RaceService raceService;

    @Autowired
    public CharacterCreatorServiceImpl(Stat[] stats, StatService statService, RaceService raceService) {
        this.stats = stats;
        this.statService = statService;
        this.raceService = raceService;
    }

    public void menu() {
        String option;
        System.out.println("\n" + "-".repeat(60));
        System.out.println("WELCOME TO \"D&D BUILDER\"");
        System.out.println("-".repeat(60) + "\n");
        do {
            System.out.print("""
                    Select your option:
                    
                    1. Create character
                    2. Information
                    3. Exit
                    
                    """);
            System.out.print("Option: ");
            option = scanner.nextLine();
            System.out.println();
            switch (option) {
                case "1":
                    createCharacter();
                    break;
                case "2":
                    try {
                        raceService.listRaces();
                    } catch (Exception e) {
                        log.error("e: ", e);
                    }
                    break;
                default:
                    System.out.println("\n" + "-".repeat(60));
                    System.out.println("Choose any of the following options");
                    System.out.println("-".repeat(60) + "\n");
            }
        } while (!option.equals("3"));
    }

    @Override
    public void createCharacter() {
        System.out.print("Character name: ");
        String name = scanner.nextLine();
        String race = "";
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Race: ");
            race = scanner.nextLine().trim();

            if (race.contains("-")){
                int dash = race.indexOf("-");
                race = race.substring(0, 1).toUpperCase() + race.substring(1, dash + 1).toLowerCase() +
                        race.substring(dash + 1, dash + 2).toUpperCase() + race.substring(dash + 2).toLowerCase();
            } else {
                race = race.substring(0, 1).toUpperCase() + race.substring(1).toLowerCase();
            }
            if (raceService.getValidRaces().contains(race)) {
                validInput = true;
            } else {
                System.out.println("""
                        Please introduce any of the following races:
                         - Dragonborn  - Dwarf      - Elf\
                        \s
                         - Gnome       - Half-Elf   - Halfling\
                        \s
                         - Half-Orc    - Human      - Tiefling""");
            }
        }
        Race raceValues = raceService.getAllValuesByName(race);
        System.out.print("Class: ");
        String characterClass = scanner.nextLine();

        Character character = new Character(name, race, characterClass, stats, raceValues.getSpeed(), raceValues.getLanguages(), raceValues.getTraits());
        System.out.print("Atributos...\nTirada de dados (y/n):");
        String opAtt = scanner.nextLine();

        if ("y".equalsIgnoreCase(opAtt)) {
            statService.diceRoll(character.getAttributes());
        } else {
            statService.standardStats(character.getAttributes());
        }
        statService.setRaceStats(stats, race);
        showCharacter(character);
    }

    private void showCharacter(Character character) {
        System.out.println("\n" + "-".repeat(60));
        StringBuilder sb = new StringBuilder("""
                Character name: %s\t\t\tSpeed: %d
                Race: %s
                Class: %s
                
                STR\t\tDEX\t\tCON\t\tINT\t\tWIS\t\tCHA
                """.formatted(
                character.getName(),
                character.getSpeed(),
                character.getRace(),
                character.getCharacterClass()
        ));

        // Añadir los valores dinámicos de los atributos.
        for (var attribute : character.getAttributes()) {
            sb.append(attribute.getValue()).append("\t\t");
        }

        // Añadir los idiomas.
        sb.append("\n\nLanguages: ");
        String languages = String.join(", ", character.getLanguages());
        sb.append(languages);

        sb.append("\n\nTraits: ");

        Iterator<Trait> iterator = Arrays.stream(character.getTraits()).iterator();
        while (iterator.hasNext()){
            Trait trait = iterator.next();
            sb.append(trait.getName());
            if (iterator.hasNext()){
                sb.append(", ");
            }
        }

        System.out.println(sb);
        System.out.println("-".repeat(60) + "\n");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
