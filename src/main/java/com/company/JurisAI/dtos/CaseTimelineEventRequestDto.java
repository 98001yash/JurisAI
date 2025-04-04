package com.company.JurisAI.dtos;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CaseTimelineEventRequestDto {

    private Long caseId;
    private String eventTitle;
    private String description;
    private LocalDateTime eventTime;
}
