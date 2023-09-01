package com.example.university.service.impl;

import com.example.university.entity.Lector;
import com.example.university.repository.LectorRepository;
import com.example.university.service.LectorService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectorServiceImpl implements LectorService {

    private final LectorRepository lectorRepository;

    @Override
    @Transactional(readOnly = true)
    public String searchLectorByKeyword(String keyword) {
        var lectors = lectorRepository.findAllByLectorNameContainingIgnoreCase(keyword)
                .stream()
                .map(Lector::getLectorName)
                .collect(Collectors.joining(", "));

        if (lectors.isBlank()){
            return "Nothing was found";
        }

        return lectors;
    }
}
