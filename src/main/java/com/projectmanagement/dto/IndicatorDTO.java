package com.projectmanagement.dto;

import lombok.Data;

@Data
public class IndicatorDTO {
    private Long id;
    private String name;
    private String description;
    private String unit;
    private Double targetValue;
    private Double actualValue;
}
