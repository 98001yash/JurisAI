package com.company.JurisAI.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaseTimelineEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventTitle;
    private String description;

    private LocalDateTime eventTime;


    @ManyToOne
    @JoinColumn(name = "case_id")
    private LegalCase legalCase;
}
