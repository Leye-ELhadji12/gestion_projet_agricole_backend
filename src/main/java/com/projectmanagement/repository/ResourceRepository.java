package com.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectmanagement.entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long>{

}
