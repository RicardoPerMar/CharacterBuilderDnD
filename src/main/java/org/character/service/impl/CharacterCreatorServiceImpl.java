package org.character.service.impl;

import org.character.model.Stat;
import org.character.model.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CharacterCreatorServiceImpl {

    private final Stat[] stats;
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public CharacterCreatorServiceImpl(Stat[] stats) {

        this.stats = stats;
    }

    public void createCharacter() {

        System.out.print("Character name: ");
        String name = scanner.nextLine();
        System.out.print("Race: ");
        String race = scanner.nextLine();
        System.out.print("Class: ");
        String characterClass = scanner.nextLine();

        Character character = new Character(name, race, characterClass, stats);
        System.out.print("Atributos...\nTirada de dados (y/n):");
        String opAtt = scanner.nextLine();

        if ("y".equalsIgnoreCase(opAtt)) {
            diceRoll(character.getAttributes());
        } else {
            standardStats(character.getAttributes());
        }
        showCharacter(character);
    }

    private void diceRoll(Stat[] stats) {
        Stat[] a = new Stat[6];
        Random random = new Random();
        int[] values = new int[6];

        for (int i = 0; i < 6; i++) {
            int[] randValues = new int[4];
            int min = randValues[0];
            int sum = 0;
            for (int j = 0; j < randValues.length; j++) {
                randValues[j] = (random.nextInt(6) + 1);
            }

            for (int randValue : randValues) {
                if (randValue < min) {
                    min = randValue;
                }
            }

            for (int num : randValues) {
                if (num != min) {
                    sum += num;
                }
            }

            values[i] = sum;
        }
        asignedStats(stats, values);

    }

    private void standardStats(Stat[] stats) {
        int[] values = new int[]{15, 14, 13, 12, 10, 8};
        asignedStats(stats, values);
    }

    private void showCharacter(Character character) {
        System.out.println(
                "Character name: " + character.getName() +
                        "\nRace: " + character.getRace() +
                        "\nClass: " + character.getCharacterClass() +
                        "\n\nSTR\t\tDEX\t\tCON\t\tINT\t\tWIS\t\tCHA\n" +
                        character.getAttributes()[0].getValue() + "\t\t" +
                        character.getAttributes()[1].getValue() + "\t\t" +
                        character.getAttributes()[2].getValue() + "\t\t" +
                        character.getAttributes()[3].getValue() + "\t\t" +
                        character.getAttributes()[4].getValue() + "\t\t" +
                        character.getAttributes()[5].getValue() + "\t\t");
    }

    private void asignedStats(Stat[] stats, int[] values) {
        Set<String> validStats = new HashSet<>(Arrays.asList("STR", "DEX", "CON", "INT", "WIS", "CHA"));
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
                System.out.println("-".repeat(60));
            }
        }
    }

}
