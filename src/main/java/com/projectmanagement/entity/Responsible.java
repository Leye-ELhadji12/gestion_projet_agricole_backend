package com.projectmanagement.entity;

import com.projectmanagement.enums.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Responsible {
    @Id
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private Role role;
    private String phone;
    @ManyToMany(mappedBy = "responsibles")
    private List<Project> projects;
}
