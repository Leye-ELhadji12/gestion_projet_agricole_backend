package com.projectmanagement.mapper;

import com.projectmanagement.dto.ProjectDTO;
import com.projectmanagement.entity.Project;
import com.projectmanagement.enums.ProjectStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProjectMapper {

    public Project toProject(ProjectDTO projectDTO) {
        Project project = new Project();
        BeanUtils.copyProperties(projectDTO, project);
        project.setStatus(ProjectStatus.valueOf(projectDTO.getStatus()));
        return project;
    }

    public ProjectDTO toProjectDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        BeanUtils.copyProperties(project, projectDTO);
        projectDTO.setStatus(project.getStatus().name());
        return projectDTO;
    }
}
