package com.company.JurisAI.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaseTimelineEventResponseDto {

    private String eventTitle;
    private String description;
    private LocalDate eventDate;
}
