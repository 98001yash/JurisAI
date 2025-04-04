package com.company.JurisAI.dtos;


import lombok.Data;

import java.util.List;

@Data
public class LegalPrecedentResponseDto {

    private Long caseId;
    private List<String> relevantCases;
}
