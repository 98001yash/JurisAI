package com.company.JurisAI.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentInfoDto {

    private Long id;
    private String fileName;
    private String filePath;
    private Long fileSize;
    private String uploadedAt;
}
