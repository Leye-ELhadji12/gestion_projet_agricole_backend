package com.projectmanagement.mapper;

import org.springframework.stereotype.Component;

import com.projectmanagement.dto.DeliverableDTO;
import com.projectmanagement.entity.Deliverable;

@Component
public class DeliverableMapper {

    public DeliverableDTO toDocumentDTO(Deliverable document) {
        DeliverableDTO dto = new DeliverableDTO();
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

    public Deliverable toDocument(DeliverableDTO documentDTO) {
        Deliverable entity = new Deliverable();
        entity.setType(documentDTO.getType());
        entity.setOriginalFileName(documentDTO.getOriginalFileName());
        entity.setFileSize(documentDTO.getFileSize());
        entity.setFileType(documentDTO.getFileType());
        entity.setFile(documentDTO.getFile());
        return entity;
    }
}