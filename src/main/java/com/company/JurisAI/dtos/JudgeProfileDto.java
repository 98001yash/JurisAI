package com.company.JurisAI.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JudgeProfileDto {

    private String judgeName;
    private Long totalCases;
    private double averageVerdictTimeInDays;
    private List<String> commonCaseTypes;
    private List<String> frequentlyUsedTerms;
    private String decisionTrend;
}
