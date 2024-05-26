package com.eks.productivitybackend.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @ManyToOne
    private User user;
    private String name;
    private Boolean isWorking = false;
    private Date lastWorkedTime;
    @ManyToOne
    private Workstation lastWorkstation;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Earning> earnings;
    private Double hourlyWage;
    private String cardId;
    @OneToOne
    private Earning lastEarning;
}
