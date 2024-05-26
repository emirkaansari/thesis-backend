package com.eks.productivitybackend.repository;

import com.eks.productivitybackend.dao.Earning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EarningRepository extends JpaRepository<Earning, Integer> {
}
