package org.application.controller;

import jakarta.websocket.server.PathParam;
import org.application.model.Character;
import org.application.model.dto.CharacterDTO;
import org.application.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/")
    public List<Character> getAllCharacter() {
        return characterService.getAllCharacters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> getById(@PathVariable Long id) {
        return ResponseEntity.ok(characterService.getById(id));
    }

    @PostMapping("/character")
    public ResponseEntity<Character> createCharacter(@RequestBody CharacterDTO characterDTO) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(characterService.createCharacter(characterDTO));
    }

    @PostMapping("/characterList")
    public ResponseEntity<List<Character>> createCharacterList(@RequestBody List<CharacterDTO> characterDTOList) throws IOException {
        List<Character> characters = characterService.createCharacterList(characterDTOList);
        return ResponseEntity.status(HttpStatus.CREATED).body(characters);
    }

    @DeleteMapping
    public void deleteAll() {
        characterService.deleteAllCharacters();
    }
}
