package com.company.JurisAI.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JudgeBiasDto {

    private String judgeName;
    private double guiltyRate;
}
