package com.eks.productivitybackend.dto;

import com.eks.productivitybackend.dao.Earning;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private String id;
    private String name;
    private String lastWorkstation;
    private List<Earning> earnings;
    private Double hourlyWage;
    private String cardId;
    private Earning lastEarning;
    private Boolean isWorking;
    private String lastWorkedTime;
}
