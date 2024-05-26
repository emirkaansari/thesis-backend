package com.eks.productivitybackend.service;

import com.eks.productivitybackend.dao.User;
import com.eks.productivitybackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(String userSubject) {
        return userRepository.findById(userSubject)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}