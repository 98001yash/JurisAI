package com.company.JurisAI.controller;


import com.company.JurisAI.advices.ApiResponse;
import com.company.JurisAI.dtos.LegalStrategyRequestDto;
import com.company.JurisAI.dtos.LegalStrategyResponseDto;
import com.company.JurisAI.dtos.StrategyRecommendationRequestDto;
import com.company.JurisAI.dtos.StrategyRecommendationResponseDto;
import com.company.JurisAI.service.LegalStrategyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/strategies")
@RequiredArgsConstructor
@Slf4j
public class LegalStrategyController {

    private final LegalStrategyService strategyService;

    @PostMapping("/recommend")
    public ResponseEntity<ApiResponse<LegalStrategyResponseDto>> recommendStrategies(
            @RequestBody LegalStrategyRequestDto requestDto) {
        log.info("Received strategy recommendation request for case ID: {}", requestDto.getCaseId());
        LegalStrategyResponseDto strategies = strategyService.recommendStrategies(requestDto);
        return ResponseEntity.ok(new ApiResponse<>(strategies));
    }
}
