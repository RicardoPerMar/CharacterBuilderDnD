package org.stat.service;

import org.stat.model.Stat;

public interface StatService {
    void diceRoll(Stat[] stats);
    void standardStats(Stat[] stats);
    void setRaceStats(Stat[] stats, String race);
}
