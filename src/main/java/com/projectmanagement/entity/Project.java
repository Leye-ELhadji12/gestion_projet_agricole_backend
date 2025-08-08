package com.projectmanagement.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.projectmanagement.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalBudget;
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;
    @OneToMany(mappedBy = "project")
    private List<Activity> activities;
    @OneToMany(mappedBy = "project")
    private List<Indicator> indicators;
    @ManyToMany
    @JoinTable(
        name = "associate",
        joinColumns = @JoinColumn(name = "project_Id"),
        inverseJoinColumns = @JoinColumn(name = "responsible_Id")
    )
    private Set<Responsible> responsibles = new HashSet<>();

}
