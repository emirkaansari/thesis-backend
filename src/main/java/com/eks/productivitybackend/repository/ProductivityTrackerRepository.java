package com.eks.productivitybackend.repository;

import com.eks.productivitybackend.dao.ProductivityTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductivityTrackerRepository extends JpaRepository<ProductivityTracker, Integer> {
    ProductivityTracker findByWorkstationId(Integer workstationId);
}
