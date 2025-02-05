package org.application.service;

import org.application.model.Character;
import org.application.model.dto.CharacterDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface CharacterService {

    List<Character> getAllCharacters();

    Character getById(Long id);

    @Transactional
    Character createCharacter(CharacterDTO characterDTO) throws IOException;

    @Transactional
    List<Character> createCharacterList(List<CharacterDTO> characterDTO) throws IOException;

    @Transactional
    Optional<Character> updateCharacter(Long id, Character newCharacter);

    @Transactional
    void deleteAllCharacters();
}
