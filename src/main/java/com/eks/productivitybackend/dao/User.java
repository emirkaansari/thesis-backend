package com.eks.productivitybackend.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
@Data
public class User {
    @Id
    private String id;
    private String username;
    @OneToMany(mappedBy = "user")
    private List<Employee> employees;
    @OneToMany(mappedBy = "user")
    private List<ProductivityTracker> productivityTrackers;
    @OneToMany(mappedBy = "user")
    private List<Workstation> workstations;
}
