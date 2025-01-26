package org.application.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.application.model.Character;
import org.application.model.Race;
import org.application.repository.CharacterRepository;
import org.application.repository.RaceRepository;
import org.application.service.CharacterService;
import org.application.utils.DataLoader;
import org.application.utils.InputService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final DataLoader dataLoader;
    private final RaceRepository raceRepository;
    private final CharacterRepository characterRepository;
    private final InputService inputService;

    private final Scanner scanner = new Scanner(System.in);

    public CharacterServiceImpl(CharacterRepository characterRepository, RaceRepository raceRepository, DataLoader dataLoader,
                                InputService inputService) {
        this.characterRepository = characterRepository;
        this.raceRepository = raceRepository;
        this.dataLoader = dataLoader;
        this.inputService = inputService;
    }

    public void createCharacter() throws IOException {
        List<Character> characters = dataLoader.loadCharactersJSON();
        Character character = new Character();

        System.out.print("Name: ");
        character.setName(scanner.next());

        System.out.print("Level: ");
        character.setLevel(scanner.nextInt());

        System.out.print("Race: ");
        String raceName = scanner.next();
        Race race = raceRepository.findByName(raceName);
        character.setRace(race);
        System.out.println(character.getRace().getName());

        characterRepository.save(character);
        characters.add(character);
        dataLoader.saveCharactersJSON(characters);
    }
}
