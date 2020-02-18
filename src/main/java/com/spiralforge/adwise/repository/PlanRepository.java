package com.spiralforge.adwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spiralforge.adwise.entity.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

}
