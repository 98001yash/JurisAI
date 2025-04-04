package com.company.JurisAI.dtos;

import lombok.Data;

@Data
public class StrategyRecommendationRequestDto {

    private Long caseId;
    private String legalIssue;
}
