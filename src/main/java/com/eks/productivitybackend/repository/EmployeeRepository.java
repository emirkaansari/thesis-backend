package com.eks.productivitybackend.repository;

import com.eks.productivitybackend.dao.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findAllByUserId(String userId);
    Employee findByCardId(String cardId);
}
