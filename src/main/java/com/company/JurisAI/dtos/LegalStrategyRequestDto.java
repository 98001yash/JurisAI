package com.company.JurisAI.dtos;


import lombok.Data;

@Data
public class LegalStrategyRequestDto {

    private Long caseId;
    private String legalIssue;
}
