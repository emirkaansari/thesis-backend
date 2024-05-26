package com.eks.productivitybackend.dao;

import com.eks.productivitybackend.config.CustomDoubleSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Earning {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE)
    private Integer id;
    @ManyToOne
    @JsonBackReference
    private Employee employee;
    private Date date;
    @JsonSerialize(using = CustomDoubleSerializer.class)
    private Double amount;
}
