package org.application;

import org.application.service.MenuService;
import org.application.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.application")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}