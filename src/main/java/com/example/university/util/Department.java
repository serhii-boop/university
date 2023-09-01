package com.example.university.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Department {
    CS("Computer Science Department"),
    PHYSIC("Physics Department"),
    ENGINEERING("Engineering Department"),
    ARCHITECTURE("Architecture Department");

    @Getter
    private final String department;
}
