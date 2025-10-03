package com.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectmanagement.entity.Deliverable;

import java.util.List;

public interface DeliverableRepository extends JpaRepository<Deliverable, Long>{
    List<Deliverable> findByActivityId(Long activityId);
}
