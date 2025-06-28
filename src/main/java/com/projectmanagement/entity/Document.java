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
    private String name;
    @Enumerated(EnumType.STRING)
    private DocumentType type;
    private Integer quantity;
    private String filePath;
    private String contentType;
    private Long size;
    private String originalFileName;
    @ManyToOne
    private Activity activity;
}
