package com.projectmanagement.service;

import com.projectmanagement.dto.ProjectDTO;
import com.projectmanagement.entity.Project;
import com.projectmanagement.enums.ProjectStatus;
import com.projectmanagement.exception.ProjectNotFoundException;
import com.projectmanagement.mapper.ProjectMapper;
import com.projectmanagement.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = projectMapper.toProject(projectDTO);
        return projectMapper.toProjectDTO(projectRepository.save(project));
    }

    public Page<ProjectDTO> getAllProjects(int page, int size) {
        return projectRepository.findAll(PageRequest.of(page,size))
                .map(projectMapper::toProjectDTO);
    }

    public ProjectDTO getProjectById(Long id) {
         Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found : "+id));
         return projectMapper.toProjectDTO(project);
    }

    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project id: "+id+" not found"));
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setStatus(ProjectStatus.valueOf(projectDTO.getStatus()));
        project.setEndDate(projectDTO.getEndDate());
        project.setStartDate(projectDTO.getStartDate());
        project.setTotalBudget(projectDTO.getTotalBudget());
        projectRepository.save(project);
        return projectMapper.toProjectDTO(project);
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                        .orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        projectRepository.delete(project);
    }
}
