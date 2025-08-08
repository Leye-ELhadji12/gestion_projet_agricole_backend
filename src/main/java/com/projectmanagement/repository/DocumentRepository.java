package com.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectmanagement.entity.Document;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long>{
    List<Document> findByActivityId(Long activityId);
}
