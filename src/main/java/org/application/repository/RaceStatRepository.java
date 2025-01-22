package org.application.repository;

import org.application.model.RaceStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceStatRepository extends JpaRepository<RaceStat, Long> {
}
