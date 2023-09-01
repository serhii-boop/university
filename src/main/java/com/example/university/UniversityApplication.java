package com.example.university;

import com.example.university.config.ConsoleConfigView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UniversityApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityApplication.class, args)
                .getBean(ConsoleConfigView.class).startApp();
    }
}
