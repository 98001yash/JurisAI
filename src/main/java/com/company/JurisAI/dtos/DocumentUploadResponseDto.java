package com.company.JurisAI.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentUploadResponseDto {

    private String fileName;
    private String filePath;
    private long fileSize;
}
