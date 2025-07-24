package com.projectmanagement.repository;

// This file defines the DocumentRepository interface.
// In Spring Data JPA, a repository is an interface that provides mechanisms
// for performing CRUD (Create, Read, Update, Delete) operations on entities.
// By extending JpaRepository, you get a wide range of standard database
// operations out-of-the-box without writing any implementation code.
//
// Key aspects:
// - JpaRepository<Document, Long>: Specifies that this repository is for the
//   Document entity and that its primary key type is Long.
// - findByActivityId(Long activityId): This is a custom query method.
//   Spring Data JPA automatically generates the implementation for this method
//   based on its name, allowing you to find documents associated with a specific activity.

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectmanagement.entity.Document;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long>{
    List<Document> findByActivityId(Long activityId);
}
