package com.projectmanagement.controller;

import com.projectmanagement.dto.ProjectDTO;
import com.projectmanagement.dto.ResponsibleDTO;
import com.projectmanagement.service.ProjectService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.HashSet;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO result = projectService.createProject(projectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<Page<ProjectDTO>> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ) {
        return  ResponseEntity.ok(projectService.getAllProjects(page, size));
    }

    @PatchMapping("/update/{projectId}")
    public ResponseEntity<ProjectDTO> updateProject(
            @PathVariable Long projectId,
            @RequestBody ProjectDTO projectDTO) {
        return ResponseEntity.ok(projectService.updateProject(projectId, projectDTO));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.getProjectById(projectId));
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<ProjectDTO> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{projectId}/responsibles")
    public ResponseEntity<ProjectDTO> addResponsiblesToProject(
            @PathVariable Long projectId,
            @RequestBody List<Long> responsibleIds) {
        return ResponseEntity.ok(projectService.addResponsiblesToProject(projectId, new HashSet<>(responsibleIds)));
    }

    @DeleteMapping("/{projectId}/responsibles")
    public ResponseEntity<ProjectDTO> removeResponsiblesFromProject(
            @PathVariable Long projectId,
            @RequestBody List<Long> responsibleIds) {
        return ResponseEntity.ok(
            projectService.removeResponsiblesFromProject(projectId, new HashSet<>(responsibleIds))
        );
    }

    @GetMapping("/{projectId}/responsibles")
    public ResponseEntity<List<ResponsibleDTO>> getProjectResponsibles(
            @PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.getProjectResponsibles(projectId));
    }
}
