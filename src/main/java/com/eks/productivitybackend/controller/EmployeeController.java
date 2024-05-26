package com.eks.productivitybackend.controller;

import com.eks.productivitybackend.dao.Employee;
import com.eks.productivitybackend.dto.EmployeeDto;
import com.eks.productivitybackend.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/all/{userSubject}")
    @PreAuthorize("#auth.token.claims['sub'] == #userSubject")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(@PathVariable("userSubject")
                                                                 String userSubject,
                                                             JwtAuthenticationToken auth) {
        return ResponseEntity.ok(employeeService.getAllEmployees(userSubject));
    }

    @RequestMapping("/add/{userSubject}")
    @PreAuthorize("#auth.token.claims['sub'] == #userSubject")
    public ResponseEntity<EmployeeDto> addEmployee(@PathVariable("userSubject") String userSubject, @RequestBody EmployeeDto employeeDto, JwtAuthenticationToken auth) {
        return ResponseEntity.ok(employeeService.addEmployee(userSubject, employeeDto));
    }

    @RequestMapping("/delete/{userSubject}/{employeeId}")
    @PreAuthorize("#auth.token.claims['sub'] == #userSubject")
    public ResponseEntity<String> deleteEmployee(@PathVariable("userSubject") String userSubject, @PathVariable("employeeId") Integer employeeId, JwtAuthenticationToken auth) {
        employeeService.deleteEmployee(userSubject, employeeId);
        return ResponseEntity.ok("Employee deleted");
    }

    @PutMapping("/update/{userSubject}")
    @PreAuthorize("#auth.token.claims['sub'] == #userSubject")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("userSubject") String userSubject, @RequestBody EmployeeDto employeeDto, JwtAuthenticationToken auth) {
        return ResponseEntity.ok(employeeService.updateEmployee(userSubject, employeeDto));
    }
}