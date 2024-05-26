package com.eks.productivitybackend.service;

import com.eks.productivitybackend.dto.WorkstationDto;
import com.eks.productivitybackend.mapper.WorkstationMapper;
import com.eks.productivitybackend.repository.WorkstationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkstationService {
    private final WorkstationRepository workstationRepository;
    private final WorkstationMapper workstationMapper;

    public List<WorkstationDto> getWorkstationsByUserId(String userId) {
        return workstationMapper.workstationToWorkstationDto(workstationRepository.findAllByUserId(userId));
    }
}
