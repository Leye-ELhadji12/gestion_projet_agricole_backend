package com.projectmanagement.service;

import com.projectmanagement.dto.ProjectDTO;
import com.projectmanagement.entity.Project;
import com.projectmanagement.entity.Responsible;
import com.projectmanagement.enums.ProjectStatus;
import com.projectmanagement.exception.ProjectNotFoundException;
import com.projectmanagement.mapper.ProjectMapper;
import com.projectmanagement.mapper.ResponsibleMapper;
import com.projectmanagement.repository.ProjectRepository;
import com.projectmanagement.repository.ResponsibleRepository;

import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.projectmanagement.dto.ResponsibleDTO;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final ResponsibleRepository responsibleRepository;
    private final ResponsibleMapper responsibleMapper;

    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = projectMapper.toProject(projectDTO);
        return projectMapper.toProjectDTO(projectRepository.save(project));
    }

    public Page<ProjectDTO> getAllProjects(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        return projectRepository.findAll(pageRequest)
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

    // public ProjectDTO addResponsiblesToProject(Long projectId, List<Long> responsibleIds) {
    //     Project project = projectRepository.findById(projectId)
    //             .orElseThrow(() -> new ProjectNotFoundException("Project not found: " + projectId));
    //     List<Responsible> responsibles = responsibleRepository.findAllById(responsibleIds);
    //     project.getResponsibles().addAll(responsibles);
    //     Project savedProject = projectRepository.save(project);
    //     return projectMapper.toProjectDTO(savedProject);
    // }

    public ProjectDTO addResponsiblesToProject(Long projectId, Set<Long> responsibleIds) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found: " + projectId));
        Set<Responsible> responsibles = new HashSet<>(responsibleRepository.findAllById(responsibleIds));
        project.getResponsibles().addAll(responsibles);
        return projectMapper.toProjectDTO(projectRepository.save(project));
    }

    public ProjectDTO removeResponsiblesFromProject(Long projectId, Set<Long> responsibleIds) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found: " + projectId));
        Set<Responsible> responsiblesToRemove = project.getResponsibles().stream()
                .filter(r -> responsibleIds.contains(r.getId()))
                .collect(Collectors.toSet());
        project.getResponsibles().removeAll(responsiblesToRemove);
        return projectMapper.toProjectDTO(projectRepository.save(project));
    }

    public List<ResponsibleDTO> getProjectResponsibles(Long projectId) {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new ProjectNotFoundException("Project not found: " + projectId));
        return project.getResponsibles().stream()
            .map(responsibleMapper::toResponsibleDTO)
            .toList();
    }
}
