package com.eks.productivitybackend.controller;

import com.eks.productivitybackend.dao.Workstation;
import com.eks.productivitybackend.dto.WorkstationDto;
import com.eks.productivitybackend.service.WorkstationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workstation")
@RequiredArgsConstructor
public class WorkstationController {

    private final WorkstationService workstationService;

    @GetMapping("/all/{userId}")
    @PreAuthorize("#auth.token.claims['sub'] == #userId")
    public ResponseEntity<List<WorkstationDto>> getAllWorkstations(@PathVariable("userId") String userId, JwtAuthenticationToken auth) {
        return ResponseEntity
                .ok()
                .body(workstationService.getWorkstationsByUserId(userId));
    }
}
