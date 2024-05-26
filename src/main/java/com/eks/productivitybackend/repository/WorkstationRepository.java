package com.eks.productivitybackend.repository;

import com.eks.productivitybackend.dao.Workstation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkstationRepository extends JpaRepository<Workstation, Integer> {
    List<Workstation> findAllByUserId(String userId);
}
