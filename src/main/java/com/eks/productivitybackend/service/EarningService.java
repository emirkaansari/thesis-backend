package com.eks.productivitybackend.service;

import com.eks.productivitybackend.dao.Earning;
import com.eks.productivitybackend.dao.Employee;
import com.eks.productivitybackend.repository.EarningRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EarningService {
    private final EarningRepository earningRepository;

    @Transactional
    public Earning addEarning(Earning earning) {
        return earningRepository.save(earning);
    }
}
