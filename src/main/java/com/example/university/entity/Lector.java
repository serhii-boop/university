package com.example.university.entity;

import com.example.university.util.LectorDegree;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@Table(name = "lectors")
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lector_name")
    private String lectorName;

    @Column(name = "month_salary")
    private BigDecimal monthSalary;

    @Enumerated(EnumType.STRING)
    private LectorDegree lectorDegree;

    @ManyToMany(mappedBy = "lectors", fetch = FetchType.LAZY)
    private Set<Department> departments;
}
