package com.company.JurisAI.service;

import com.company.JurisAI.dtos.JudgeProfileDto;
import com.company.JurisAI.entities.LegalCase;
import com.company.JurisAI.repository.LegalCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JudgeProfilingService {

    private final LegalCaseRepository legalCaseRepository;


    public JudgeProfileDto analyzeJudgeProfile(String judgeName){
        List<LegalCase> cases = legalCaseRepository.findByJudge(judgeName);
        if(cases.isEmpty()){
            throw new RuntimeException("No cases found for judge: "+judgeName);
        }
        long totalCases = cases.size();

        // Average verdict time
        double avgVerdictTime = cases.stream()
                .filter(c -> c.getFiledDate() != null && c.getVerdictDate() != null)
                .mapToLong(c -> ChronoUnit.DAYS.between(c.getFiledDate(), c.getVerdictDate()))
                .average().orElse(0.0);

        // Common case types
        List<String> commonCaseTypes = cases.stream()
                .map(LegalCase::getCaseStatus)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Frequently used terms from descriptions
        Map<String, Long> termFrequency = new HashMap<>();
        for (LegalCase c : cases) {
            if (c.getDescription() != null) {
                String[] words = c.getDescription().toLowerCase().split("\\W+");
                for (String word : words) {
                    if (word.length() > 4) {
                        termFrequency.put(word, termFrequency.getOrDefault(word, 0L) + 1);
                    }
                }
            }
        }

        List<String> commonTerms = termFrequency.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Decision trend (mock logic)
        String trend = avgVerdictTime > 60 ? "Lenient" : avgVerdictTime < 20 ? "Strict" : "Balanced";

        return JudgeProfileDto.builder()
                .judgeName(judgeName)
                .totalCases(totalCases)
                .averageVerdictTimeInDays(avgVerdictTime)
                .commonCaseTypes(commonCaseTypes)
                .frequentlyUsedTerms(commonTerms)
                .decisionTrend(trend)
                .build();
    }
}