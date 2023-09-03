package com.example.university.service.impl;

import com.example.university.entity.Department;
import com.example.university.entity.Lector;
import com.example.university.repository.DepartmentRepository;
import com.example.university.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private static final String HEAD_OF_DEPARTMENT_ANSWER = "Head of %s department is %s";
    private static final String AVG_DEPARTMENT_SALARY_ANSWER = "The average salary of %s is %s";
    private static final String DEPARTMENT_NOT_FOUND = "Department %s not found. Please try again";

    @Override
    public String getHeadOfDepartment(String departmentName) {
        var department = getDepartmentByName(departmentName);
        if (department.isPresent()) {
            var headOfDepartmentName = department.get().getHeadOfDepartment().getLectorName();
            return String.format(HEAD_OF_DEPARTMENT_ANSWER, departmentName, headOfDepartmentName);
        }

        return String.format(DEPARTMENT_NOT_FOUND, departmentName);
    }

    @Override
    public String getDepartmentStatistics(String departmentName) {
        return departmentRepository.findAllLectorsByDepartmentName(departmentName).stream()
                .collect(groupingBy(Lector::getLectorDegree, counting()))
                .entrySet()
                .stream()
                .map(entry -> String.format("%s - %d", entry.getKey(), entry.getValue()))
                .collect(joining("\n"));

    }

    @Override
    public String getAvgDepartmentSalary(String departmentName) {
        var avgSalary = departmentRepository.findAllLectorsByDepartmentName(departmentName).stream()
                .map(Lector::getMonthSalary)
                .mapToDouble(BigDecimal::doubleValue)
                .average();

        if (avgSalary.isEmpty()) {
            return String.format(DEPARTMENT_NOT_FOUND, departmentName);
        }
        return String.format(AVG_DEPARTMENT_SALARY_ANSWER, departmentName, avgSalary.getAsDouble());
    }

    @Override
    public String countEmployeeByDepartment(String departmentName) {
        var lectorsSize = departmentRepository.findAllLectorsByDepartmentName(departmentName);

        if (lectorsSize.isEmpty()) {
            return String.format(DEPARTMENT_NOT_FOUND, departmentName);
        }
        return String.valueOf(lectorsSize.size());
    }

    private Optional<Department> getDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName);
    }
}
