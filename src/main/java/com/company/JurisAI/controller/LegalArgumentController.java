package com.company.JurisAI.controller;


import com.company.JurisAI.dtos.LegalArgumentRequestDto;
import com.company.JurisAI.dtos.LegalArgumentResponseDto;
import com.company.JurisAI.service.LegalArgumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/legal-arguments")
@RequiredArgsConstructor
public class LegalArgumentController {

    private final LegalArgumentService legalArgumentService;

    @PostMapping
    public ResponseEntity<LegalArgumentResponseDto> generateArguments(@RequestBody LegalArgumentRequestDto requestDto){
        log.info("Received request to generate legal arguments for issue: {}",requestDto.getIssue());
        return ResponseEntity.ok(legalArgumentService.generateArguments(requestDto));
    }
}
