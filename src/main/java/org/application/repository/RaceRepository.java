package org.application.repository;

import org.application.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
    boolean existsByName(String name);
    Race findByName(String name);
    Optional<Race> findById(Long id);
}
