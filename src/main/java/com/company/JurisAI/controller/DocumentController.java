package com.company.JurisAI.controller;


import com.company.JurisAI.dtos.DocumentInfoDto;
import com.company.JurisAI.dtos.DocumentUploadResponseDto;
import com.company.JurisAI.entities.Document;
import com.company.JurisAI.repository.DocumentRepository;
import com.company.JurisAI.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;
    private final DocumentRepository documentRepository;

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
    public ResponseEntity<List<DocumentInfoDto>> getAllDocuments() {
        return ResponseEntity.ok(documentService.getAllDocuments());
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        try {
            File file = documentService.getDocumentFile(id);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment;filename=" + file.getName())
                    .contentLength(file.length())
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteDocument(@PathVariable Long id){
        documentService.deleteDocument(id);
        return ResponseEntity.ok("Document deleted successfully");
  }


}
