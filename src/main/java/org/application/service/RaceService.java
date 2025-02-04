package org.application.service;

import org.application.model.Race;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RaceService {
    void showAllRaces();
    List<Race> getAllRaces();
}
