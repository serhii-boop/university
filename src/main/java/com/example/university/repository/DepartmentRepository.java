package com.example.university.repository;

import com.example.university.entity.Department;
import com.example.university.entity.Lector;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"headOfDepartment"})
    Optional<Department> findByDepartmentName(String departmentName);

    @Query("SELECT d.lectors FROM Department d WHERE d.departmentName = :departmentName")
    List<Lector> findAllLectorsByDepartmentName(@Param("departmentName") String departmentName);
}
