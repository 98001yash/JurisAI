package com.company.JurisAI.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CaseTimelineEventResponseDto {

    private String eventTitle;
    private String description;
    private LocalDateTime eventTime;
}
