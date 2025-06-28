package com.projectmanagement.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ActivityDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate plannedStartDate;
    private LocalDate plannedEndDate;
    private LocalDate actualStartDate;
    private String priorite;
    private String status;
}
