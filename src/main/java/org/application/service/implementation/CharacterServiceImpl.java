package org.application.service.implementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.application.model.Character;
import org.application.model.Race;
import org.application.model.dto.CharacterDTO;
import org.application.repository.CharacterRepository;
import org.application.repository.RaceRepository;
import org.application.service.CharacterService;
import org.application.utils.DataLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class CharacterServiceImpl implements CharacterService {

    @PersistenceContext
    private EntityManager entityManager;
    private final DataLoader dataLoader;
    private final RaceRepository raceRepository;
    private final CharacterRepository characterRepository;

    private final Scanner scanner = new Scanner(System.in);

    public CharacterServiceImpl(CharacterRepository characterRepository, RaceRepository raceRepository, DataLoader dataLoader) {
        this.characterRepository = characterRepository;
        this.raceRepository = raceRepository;
        this.dataLoader = dataLoader;
    }

    public Character createCharacter(CharacterDTO characterDTO) throws IOException {
        Character character = new Character();
        character.setName(characterDTO.getName());

        character.setLevel(characterDTO.getLevel());

        Long raceId = characterDTO.getRace().getId();
        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new IllegalArgumentException("Race not found with id: " + raceId));

        character.setRace(race);
        return characterRepository.save(character);
    }

    @Override
    public List<Character> createCharacterList(List<CharacterDTO> characterDTOList) {
        List<Character> characters = new ArrayList<>();
        for (CharacterDTO characterDTO : characterDTOList) {
            Character character = new Character();
            character.setName(characterDTO.getName());
            character.setLevel(characterDTO.getLevel());
            Race race = raceRepository.findById(characterDTO.getRace().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Race not found with id: " + characterDTO.getRace().getId()));

            character.setRace(race);
            characters.add(character);
        }
        return characterRepository.saveAll(characters);
    }

    @Override
    public Character getById(Long id){
        return characterRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Character not found with id: " + id));
    }

    @Override
    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteAllCharacters() {
        characterRepository.deleteAll();
        resetCharactersId();
    }

    @Transactional
    private void resetCharactersId() {
        entityManager.createNativeQuery("ALTER TABLE `characters` AUTO_INCREMENT = 1")
                .executeUpdate();
    }
}