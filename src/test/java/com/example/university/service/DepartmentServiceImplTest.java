package com.example.university.service;

import com.example.university.entity.Department;
import com.example.university.entity.Lector;
import com.example.university.repository.DepartmentRepository;
import com.example.university.service.impl.DepartmentServiceImpl;
import com.example.university.util.LectorDegree;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    private static final String HEAD_OF_DEPARTMENT_ANSWER = "Head of %s department is %s";
    private static final String DEPARTMENT_NOT_FOUND = "Department %s not found. Please try again";

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    void testIfGetHeadOfDepartmentSuccess() {
        Department department = getDepartment();
        when(departmentRepository.findByDepartmentName(any())).thenReturn(Optional.of(department));

        String expectedAnswer = String.format(HEAD_OF_DEPARTMENT_ANSWER, department.getDepartmentName(),
                department.getHeadOfDepartment().getLectorName());
        String actualAnswer = departmentService.getHeadOfDepartment(department.getDepartmentName());

        assertEquals(expectedAnswer, actualAnswer);

    }

    @Test
    void testIfGetHeadOfDepartmentNotFond() {
        String notFoundDepartment = "notFoundDepartment";
        when(departmentRepository.findByDepartmentName(notFoundDepartment)).thenReturn(Optional.empty());

        String expectedAnswer = String.format(DEPARTMENT_NOT_FOUND, notFoundDepartment);
        String actualAnswer = departmentService.getHeadOfDepartment(notFoundDepartment);

        assertEquals(expectedAnswer, actualAnswer);

    }

    @Test
    void testGetDepartmentStatistics() {
        List<Lector> lectors = getLectors();
        when(departmentRepository.findAllLectorsByDepartmentName(any())).thenReturn(lectors);

        String departmentStatistic = departmentService.getDepartmentStatistics(any());
        String expectedProfessorStatistic = "PROFESSOR - 2";
        String expectedAssociateStatistic = "ASSOCIATE - 1";
        String expectedAssistantStatistic = "ASSISTANT - 1";

        assertNotNull(departmentStatistic);
        assertTrue(departmentStatistic.contains(expectedAssistantStatistic));
        assertTrue(departmentStatistic.contains(expectedAssociateStatistic));
        assertTrue(departmentStatistic.contains(expectedProfessorStatistic));
    }

    @Test
    void testGetAvgDepartmentSalarySuccess() {
        List<Lector> lectors = getLectors();
        when(departmentRepository.findAllLectorsByDepartmentName(any())).thenReturn(lectors);

        String avgSalaryAnswer = departmentService.getAvgDepartmentSalary(any());
        String avgSalary = String.valueOf(lectors.stream()
                .map(Lector::getMonthSalary)
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .getAsDouble());


        assertNotNull(avgSalaryAnswer);
        assertTrue(avgSalaryAnswer.contains(avgSalary));
    }

    @Test
    void testGetAvgDepartmentSalaryNotFound() {
        String department = "Department";
        when(departmentRepository.findAllLectorsByDepartmentName(department)).thenReturn(new ArrayList<>());

        String expectedAnswer = String.format(DEPARTMENT_NOT_FOUND, department);
        String avgSalaryAnswer = departmentService.getAvgDepartmentSalary(department);

        assertEquals(expectedAnswer, avgSalaryAnswer);
    }

    private Department getDepartment() {
        return Department.builder()
                .departmentName("Physic")
                .headOfDepartment(Lector.builder()
                        .id(1L)
                        .lectorName("Serhii")
                        .lectorDegree(LectorDegree.PROFESSOR)
                        .monthSalary(BigDecimal.valueOf(100))
                        .build())
                .build();
    }

    private List<Lector> getLectors() {
        return List.of(
                Lector.builder().lectorDegree(LectorDegree.PROFESSOR).lectorName("Serhii").monthSalary(BigDecimal.valueOf(3000)).build(),
                Lector.builder().lectorDegree(LectorDegree.PROFESSOR).lectorName("Petro").monthSalary(BigDecimal.valueOf(900)).build(),
                Lector.builder().lectorDegree(LectorDegree.ASSISTANT).lectorName("Ivan").monthSalary(BigDecimal.valueOf(1900)).build(),
                Lector.builder().lectorDegree(LectorDegree.ASSOCIATE).lectorName("Marat").monthSalary(BigDecimal.valueOf(1300)).build()
        );
    }

}
