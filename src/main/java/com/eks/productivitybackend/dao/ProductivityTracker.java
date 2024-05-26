package com.eks.productivitybackend.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductivityTracker {
    @Id
    private Integer id;
    @ManyToOne
    private User user;
    @OneToOne
    private Workstation workstation;
}
