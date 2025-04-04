package com.company.JurisAI.repository;

import com.company.JurisAI.dtos.CaseTimelineEventResponseDto;
import com.company.JurisAI.entities.CaseTimelineEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaseTimelineEventRepository extends JpaRepository<CaseTimelineEvent, Long> {

    List<CaseTimelineEvent> findByLegalCaseIdOrderByEventDateAsc(Long caseId);

    List<CaseTimelineEventResponseDto> findByLegalCaseId(Long caseId);

}
