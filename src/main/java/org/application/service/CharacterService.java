package org.application.service;

import org.application.model.Character;
import org.application.model.dto.CharacterDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public interface CharacterService {

    @Transactional
    Character createCharacter(CharacterDTO characterDTO) throws IOException;

    @Transactional
    List<Character> createCharacterList(List<CharacterDTO> characterDTO) throws IOException;

    List<Character> getAllCharacters();

    @Transactional
    void deleteAllCharacters();

    Character getById(Long id);
}
