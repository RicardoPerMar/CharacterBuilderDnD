package org.application.service.implementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.application.model.Character;
import org.application.model.CharacterClass;
import org.application.model.Race;
import org.application.model.Stat;
import org.application.model.dto.CharacterDTO;
import org.application.repository.CharacterClassRepository;
import org.application.repository.CharacterRepository;
import org.application.repository.RaceRepository;
import org.application.repository.StatRepository;
import org.application.service.CharacterService;
import org.application.utils.DataLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CharacterServiceImpl implements CharacterService {

    @PersistenceContext
    private EntityManager entityManager;
    private final DataLoader dataLoader;
    private final RaceRepository raceRepository;
    private final StatRepository statRepository;
    private final CharacterRepository characterRepository;
    private final CharacterClassRepository characterClassRepository;

    private final Scanner scanner = new Scanner(System.in);

    public CharacterServiceImpl(CharacterRepository characterRepository, RaceRepository raceRepository, StatRepository statRepository,
                                DataLoader dataLoader, CharacterClassRepository characterClassRepository) {
        this.characterRepository = characterRepository;
        this.raceRepository = raceRepository;
        this.statRepository = statRepository;
        this.dataLoader = dataLoader;
        this.characterClassRepository = characterClassRepository;
    }

    @Override
    public Character getById(Long id){
        return characterRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Character not found with id: " + id));
    }

    @Override
    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    public Character createCharacter(CharacterDTO characterDTO) throws IOException {
        if (characterRepository.existsByName(characterDTO.getName())){
            throw new IllegalArgumentException("Character with name '" + characterDTO.getName() + "' already exists.");
        }
        Character character = new Character();
        character.setName(characterDTO.getName());
        character.setLevel(characterDTO.getLevel());

        Long raceId = characterDTO.getRace().getId();
        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new IllegalArgumentException("Race not found with id: " + raceId));
        character.setRace(race);

        Stat stat = characterDTO.getStat();
        statRepository.save(stat);
        character.setStat(stat);

        Long characterClassId = characterDTO.getCharacterClass().getId();
        CharacterClass characterClass = characterClassRepository.findById(characterClassId).
                orElseThrow(() -> new IllegalArgumentException("Class not found with id: " + characterClassId));
        character.setCharacterClass(characterClass);

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

            Stat stat = characterDTO.getStat();
            statRepository.save(stat);
            character.setStat(stat);

            characters.add(character);
        }
        return characterRepository.saveAll(characters);
    }

    @Override
    public Optional<Character> updateCharacter(Long id, Character newCharacter) {
        return characterRepository.findById(id)
                .map(character -> {
                    character.setName(newCharacter.getName());
                    character.setLevel(newCharacter.getLevel());
                    character.setRace(newCharacter.getRace());
                    updateStatValues(character.getStat(), newCharacter.getStat());
                    return characterRepository.save(character);
                });
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

    @Transactional
    private void updateStatValues(Stat oldStat,Stat newStat){
        oldStat.setStr(newStat.getStr());
        oldStat.setDex(newStat.getDex());
        oldStat.setCon(newStat.getCon());
        oldStat.setWis(newStat.getWis());
        oldStat.setIntel(newStat.getIntel());
        oldStat.setCha(newStat.getCha());
    }
}