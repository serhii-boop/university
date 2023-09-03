package com.example.university.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class UniversityServiceFactory {

    private final DepartmentService departmentService;
    private final LectorService lectorService;
    private Map<Pattern, Function<String, String>> commandPattern;

    @PostConstruct
    void init() {
        commandPattern = Map.of(
                Pattern.compile("Who is head of department (.+)"), departmentService::getHeadOfDepartment,
                Pattern.compile("Show (.+) statistics"), departmentService::getDepartmentStatistics,
                Pattern.compile("Show the average salary for the department (.+)"), departmentService::getAvgDepartmentSalary,
                Pattern.compile("Show count of employee for (.+)"), departmentService::countEmployeeByDepartment,
                Pattern.compile("Global search by (.+)"), lectorService::searchLectorByKeyword
        );
    }

    public String executeCommand(String command) {
        for (var entry : commandPattern.entrySet()) {
            Matcher matcher = entry.getKey().matcher(command);
            if (matcher.find()) {
                return entry.getValue().apply(matcher.group(1));
            }
        }
        return "Command not found";
    }
}
