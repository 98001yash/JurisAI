package com.company.JurisAI.controller;

import com.company.JurisAI.dtos.SummarizeRequestDto;
import com.company.JurisAI.dtos.SummarizeResponseDto;
import com.company.JurisAI.service.SummarizeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/summarizer")
@RequiredArgsConstructor
public class SummarizerController {

    private final SummarizeService summarizeService;

    @PostMapping
    public ResponseEntity<SummarizeResponseDto> summarizeText(@RequestBody SummarizeRequestDto requestDto){
        log.info("Summarizing text....");
        return ResponseEntity.ok(summarizeService.summarizeText(requestDto));
    }
}
