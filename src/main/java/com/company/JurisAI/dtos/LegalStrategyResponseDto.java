package com.company.JurisAI.dtos;


import lombok.Data;

import java.util.List;

@Data
public class LegalStrategyResponseDto {

    private Long caseId;
    private List<String> recommendationStrategies;
    private String summary;
}
