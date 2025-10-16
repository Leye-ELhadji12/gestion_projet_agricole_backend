package com.projectmanagement.mapper;

import com.projectmanagement.dto.ProjectDTO;
import com.projectmanagement.entity.Project;
import com.projectmanagement.enums.ProjectStatus;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class ProjectMapper {

    private final ResponsibleMapper responsibleMapper;

    public Project toProject(ProjectDTO projectDTO) {
        Project project = new Project();
        BeanUtils.copyProperties(projectDTO, project);
        project.setStatus(ProjectStatus.valueOf(projectDTO.getStatus()));
        if (projectDTO.getResponsibles() != null) {
            project.setResponsibles(new HashSet<>(projectDTO.getResponsibles().stream()
                .map(responsibleMapper::toResponsible)
                .toList()));
        }
        return project;
    }

    public ProjectDTO toProjectDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        BeanUtils.copyProperties(project, projectDTO);
        projectDTO.setStatus(project.getStatus().name());
        if (project.getResponsibles() != null) {
            projectDTO.setResponsibles(new HashSet<>(project.getResponsibles().stream()
                .map(responsibleMapper::toResponsibleDTO)
                .toList()));
        }
        return projectDTO;
    }
}
