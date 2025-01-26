package org.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public interface CharacterService {
    @Transactional
    void createCharacter() throws IOException;
}
