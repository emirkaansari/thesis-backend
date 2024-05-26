package com.eks.productivitybackend.mapper;

import com.eks.productivitybackend.dao.Workstation;
import com.eks.productivitybackend.dto.WorkstationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkstationMapper {
    WorkstationMapper INSTANCE = Mappers.getMapper(WorkstationMapper.class);

    WorkstationDto workstationToWorkstationDto(Workstation workstation);

    List<WorkstationDto> workstationToWorkstationDto(List<Workstation> workstations);
}
