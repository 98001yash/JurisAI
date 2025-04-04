package com.company.JurisAI.controller;


import com.company.JurisAI.dtos.StrategyRecommendationRequestDto;
import com.company.JurisAI.dtos.StrategyRecommendationResponseDto;
import com.company.JurisAI.service.LegalStrategyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/strategy")
@RequiredArgsConstructor
public class LegalStrategyController {

    private final LegalStrategyService legalStrategyService;

    @PostMapping
    public ResponseEntity<StrategyRecommendationResponseDto> recommendStrategy(
            @RequestBody StrategyRecommendationRequestDto requestDto
            ){
        return ResponseEntity.ok(legalStrategyService.recommendStrategy(requestDto));
    }
}
