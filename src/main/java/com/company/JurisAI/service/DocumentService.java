package com.company.JurisAI.service;


import com.company.JurisAI.dtos.DocumentInfoDto;
import com.company.JurisAI.dtos.DocumentUploadResponseDto;
import com.company.JurisAI.entities.Document;
import com.company.JurisAI.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DocumentService {

    private final String uploadDir = "uploads/";
    private final DocumentRepository documentRepository;

    public DocumentUploadResponseDto saveFile(MultipartFile file) throws IOException {
        if(file.isEmpty()){
            throw new IOException("File is empty");
        }
        File dir = new File(uploadDir);
        if(!dir.exists()){
            dir.mkdirs();
        }

        // save the file to the fileSystem
        String filePath = uploadDir + file.getOriginalFilename();
        File destFile = new File(filePath);
        file.transferTo(destFile);

        // save metadata to the database
        Document document = new Document();
        document.setFileName(file.getOriginalFilename());
        document.setFilePath(file.getContentType());
        document.setFilePath(filePath);
        document.setFileSize(file.getSize());

        document = documentRepository.save(document);
        return new DocumentUploadResponseDto(document.getFileName(), document.getFilePath(), document.getFileSize());
    }

    public List<DocumentInfoDto> getAllDocuments() {
        return documentRepository.findAll().stream().map(doc ->
                new DocumentInfoDto(
                        doc.getId(),
                        doc.getFileName(),
                        doc.getFilePath(),
                        doc.getFileSize(),
                        doc.getUploadedAt().toString()
                )
        ).toList();
    }


    public File getDocumentFile(Long id) throws IOException {
        Document doc = documentRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("Document not found"));
        return new File(doc.getFilePath());
    }

    public void deleteDocument(Long id){
        Document document = documentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Document not found with ID: "+ id));

        // delete file from storage
        File file = new File(document.getFilePath());
        if(file.exists()){
            file.delete();
        }
        // delete record from db
        documentRepository.deleteById(id);
    }

}
