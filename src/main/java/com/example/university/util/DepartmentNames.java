package com.example.university.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DepartmentNames {
    CS("Computer Science"),
    PHYSIC("Physics"),
    ENGINEERING("Engineering"),
    ARCHITECTURE("Architecture");

    @Getter
    private final String department;
}
