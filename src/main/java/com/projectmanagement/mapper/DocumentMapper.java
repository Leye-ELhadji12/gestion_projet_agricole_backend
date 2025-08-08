package com.projectmanagement.mapper;

import com.projectmanagement.dto.DocumentDTO;
import com.projectmanagement.entity.Document;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {

    public DocumentDTO toDocumentDTO(Document document) {
        DocumentDTO dto = new DocumentDTO();
        dto.setId(document.getId());
        dto.setType(document.getType());
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
        Document entity = new Document();
        entity.setType(documentDTO.getType());
        entity.setOriginalFileName(documentDTO.getOriginalFileName());
        entity.setFileSize(documentDTO.getFileSize());
        entity.setFileType(documentDTO.getFileType());
        entity.setFile(documentDTO.getFile());
        return entity;
    }
}