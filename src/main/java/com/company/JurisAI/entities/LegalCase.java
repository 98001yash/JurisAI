package com.company.JurisAI.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "legal_cases", indexes = {
        @Index(name = "idx_title", columnList = "title"),
        @Index(name = "idx_description", columnList = "description"),
        @Index(name = "idx_court_name", columnList = "court_name")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LegalCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private String courtName;

    private String judge;

    @ElementCollection
    private List<String> lawyersInvolved;

    private String caseStatus;

    private LocalDate filedDate;

    private LocalDate verdictDate;
}
