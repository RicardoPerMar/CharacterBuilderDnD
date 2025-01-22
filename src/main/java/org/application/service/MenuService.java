package org.application.service;

import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
public class MenuService {

    private final Scanner scanner = new Scanner(System.in);
/*
    @PostConstruct
    public void init() {
        System.out.println("CharacterCreatorService is injected: " + (characterCreatorService != null));
    }
*/
    public void showMenu() {
        String option;
        System.out.println("\n" + "-".repeat(60));
        System.out.println("WELCOME TO \"D&D BUILDER\"");
        System.out.println("-".repeat(60) + "\n");
        do {
            System.out.print("""
                    Select your option:
                    
                    1. Create character
                    2. Information
                    3. Exit
                    
                    """);
            System.out.print("Option: ");
            option = scanner.nextLine();
            System.out.println();
            switch (option) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    System.out.println("Thank you for use Character Builder DnD\n\n\n");
                    break;
                default:
                    System.out.println("\n" + "-".repeat(60));
                    System.out.println("Choose any of the following options");
                    System.out.println("-".repeat(60) + "\n");
            }
        } while (!option.equals("3"));
    }
}

