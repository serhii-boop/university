package com.example.university.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class ConsoleConfigView {

    private final Map<String, Integer> commandPattern = Map.of(
            "Who is head of department (.+)", 1,
            "Show (.+) statistics", 2,
            "Show the average salary for the department (.+)", 3,
            "Show count of employee for (.+)", 4,
            "Global search by {template}", 5
    );

    public void startApp() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            printMainMenu();
            String choice = scanner.next();
            if ("0".equals(choice)) {
                exit = true;
            } else {
                extractCommand(choice);
            }

        }

        System.out.println("Exiting the application.");
    }

    private void extractCommand(String choice) {
    }

    private void printMainMenu() {
        System.out.println("Please write a command if you want to exit, type 0:");
        System.out.println("1. Who is head of department?");
        System.out.println("2. Subtract");
        System.out.println("3. Multiply");
        System.out.println("4. Divide");
        System.out.println("0. Exit");
        System.out.println("99. Open New University App Console");
        System.out.print("Enter command number: ");
    }

    private void performAddition(Scanner scanner) {
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();
        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();
        double result = num1 + num2;
        System.out.println("Result: " + result);
    }

    private void performSubtraction(Scanner scanner) {
        // Similar logic for subtraction
    }

    private void performMultiplication(Scanner scanner) {
        // Similar logic for multiplication
    }

    private void performDivision(Scanner scanner) {
        // Similar logic for division
    }

    private void launchUniversityAppConsole() {
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "start", "cmd.exe", "/K", "title University App");
        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
