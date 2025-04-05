package com.company.JurisAI.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerdictStats {

    private int totalCases;
    private int guiltyCount;
    private int notGuiltyCount;
    private int settledCount;
}
