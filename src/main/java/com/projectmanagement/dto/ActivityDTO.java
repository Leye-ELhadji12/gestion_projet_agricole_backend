package com.projectmanagement.dto;

import com.projectmanagement.enums.ActivityStatus;
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
    private ActivityStatus status;
}
