package com.company.JurisAI.controller;


import com.company.JurisAI.dtos.CaseOutcomePredictionRequestDto;
import com.company.JurisAI.dtos.CaseOutcomePredictionResponseDto;
import com.company.JurisAI.service.CaseOutcomesPredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/predictions")
@RequiredArgsConstructor
public class CaseOutcomePredictionController {

    private final CaseOutcomesPredictionService predictionService;

    @PostMapping
    public ResponseEntity<CaseOutcomePredictionResponseDto> predictOutcome(
            @RequestBody CaseOutcomePredictionRequestDto requestDto) {
        return ResponseEntity.ok(predictionService.predictOutcome(requestDto));
    }

    @GetMapping("/{caseId}")
    public ResponseEntity<CaseOutcomePredictionResponseDto> getPrediction(@PathVariable Long caseId){
        return ResponseEntity.ok(predictionService.getPredictionByCaseId(caseId));
    }
}
