package com.projectmanagement.dto;

// This file defines the DocumentDTO (Data Transfer Object) class.
// DTOs are used to transfer data between different layers of an application,
// especially between the web layer (controllers) and the service layer.
//
// The main purposes of DocumentDTO are:
// 1. Decoupling: It prevents exposing the internal database entity structure
//    (Document.java) directly through the API. This allows you to change the
//    database schema without necessarily breaking the API contract.
// 2. API Specificity: It can be tailored to include only the fields relevant
//    for a specific API operation, or to combine data from multiple entities.
//    For example, it uses 'activityId' instead of a full 'Activity' object.
// 3. Data Validation: While not explicitly shown here, DTOs are often the place
//    where input validation annotations (e.g., @NotNull, @Size) are applied
//    to ensure incoming data meets business rules.
// 4. Reduced Data Transfer: By selecting only necessary fields, it can reduce
//    the payload size over the network.
//
// Lombok annotations (@Data, @NoArgsConstructor, @AllArgsConstructor) are used
// to reduce boilerplate code for getters, setters, and constructors.

import com.projectmanagement.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    private Long id;
    private String name;
    private DocumentType type;
    private Integer quantity;
    private String originalFileName;
    private Long fileSize;
    private String fileType;
    private byte[] file;
    private Long activityId;
}