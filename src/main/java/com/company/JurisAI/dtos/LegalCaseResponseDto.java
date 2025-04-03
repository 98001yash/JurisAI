package com.company.JurisAI.dtos;


import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class LegalCaseResponseDto {

    private Long id;
    private String title;
    private String description;
    private LocalDate filedDate;
    private LocalDate verdictDate;
    private String courtName;
    private String judge;
    private List<String> lawyersInvolved;
    private String caseStatus;
}
