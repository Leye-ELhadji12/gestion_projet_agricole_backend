package com.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectmanagement.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
