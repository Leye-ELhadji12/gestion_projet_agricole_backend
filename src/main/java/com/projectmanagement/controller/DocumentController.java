package com.projectmanagement.controller;

// This file defines the DocumentController, which acts as the entry point for
// handling HTTP requests related to document management in your application.
// It exposes RESTful API endpoints that clients (e.g., a frontend application)
// can interact with to perform operations on documents.
//
// Key responsibilities of a controller:
// 1. Request Mapping: Maps incoming HTTP requests to specific handler methods
//    based on URL paths and HTTP methods (e.g., @GetMapping, @PostMapping).
// 2. Request Parameter Handling: Extracts data from the HTTP request (e.g.,
//    @PathVariable, @RequestParam, @RequestBody) and binds it to Java objects.
// 3. Delegation to Service Layer: Delegates business logic operations to the
//    appropriate service layer (DocumentService) to keep the controller thin
//    and focused on web concerns.
// 4. Response Handling: Constructs HTTP responses, including status codes
//    (e.g., HttpStatus.CREATED, ResponseEntity.ok) and response bodies (DTOs).
// 5. Error Handling: Catches exceptions that might occur during request processing
//    and returns appropriate HTTP error responses.
//
// @RestController: A convenience annotation that combines @Controller and @ResponseBody,
//   indicating that this class handles RESTful requests and its methods return data
//   directly as JSON/XML.
// @RequestMapping("/api/documents"): Specifies the base URL path for all endpoints
//   in this controller.

import com.projectmanagement.dto.DocumentDTO;
import com.projectmanagement.service.DocumentService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/documents")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200/")
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/upload")
    public ResponseEntity<DocumentDTO> uploadDocument(
        @RequestPart MultipartFile file,
        @RequestPart DocumentDTO documentDTO) throws IOException {
        DocumentDTO savedDocument = documentService.saveDocument(file, documentDTO);
        return new ResponseEntity<>(savedDocument, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDTO> getDocumentById(@PathVariable Long id) {
        DocumentDTO documentDTO = documentService.getDocumentById(id);
        return ResponseEntity.ok(documentDTO);
    }

    @GetMapping
    public ResponseEntity<List<DocumentDTO>> getAllDocuments() {
        List<DocumentDTO> documents = documentService.getAllDocuments();
        return ResponseEntity.ok(documents);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<DocumentDTO>> getDocumentsByActivityId(@PathVariable Long activityId) {
        List<DocumentDTO> documents = documentService.getDocumentsByActivityId(activityId);
        return ResponseEntity.ok(documents);
    }
}