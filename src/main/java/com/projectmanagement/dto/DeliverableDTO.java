package com.projectmanagement.dto;

import com.projectmanagement.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliverableDTO {
    private Long id;
    private DocumentType type;
    private String originalFileName;
    private Long fileSize;
    private String fileType;
    private byte[] file;
    private Long activityId;
}