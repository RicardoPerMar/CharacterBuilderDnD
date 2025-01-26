package org.application.service;

import org.application.utils.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Scanner;

@Service
public class MenuService {

    //private final Scanner scanner = new Scanner(System.in);
    private final RaceService raceService;
    private final CharacterService characterService;
    private final InputService inputService;
    private final String doubleLine = "=".repeat(200) + "\n";

    public MenuService(CharacterService characterService, RaceService raceService, InputService inputService) {
        this.characterService = characterService;
        this.raceService = raceService;
        this.inputService = inputService;
    }

    public void showMenu() throws InterruptedException, IOException {
        String option;
        System.out.println("\n".repeat(20));
        System.out.println(doubleLine);
        System.out.println("\t".repeat(20) + "WELCOME TO \"D&D BUILDER\"\n");
        System.out.println(doubleLine);

        Thread.sleep(500);

        do {
            printMenuOptions();
            System.out.print("Option: ");
            option = inputService.getLineInput();
            System.out.println("\n" + doubleLine);
            switch (option) {
                case "1":
                    characterService.createCharacter();
                    break;
                case "2":
                    System.out.println("Races: ");
                    raceService.showAllRaces();
                    break;
                case "3":
                    System.out.println("\t".repeat(20) + "Thank you for use Character Builder DnD\n");
                    System.out.println(doubleLine);
                    break;
                default:
                    System.out.println("\n" + "-".repeat(60));
                    System.out.println("Choose any of the following options");
                    System.out.println("-".repeat(60) + "\n");
            }
        } while (!option.equals("3"));
    }

    private void printMenuOptions(){
        System.out.print("""
                    Select your option:
                    
                    1. Create character
                    2. Information
                    3. Exit
                    
                    """);
    }
}

