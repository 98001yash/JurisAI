package com.company.JurisAI.controller;


import com.company.JurisAI.dtos.DocumentUploadResponseDto;
import com.company.JurisAI.entities.Document;
import com.company.JurisAI.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/upload")
    public ResponseEntity<DocumentUploadResponseDto> uploadFile(@RequestParam("file")MultipartFile file){
        try{
            DocumentUploadResponseDto response = documentService.saveFile(file);
            return ResponseEntity.ok(response);
        }catch(IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Document>> getAllDocuments() {
        return ResponseEntity.ok(documentService.getAllDocuments());
    }

}
