package com.company.JurisAI.service;


import com.company.JurisAI.dtos.LegalStrategyRequestDto;
import com.company.JurisAI.dtos.LegalStrategyResponseDto;
import com.company.JurisAI.entities.LegalCase;
import com.company.JurisAI.exceptions.ResourceNotFoundException;
import com.company.JurisAI.repository.LegalCaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LegalStrategyService {

    private final LegalCaseRepository legalCaseRepository;

    public LegalStrategyResponseDto recommendStrategies(LegalStrategyRequestDto requestDto) {
        LegalCase currentCase = legalCaseRepository.findById(requestDto.getCaseId())
                .orElseThrow(() -> new ResourceNotFoundException("Case not found with ID: " + requestDto.getCaseId()));

        log.info("Generating strategy recommendations for case: {}", requestDto.getCaseId());

        List<String> strategies = generateStrategiesBasedOnIssue(requestDto.getLegalIssue());

        LegalStrategyResponseDto responseDto = new LegalStrategyResponseDto();
        responseDto.setCaseId(currentCase.getId());
        responseDto.setRecommendationStrategies(strategies);
        responseDto.setSummary("Strategies generated based on similar past cases and legal trends.");

        return responseDto;
    }

    private List<String> generateStrategiesBasedOnIssue(String issue) {
        if (issue.toLowerCase().contains("contract")) {
            return Arrays.asList("Emphasize breach of terms", "Highlight material performance", "Use prior rulings on similar clauses");
        } else if (issue.toLowerCase().contains("property")) {
            return Arrays.asList("Assert ownership history", "Cite landmark possession rulings", "Focus on adverse possession principles");
        } else {
            return Arrays.asList("Use case law precedents", "Argue constitutional violation", "Leverage expert testimony");
        }
    }
}