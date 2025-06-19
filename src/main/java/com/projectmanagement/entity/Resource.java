package com.projectmanagement.entity;

import java.util.List;

import com.projectmanagement.enums.ResourceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private ResourceType type;
    private Double quantity;
    private String unit;
    @OneToMany(mappedBy = "resource")
    private List<Usage> usages;
}
