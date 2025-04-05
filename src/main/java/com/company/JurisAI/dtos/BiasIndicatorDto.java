package com.company.JurisAI.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BiasIndicatorDto {

    private String genderBias;
    private String representationBias;
    private List<JudgeBiasDto> judgeBias;
}
