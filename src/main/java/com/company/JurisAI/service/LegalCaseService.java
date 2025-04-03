package com.company.JurisAI.service;


import com.company.JurisAI.dtos.LegalCaseDto;
import com.company.JurisAI.dtos.LegalCaseRequestDto;
import com.company.JurisAI.dtos.LegalCaseResponseDto;
import com.company.JurisAI.entities.LegalCase;
import com.company.JurisAI.exceptions.ResourceNotFoundException;
import com.company.JurisAI.repository.LegalCaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LegalCaseService {

    private final LegalCaseRepository legalCaseRepository;
    private final ModelMapper modelMapper;

    // Create a new legal case
    public LegalCaseResponseDto createCase(LegalCaseRequestDto caseRequestDto) {
        log.info("Creating new legal case: {}", caseRequestDto.getTitle());
        LegalCase legalCase = modelMapper.map(caseRequestDto, LegalCase.class);
        LegalCase savedCase = legalCaseRepository.save(legalCase);
        return modelMapper.map(savedCase, LegalCaseResponseDto.class);
    }

    // Get a case by ID
    public LegalCaseResponseDto getCaseById(Long id) {
        log.info("Fetching legal case with ID: {}", id);
        LegalCase legalCase = legalCaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Legal case not found with ID: " + id));
        return modelMapper.map(legalCase, LegalCaseResponseDto.class);
    }

    // Get all legal cases
    public List<LegalCaseResponseDto> getAllCases() {
        log.info("Fetching all legal cases");
        return legalCaseRepository.findAll().stream()
                .map(legalCase -> modelMapper.map(legalCase, LegalCaseResponseDto.class))
                .collect(Collectors.toList());
    }

    // Update a case
    public LegalCaseResponseDto updateCase(Long id, LegalCaseRequestDto caseRequestDto) {
        log.info("Updating legal case with ID: {}", id);
        LegalCase legalCase = legalCaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Legal case not found with ID: " + id));

        // Update fields
        legalCase.setTitle(caseRequestDto.getTitle());
        legalCase.setDescription(caseRequestDto.getDescription());
        legalCase.setCaseStatus(caseRequestDto.getCaseStatus());

        LegalCase updatedCase = legalCaseRepository.save(legalCase);
        return modelMapper.map(updatedCase, LegalCaseResponseDto.class);
    }

    // Delete a case
    public void deleteCase(Long id) {
        log.info("Deleting legal case with ID: {}", id);
        LegalCase legalCase = legalCaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Legal case not found with ID: " + id));
        legalCaseRepository.delete(legalCase);
    }



    public List<LegalCaseDto> searchCases(String keyword){
        return legalCaseRepository.searchByKeyword(keyword)
                .stream()
                .map(caseEntity->modelMapper.map(caseEntity, LegalCaseDto.class))
                .collect(Collectors.toList());
    }

    public List<LegalCaseDto> filterCases(String courtName, String caseStatus, LocalDate filedDate){
        return legalCaseRepository.filterCases(courtName, caseStatus, filedDate)
                .stream()
                .map(caseEntity->modelMapper.map(caseEntity, LegalCaseDto.class))
                .collect(Collectors.toList());
    }
}
