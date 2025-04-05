package com.company.JurisAI.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerdictAnalysisRequestDto {
    private Long caseId;
}
