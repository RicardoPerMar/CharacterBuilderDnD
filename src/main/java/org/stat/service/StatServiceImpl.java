package org.stat.service;

import org.race.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.stat.model.Stat;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private RaceService raceService;

    private final Scanner scanner = new Scanner(System.in);
    private final Set<String> validStats = new HashSet<>(Arrays.asList("STR", "DEX", "CON", "INT", "WIS", "CHA"));

    @Override
    public void diceRoll(Stat[] stats) {
        Random random = new Random();
        int[] values = new int[6];


        for (int i = 0; i < 6; i++) {
            int[] randValues = new int[4];

            int sum = 0;
            for (int j = 0; j < randValues.length; j++) {
                randValues[j] = (random.nextInt(6) + 1);
            }
            int min = randValues[0];
            for (int randValue : randValues) {
                if (randValue < min) {
                    min = randValue;
                }
            }

            int cont = 0;
            for (int num : randValues) {

                if (num != min) {
                    sum += num;
                } else {
                    if (cont != 0){
                        sum += num;
                    }
                    cont++;
                }
            }

            values[i] = sum;
        }
        asignedStats(stats, values);
    }

    @Override
    public void standardStats(Stat[] stats) {
        int[] values = new int[]{15, 14, 13, 12, 10, 8};
        asignedStats(stats, values);

    }

    private void asignedStats(Stat[] stats, int[] values) {
        Set<String> usedStats = new HashSet<>();
        System.out.println("Insert value to stat (STR/DEX/CON/INT/WIS/CHA)");
        for (int i = 0; i < 6; i++) {
            boolean validInput = false;
            while (!validInput) {
                System.out.print(values[i] + " to stat: ");
                String stat = scanner.nextLine().toUpperCase();

                if (validStats.contains(stat)) {
                    if (!usedStats.contains(stat)) {
                        for (int j = 0; j < 6; j++) {
                            if (stat.equalsIgnoreCase(stats[j].getName())) {
                                stats[j].setValue(values[i]);
                                usedStats.add(stats[j].getName());
                                validInput = true;
                            }
                        }
                    } else {
                        System.out.println("Stat already assigned");
                    }
                } else {
                    System.out.println("Enter values as shown");
                }
            }
            if (i == 5) {
                System.out.println("-".repeat(60) + "\n");
            }
        }
    }

    public void setRaceStats(Stat[] stats, String race){
        Stat[] raceStats = raceService.getStatsByName(race);

        for (Stat statRace : raceStats){
            for (Stat stat : stats){
                if (statRace.getName().equals(stat.getName())){
                    stat.setValue(stat.getValue() + statRace.getValue());
                    break;
                }
            }
        }
    }
}
