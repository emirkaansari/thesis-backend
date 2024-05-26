package com.eks.productivitybackend.service;

import com.eks.productivitybackend.dao.WorkSession;
import com.eks.productivitybackend.repository.WorkSessionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkSessionService {

    private final WorkSessionRepository workSessionRepository;

    @Transactional
    public WorkSession saveWorkSession(WorkSession workSession) {
         return workSessionRepository.save(workSession);
    }

    public WorkSession getById(String workSessionId) {
        return workSessionRepository.findById(Integer.valueOf(workSessionId)).orElseThrow();
    }
}
