package com.company.JurisAI.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dcouments")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    private String filePath;
    private Long fileSize;


    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadedAt;


    @ManyToOne
    @JoinColumn(name = "case_id")
    private LegalCase legalCase;
}
