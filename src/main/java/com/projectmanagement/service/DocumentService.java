package com.projectmanagement.service;

import com.projectmanagement.dto.DocumentDTO;
import com.projectmanagement.entity.Activity;
import com.projectmanagement.entity.Document;
import com.projectmanagement.exception.ActivityNotFoundException;
import com.projectmanagement.exception.DocumentNotFoundException;
import com.projectmanagement.mapper.DocumentMapper;
import com.projectmanagement.repository.ActivityRepository;
import com.projectmanagement.repository.DocumentRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DocumentService {

      private final DocumentRepository documentRepository;
      private final DocumentMapper documentMapper;
      private final ActivityRepository activityRepository;

      public DocumentDTO saveDocument(DocumentDTO documentDTO) {
            Document document = documentMapper.toDocument(documentDTO);
            if (documentDTO.getActivityId() != null) {
                  Activity activity = activityRepository.findById(documentDTO.getActivityId())
                        .orElseThrow(() -> new ActivityNotFoundException("Activity not found with ID: " + documentDTO.getActivityId()));
                  document.setActivity(activity);
            }
            Document savedDocument = documentRepository.save(document);
            return documentMapper.toDocumentDTO(savedDocument);
      }

      public DocumentDTO getDocumentById(Long id) {
            Document document = documentRepository.findById(id)
                  .orElseThrow(() -> new DocumentNotFoundException("Document not found with ID: " + id));
            return documentMapper.toDocumentDTO(document);
      }

      public List<DocumentDTO> getAllDocuments() {
            return documentRepository.findAll().stream()
                  .map(documentMapper::toDocumentDTO)
                  .toList();
      }

      public void deleteDocument(Long id) {
            documentRepository.deleteById(id);
      }

      public List<DocumentDTO> getDocumentsByActivityId(Long activityId) {
            return documentRepository.findByActivityId(activityId).stream()
                  .map(documentMapper::toDocumentDTO)
                  .toList();
      }
}