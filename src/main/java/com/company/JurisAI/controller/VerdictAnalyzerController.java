package com.company.JurisAI.controller;


import com.company.JurisAI.dtos.VerdictAnalysisRequestDto;
import com.company.JurisAI.dtos.VerdictAnalysisResponseDto;
import com.company.JurisAI.service.VerdictAnalyzerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/verdict-analysis")
@RequiredArgsConstructor
@Slf4j
public class VerdictAnalyzerController {

    private final VerdictAnalyzerService verdictAnalyzerService;

    @PostMapping
    public ResponseEntity<VerdictAnalysisResponseDto> analyzeVerdict(@RequestBody VerdictAnalysisRequestDto requestDto) {
        log.info("Analyzing verdict for legal case ID: {}", requestDto.getCaseId());
        VerdictAnalysisResponseDto response = verdictAnalyzerService.analyzeVerdicts(requestDto);
        return ResponseEntity.ok(response);
    }
}
