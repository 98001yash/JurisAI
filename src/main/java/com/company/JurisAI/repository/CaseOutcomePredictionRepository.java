package com.company.JurisAI.repository;

import com.company.JurisAI.entities.CaseOutcomesPrediction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CaseOutcomePredictionRepository extends JpaRepository<CaseOutcomesPrediction,Long> {

    Optional<CaseOutcomesPrediction> findByLegalCaseId(Long caseId);
}
