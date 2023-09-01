package com.example.university.service;

import com.example.university.util.UniversityServiceCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Pattern;

import static com.example.university.util.UniversityServiceCommand.AVG_DEPARTMENT_SALARY;
import static com.example.university.util.UniversityServiceCommand.COUNT_DEPARTMENT_EMPLOYEES;
import static com.example.university.util.UniversityServiceCommand.DEPARTMENT_STATISTIC;
import static com.example.university.util.UniversityServiceCommand.HEAD_OF_DEPARTMENT;
import static com.example.university.util.UniversityServiceCommand.SEARCH_LECTOR_BY_KEYWORD;

@Service
@RequiredArgsConstructor
public class UniversityServiceFactory {

    private final DepartmentService departmentService;
    private final LectorService lectorService;
    private final Map<Pattern, UniversityServiceCommand> commandPattern = Map.of(
            Pattern.compile("Who is head of department (.+)"), HEAD_OF_DEPARTMENT,
            Pattern.compile("Show (.+) statistics"), DEPARTMENT_STATISTIC,
            Pattern.compile("Show the average salary for the department (.+)"), AVG_DEPARTMENT_SALARY,
            Pattern.compile("Show count of employee for (.+)"), COUNT_DEPARTMENT_EMPLOYEES,
            Pattern.compile("Global search by (.+)"), SEARCH_LECTOR_BY_KEYWORD

    );

    public String executeCommand(String command) {
        String returningString = "Command not found";
        for (Pattern pattern: commandPattern.keySet()) {
            var matcher = pattern.matcher(command);
            if (matcher.find()) {
                return switch (commandPattern.get(pattern)) {
                    case HEAD_OF_DEPARTMENT -> departmentService.getHeadOfDepartment(matcher.group(1));
                    case DEPARTMENT_STATISTIC -> departmentService.getDepartmentStatistics(matcher.group(1));
                    case AVG_DEPARTMENT_SALARY -> departmentService.getAvgDepartmentSalary(matcher.group(1));
                    case COUNT_DEPARTMENT_EMPLOYEES -> departmentService.countEmployeeByDepartment(matcher.group(1));
                    case SEARCH_LECTOR_BY_KEYWORD -> lectorService.searchLectorByKeyword(matcher.group(1));
                };
            }
        }

        return returningString;
    }


}
