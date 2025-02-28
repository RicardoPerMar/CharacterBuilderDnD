package org.application.controller;

import org.application.model.Character;
import org.application.model.dto.CharacterDTO;
import org.application.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    @PutMapping("/update/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable Long id, @RequestBody Character newCharacter){
        return characterService.updateCharacter(id, newCharacter)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping
    public void deleteAll() {
        characterService.deleteAllCharacters();
    }
}
