package com.company.JurisAI.service;


import com.company.JurisAI.dtos.CaseTimelineEventRequestDto;
import com.company.JurisAI.dtos.CaseTimelineEventResponseDto;
import com.company.JurisAI.entities.CaseTimelineEvent;
import com.company.JurisAI.entities.LegalCase;
import com.company.JurisAI.repository.CaseTimelineEventRepository;
import com.company.JurisAI.repository.LegalCaseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CaseTimelineEventService {

    private final CaseTimelineEventRepository caseTimelineEventRepository;
    private final LegalCaseRepository legalCaseRepository;
    private final ModelMapper modelMapper;

    public CaseTimelineEventResponseDto createEvent(CaseTimelineEventRequestDto request){
        LegalCase legalCase = legalCaseRepository.findById(request.getCaseId())
                .orElseThrow(()->new RuntimeException("Legal case not found"));

        CaseTimelineEvent event = new CaseTimelineEvent();
        event.setEventTitle(request.getEventTitle());
        event.setDescription(request.getDescription());
        event.setEventDate(request.getEventDate());
        event.setLegalCase(legalCase);

        CaseTimelineEvent savedEvent = caseTimelineEventRepository.save(event);
        return modelMapper.map(savedEvent, CaseTimelineEventResponseDto.class);
    }

    public List<CaseTimelineEventResponseDto> getEventByCaseId(Long caseId) {
        return caseTimelineEventRepository.findByLegalCaseIdOrderByEventDateAsc(caseId)
                .stream()
                .map(event -> modelMapper.map(event, CaseTimelineEventResponseDto.class))
                .toList();
    }

        public void deleteEvent(Long eventId){
        if(!caseTimelineEventRepository.existsById(eventId)){
            throw new RuntimeException("Event not found with ID: "+eventId);
        }
        caseTimelineEventRepository.deleteById(eventId);
    }
}
