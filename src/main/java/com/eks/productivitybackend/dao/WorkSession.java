package com.eks.productivitybackend.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkSession {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE)
    private Integer id;
    @ManyToOne
    private Workstation workstation;
    @ManyToOne
    private Employee employee;
    private Long duration;
    private Date startTime;
    private Date endTime;
    @ManyToOne
    private Earning earning;
    @ManyToOne
    private ProductivityTracker productivityTracker;
    private Boolean isFinished = false;
}
