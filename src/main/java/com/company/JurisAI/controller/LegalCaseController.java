package com.company.JurisAI.controller;


import com.company.JurisAI.dtos.LegalCaseDto;
import com.company.JurisAI.dtos.LegalCaseRequestDto;
import com.company.JurisAI.dtos.LegalCaseResponseDto;
import com.company.JurisAI.repository.LegalCaseRepository;
import com.company.JurisAI.service.LegalCaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cases")
@RequiredArgsConstructor
public class LegalCaseController {

    private final LegalCaseService legalCaseService;
    private final LegalCaseRepository legalCaseRepository;

    // Create a new legal case
    @PostMapping
    public ResponseEntity<LegalCaseResponseDto> createCase(@RequestBody LegalCaseRequestDto caseRequestDto) {
        log.info("Creating a new legal case: {}", caseRequestDto.getTitle());
        return ResponseEntity.ok(legalCaseService.createCase(caseRequestDto));
    }

    // Get a legal case by ID
    @GetMapping("/{id}")
    public ResponseEntity<LegalCaseResponseDto> getCaseById(@PathVariable Long id) {
        log.info("Fetching legal case with ID: {}", id);
        return ResponseEntity.ok(legalCaseService.getCaseById(id));
    }

    // Get all legal cases
    @GetMapping
    public ResponseEntity<List<LegalCaseResponseDto>> getAllCases() {
        log.info("Fetching all legal cases");
        return ResponseEntity.ok(legalCaseService.getAllCases());
    }

    // Update a legal case
    @PutMapping("/{id}")
    public ResponseEntity<LegalCaseResponseDto> updateCase(
            @PathVariable Long id,
            @RequestBody LegalCaseRequestDto caseRequestDto) {
        log.info("Updating legal case with ID: {}", id);
        return ResponseEntity.ok(legalCaseService.updateCase(id, caseRequestDto));
    }

    // Delete a legal case
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCase(@PathVariable Long id) {
        log.info("Deleting legal case with ID: {}", id);
        legalCaseService.deleteCase(id);
        return ResponseEntity.ok("Legal case deleted successfully");
    }


    @GetMapping("/search")
    public ResponseEntity<List<LegalCaseDto>> searchCases(@RequestParam String keyword){
        log.info("Searching for cases with keywords: {}",keyword);
        return ResponseEntity.ok(legalCaseService.searchCases(keyword));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<LegalCaseDto>> filterCases(
            @RequestParam(required = false) String courtName,
            @RequestParam(required = false) String caseStatus,
            @RequestParam(required = false) LocalDate filedDate
    ){
        log.info("filtering cases = court: {}, status:  {} , filed Date:  {}",courtName, caseStatus, filedDate);
        return ResponseEntity.ok(legalCaseService.filterCases(courtName, caseStatus, filedDate));
    }
}