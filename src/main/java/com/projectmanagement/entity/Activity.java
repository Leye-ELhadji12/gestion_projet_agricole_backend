package com.projectmanagement.entity;

import java.time.LocalDate;
import java.util.List;

import com.projectmanagement.enums.ActivityStatus;
import com.projectmanagement.enums.Priorite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private LocalDate plannedStartDate;
    private LocalDate plannedEndDate;
    private LocalDate actualStartDate;
    @Enumerated(EnumType.STRING)
    private Priorite priorite;
    @Enumerated(EnumType.STRING)
    private ActivityStatus status;
    @ManyToOne
    private Project project;
    @OneToMany(mappedBy = "activity")
    private List<Document> documents;
    @OneToMany(mappedBy = "activity")
    private List<Usage> usages;
}
