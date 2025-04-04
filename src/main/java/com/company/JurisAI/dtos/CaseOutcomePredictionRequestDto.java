package com.company.JurisAI.dtos;


import lombok.Data;

@Data
public class CaseOutcomePredictionRequestDto {

    private Long caseId;
    private String caseFactsSummary;
}
