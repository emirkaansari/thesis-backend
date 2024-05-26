package com.eks.productivitybackend.controller;

import com.eks.productivitybackend.dto.EmployeeDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class EmployeeSseController {
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    @GetMapping("/employee/sse/{userSubject}")
    @PreAuthorize("#auth.token.claims['sub'] == #userSubject")
    public SseEmitter employeeSse(@PathVariable("userSubject") String userSubject, JwtAuthenticationToken auth) {
        SseEmitter emitter = new SseEmitter();
        this.emitters.put(userSubject, emitter);
        return emitter;
    }

    public void sendSseEvent(String userSubject, EmployeeDto employeeDto) {
        SseEmitter emitter = this.emitters.get(userSubject);
        if (emitter != null) {
            try {
                emitter.send(employeeDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
