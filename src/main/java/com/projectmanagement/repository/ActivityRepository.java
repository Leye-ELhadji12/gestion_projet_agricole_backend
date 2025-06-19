package com.projectmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projectmanagement.entity.Activity;
import com.projectmanagement.entity.Project;

public interface ActivityRepository extends JpaRepository<Activity, Long>{
    Page<Activity> findByProject(Project project, Pageable pageable);
}
