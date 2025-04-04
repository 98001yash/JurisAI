package com.company.JurisAI.service;


import com.company.JurisAI.dtos.StrategyRecommendationRequestDto;
import com.company.JurisAI.dtos.StrategyRecommendationResponseDto;
import com.company.JurisAI.entities.LegalCase;
import com.company.JurisAI.exceptions.ResourceNotFoundException;
import com.company.JurisAI.repository.LegalCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LegalStrategyService {

    private final LegalCaseRepository legalCaseRepository;

    public StrategyRecommendationResponseDto recommendStrategy(StrategyRecommendationRequestDto requestDto) {
        LegalCase legalCase = legalCaseRepository.findById(requestDto.getCaseId())
                .orElseThrow(() -> new ResourceNotFoundException("case not found with id: " + requestDto.getCaseId()));

        // Dummy logic for now
        List<String> strategies = List.of(
                "File a preliminary motion to dismiss",
                "Request evidence disclosure",
                "Cite precedent case XYZ vs ABC (2020)",
                "Negotiate settlement for faster resolution"
        );

        StrategyRecommendationResponseDto response = new StrategyRecommendationResponseDto();
        response.setCaseId(legalCase.getId());
        response.setRecommendedStrategies(strategies);

        return response;
    }
}
