package com.projectmanagement.repository;

import com.projectmanagement.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projectmanagement.entity.Indicator;

import java.util.List;

public interface IndicatorRepository extends JpaRepository<Indicator, Long>{
    List<Indicator> findByProject(Project pjt);
}
