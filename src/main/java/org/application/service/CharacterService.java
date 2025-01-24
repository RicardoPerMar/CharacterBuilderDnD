package org.application.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface CharacterService {
    void createCharacter() throws IOException;
}
