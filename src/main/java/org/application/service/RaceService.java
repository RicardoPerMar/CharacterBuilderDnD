package org.application.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface RaceService {
    void readAndSaveRaces() throws IOException;
}
