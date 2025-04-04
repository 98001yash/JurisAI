package com.company.JurisAI.dtos;

import lombok.Data;

@Data
public class LegalPrecedentRequestDto {

    private Long caseId;
    private String legalIssue;
}
