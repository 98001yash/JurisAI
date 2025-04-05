package com.company.JurisAI.service;


import com.company.JurisAI.dtos.*;
import com.company.JurisAI.entities.LegalCase;
import com.company.JurisAI.repository.LegalCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VerdictAnalyzerService {

    private final LegalCaseRepository legalCaseRepository;

    public VerdictAnalysisResponseDto analyzeVerdicts(VerdictAnalysisRequestDto requestDto){
        List<LegalCase> allCases = legalCaseRepository.findAll();

        int total = allCases.size();
        int guilty = 0;
        int notGuilty = 0;
        int settled = 0;

        Map<String, Integer> judgeGuiltyMap = new HashMap<>();
        Map<String, Integer> judgeTotalMap = new HashMap<>();

        for (LegalCase legalCase : allCases) {
            String verdict = legalCase.getCaseStatus(); // Assuming `caseStatus` holds values like "Guilty", etc.
            String judge = legalCase.getJudge();

            if (verdict == null || judge == null) continue;

            switch (verdict.toLowerCase()) {
                case "guilty" -> guilty++;
                case "not guilty" -> notGuilty++;
                case "settled" -> settled++;
            }
            // Track per judge
            judgeTotalMap.put(judge, judgeTotalMap.getOrDefault(judge, 0)+1);
            if(verdict.equalsIgnoreCase("guilty")){
                judgeGuiltyMap.put(judge, judgeGuiltyMap.getOrDefault(judge, 0)+1);
            }
    }

        VerdictStats stats = new VerdictStats(total, guilty, notGuilty, settled);

        // Judge bias calculations
        List<JudgeBiasDto> judgeBiasList = judgeTotalMap.entrySet().stream()
                .map(entry -> {
                    String judge = entry.getKey();
                    int totalCasesByJudge = entry.getValue();
                    int guiltyByJudge = judgeGuiltyMap.getOrDefault(judge, 0);
                    double guiltyRate = (double) guiltyByJudge / totalCasesByJudge;
                    return new JudgeBiasDto(judge, guiltyRate);
                })
                .sorted(Comparator.comparingDouble(JudgeBiasDto::getGuiltyRate).reversed())
                .collect(Collectors.toList());

        BiasIndicatorDto biasIndicators = new BiasIndicatorDto(
                "Analysis pending",  // Placeholder
                "Analysis pending",  // Placeholder
                judgeBiasList
        );

        return new VerdictAnalysisResponseDto(stats, biasIndicators);
    }
}