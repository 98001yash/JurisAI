package com.company.JurisAI.dtos;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CaseOutcomePredictionResponseDto {

    private String predictedOutcome;
    private Double confidenceScore;
    private String caseFactsSummary;
    private LocalDateTime predictedDate;
}
