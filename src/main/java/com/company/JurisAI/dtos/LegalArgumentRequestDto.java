package com.company.JurisAI.dtos;


import lombok.Data;

import java.util.List;

@Data
public class LegalArgumentRequestDto {

    private String issue;
    private String caseType;
    private String facts;
    private String jurisdiction;
    private List<String> precedentCaseIds;
}
