package com.example.university.service;

public interface DepartmentService {

    String getHeadOfDepartment(String department);
    String getDepartmentStatistics(String department);
    String getAvgDepartmentSalary(String department);
    String countEmployeeByDepartment(String department);
}
