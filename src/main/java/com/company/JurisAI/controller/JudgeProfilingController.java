package com.company.JurisAI.controller;


import com.company.JurisAI.dtos.JudgeProfileDto;
import com.company.JurisAI.service.JudgeProfilingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/judge-profiling")
@RequiredArgsConstructor
public class JudgeProfilingController {

    private final JudgeProfilingService judgeProfilingService;

    @GetMapping("/{judgeName}")
    public ResponseEntity<JudgeProfileDto> getJudgeProfile(@PathVariable String judgeName) {
        JudgeProfileDto profile = judgeProfilingService.analyzeJudgeProfile(judgeName);
        return ResponseEntity.ok(profile);
    }
}
