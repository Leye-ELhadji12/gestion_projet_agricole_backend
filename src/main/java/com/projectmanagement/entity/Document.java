package com.projectmanagement.entity;

import com.projectmanagement.enums.DocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DocumentType type;
    private String originalFileName;
    private Long fileSize;
    private String fileType;
    @Lob
    private byte[] file;
    @ManyToOne
    private Activity activity;
}
