package com.company.JurisAI.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerdictAnalysisResponseDto {

    private VerdictStats verdictStats;
    private BiasIndicatorDto biasIndicators;
}
