package org.application.utils;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class InputService {
    private final Scanner scanner = new Scanner(System.in);

    public String getLineInput(){
        return scanner.next();
    }

    public double getIntInput(){
        scanner.nextLine();
        return scanner.nextInt();
    }
}
