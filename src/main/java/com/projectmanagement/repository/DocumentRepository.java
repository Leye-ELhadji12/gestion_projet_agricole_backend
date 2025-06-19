package com.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectmanagement.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{

}
