package com.company.JurisAI.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaseOutcomesPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String predictedOutcome;
    private Double confidenceScore;
    private String caseFactsSummary;


    @OneToOne
    private LegalCase legalCase;
}
