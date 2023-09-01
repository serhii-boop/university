package com.example.university.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LectorDegree {

    ASSISTANT("assistant"), ASSOCIATE("associate"), PROFESSOR("professor");

    @Getter
    private final String degree;
}
