package com.company.JurisAI.dtos;


import lombok.Data;

import java.util.List;

@Data
public class StrategyRecommendationResponseDto {

    private Long caseId;
    private List<String> recommendedStrategies;
}
