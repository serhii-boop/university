package com.example.university.config;

import com.example.university.service.UniversityServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConsoleConfigView {

    private final UniversityServiceFactory universityServiceFactory;

    public void startApp() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            printMainMenu();
            String command = scanner.nextLine();
            if ("0".equals(command)) {
                exit = true;
            } else {
                System.out.println(universityServiceFactory.executeCommand(command));
            }
        }
        System.out.println("Exiting the application.");
    }

    private void printMainMenu() {
        System.out.println("Please write a command.\nIf you want to exit, type 0:");
    }

}
