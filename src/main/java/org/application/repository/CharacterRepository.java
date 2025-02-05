package org.application.repository;

import org.application.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    boolean existsByName(String name);
    Optional<Character> findByName(String name);
}
