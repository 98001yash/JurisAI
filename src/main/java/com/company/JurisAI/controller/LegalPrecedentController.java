package com.company.JurisAI.controller;

import com.company.JurisAI.advices.ApiResponse;
import com.company.JurisAI.dtos.LegalPrecedentRequestDto;
import com.company.JurisAI.dtos.LegalPrecedentResponseDto;
import com.company.JurisAI.service.LegalPrecedentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/legal-precedents")
@RequiredArgsConstructor
public class LegalPrecedentController {

    private final LegalPrecedentService precedentService;

    @PostMapping
    public ResponseEntity<ApiResponse<LegalPrecedentResponseDto>> getRelevantPrecedents(
            @RequestBody LegalPrecedentRequestDto requestDto
    ) {
        log.info("Received precedent search request for case ID: {}", requestDto.getCaseId());
        LegalPrecedentResponseDto responseDto = precedentService.findRelevantPrecedents(requestDto);
        return ResponseEntity.ok(new ApiResponse<>(responseDto));
    }
}