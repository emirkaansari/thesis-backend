package com.eks.productivitybackend.repository;

import com.eks.productivitybackend.dao.WorkSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkSessionRepository extends JpaRepository<WorkSession, Integer> {
}
