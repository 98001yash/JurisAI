package com.company.JurisAI.controller;


import com.company.JurisAI.advices.ApiResponse;
import com.company.JurisAI.dtos.CaseTimelineEventRequestDto;
import com.company.JurisAI.dtos.CaseTimelineEventResponseDto;
import com.company.JurisAI.service.CaseTimelineEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timeline-events")
@RequiredArgsConstructor
public class CaseTimelineEventController {

    private final CaseTimelineEventService caseTimelineEventService;

    @PostMapping
    public ResponseEntity<CaseTimelineEventResponseDto> createEvent(@RequestBody CaseTimelineEventRequestDto request){
        CaseTimelineEventResponseDto response = caseTimelineEventService.createEvent(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/case/{caseId}")
    public ResponseEntity<List<CaseTimelineEventResponseDto>> getEventsByCase(@PathVariable Long caseId){
        List<CaseTimelineEventResponseDto> responses =caseTimelineEventService.getEventByCaseId(caseId);
        return ResponseEntity.ok(responses);
    }


    @DeleteMapping("/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long eventId){
        caseTimelineEventService.deleteEvent(eventId);
        return ResponseEntity.ok("event deleted successfully");
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<ApiResponse<CaseTimelineEventResponseDto>> updateEvent(
            @PathVariable Long eventId,
            @RequestBody CaseTimelineEventRequestDto requestDto) {

        CaseTimelineEventResponseDto updatedEvent = caseTimelineEventService.updateTimelineEvent(eventId, requestDto);
        return ResponseEntity.ok(new ApiResponse<>(updatedEvent));
    }


}
