package com.company.JurisAI.service;


import com.company.JurisAI.dtos.CaseOutcomePredictionRequestDto;
import com.company.JurisAI.dtos.CaseOutcomePredictionResponseDto;
import com.company.JurisAI.dtos.CaseTimelineEventResponseDto;
import com.company.JurisAI.entities.CaseOutcomesPrediction;
import com.company.JurisAI.entities.LegalCase;
import com.company.JurisAI.exceptions.ResourceNotFoundException;
import com.company.JurisAI.repository.CaseOutcomePredictionRepository;
import com.company.JurisAI.repository.LegalCaseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CaseOutcomesPredictionService {

    private final CaseOutcomePredictionRepository caseOutcomePredictionRepository;
    private final LegalCaseRepository legalCaseRepository;
    private final ModelMapper modelMapper;

    public CaseOutcomePredictionResponseDto predictOutcome(CaseOutcomePredictionRequestDto request){
        LegalCase legalCase = legalCaseRepository.findById(request.getCaseId())
                .orElseThrow(()->new ResourceNotFoundException("Legal case not found"));

        // simulate AI prediction logic
        // following the rule-based approach
        String predicted = Math.random()>0.5 ? "Won":"Lost";
        double confidence = 0.6 + (Math.random()*0.4);


        CaseOutcomesPrediction prediction = CaseOutcomesPrediction.builder()
                .legalCase(legalCase)
                .predictedOutcome(predicted)
                .confidenceScore(confidence)
                .caseFactsSummary(request.getCaseFactsSummary())
                .predictionDate(LocalDateTime.now())
                .build();

        caseOutcomePredictionRepository.save(prediction);
        return modelMapper.map(prediction, CaseOutcomePredictionResponseDto.class);
    }

    public CaseOutcomePredictionResponseDto getPredictionByCaseId(Long caseId){
        CaseOutcomesPrediction prediction = caseOutcomePredictionRepository.findByLegalCaseId(caseId)
                .orElseThrow(()->new ResourceNotFoundException("Prediction not found for case ID: "+caseId));
        return modelMapper.map(prediction, CaseOutcomePredictionResponseDto.class);
    }
}
