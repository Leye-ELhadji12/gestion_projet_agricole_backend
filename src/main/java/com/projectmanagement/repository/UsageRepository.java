package com.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectmanagement.entity.Usage;

public interface UsageRepository extends JpaRepository<Usage, Long>{

}
