package com.example.university.repository;

import com.example.university.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {

    List<Lector> findAllByLectorNameContainingIgnoreCase(String keyword);
}
