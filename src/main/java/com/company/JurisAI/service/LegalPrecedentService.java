package com.company.JurisAI.service;


import com.company.JurisAI.dtos.LegalPrecedentRequestDto;
import com.company.JurisAI.dtos.LegalPrecedentResponseDto;
import com.company.JurisAI.entities.LegalCase;
import com.company.JurisAI.repository.LegalCaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LegalPrecedentService {

    private final LegalCaseRepository legalCaseRepository;

    public LegalPrecedentResponseDto findRelevantPrecedents(LegalPrecedentRequestDto requestDto){
        String keyword = requestDto.getLegalIssue().toLowerCase();
        log.info("Searching for relevant precedents for keyword: {}",keyword);

        List<LegalCase> allCases = legalCaseRepository.findAll();

        List<String> matchingCases = allCases.stream()
                .filter(c -> c.getDescription().toLowerCase().contains(keyword)
                        || c.getTitle().toLowerCase().contains(keyword))
                .map(LegalCase::getTitle)
                .collect(Collectors.toList());

        LegalPrecedentResponseDto response = new LegalPrecedentResponseDto();
        response.setCaseId(requestDto.getCaseId());
        response.setRelevantCases(matchingCases);
        return response;
    }
}
