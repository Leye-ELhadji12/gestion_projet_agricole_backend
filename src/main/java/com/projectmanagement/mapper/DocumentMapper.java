package com.projectmanagement.mapper;

import com.projectmanagement.dto.DocumentDTO;
import com.projectmanagement.entity.Document;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {

    public DocumentDTO toDocumentDTO(Document document) {
        if (document == null) {
            return null;
        }
        DocumentDTO dto = new DocumentDTO();
        dto.setId(document.getId());
        dto.setName(document.getName());
        dto.setType(document.getType());
        dto.setQuantity(document.getQuantity());
        dto.setOriginalFileName(document.getOriginalFileName());
        dto.setFileSize(document.getFileSize());
        dto.setFileType(document.getFileType());
        dto.setFile(document.getFile());
        if (document.getActivity() != null) {
            dto.setActivityId(document.getActivity().getId());
        }
        return dto;
    }

    public Document toDocument(DocumentDTO documentDTO) {
        if (documentDTO == null) {
            return null;
        }
        Document entity = new Document();
        entity.setId(documentDTO.getId());
        entity.setName(documentDTO.getName());
        entity.setType(documentDTO.getType());
        entity.setQuantity(documentDTO.getQuantity());
        entity.setOriginalFileName(documentDTO.getOriginalFileName());
        entity.setFileSize(documentDTO.getFileSize());
        entity.setFileType(documentDTO.getFileType());
        entity.setFile(documentDTO.getFile());
        // Activity will be set by the service layer
        return entity;
    }
}