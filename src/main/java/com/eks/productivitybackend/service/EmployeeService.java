package com.eks.productivitybackend.service;

import com.eks.productivitybackend.dao.Employee;
import com.eks.productivitybackend.dto.EmployeeDto;
import com.eks.productivitybackend.mapper.EmployeeMapper;
import com.eks.productivitybackend.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final UserService userService;


    public List<EmployeeDto> getAllEmployees(String userSubject) {
        return employeeMapper.employeesToEmployeeDtos(
                employeeRepository.findAllByUserId(userSubject));
    }

    @Transactional
    public EmployeeDto addEmployee(String userSubject, EmployeeDto employeeDto) {
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        employee.setUser(userService.getUser(userSubject));
        return employeeMapper.employeeToEmployeeDto(employeeRepository.save(employee));
    }

    @Transactional
    public void deleteEmployee(String userSubject, Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        if (!employee.getUser().getId().equals(userSubject)) {
            throw new RuntimeException("Employee not found");
        }
        employeeRepository.delete(employee);
    }

    @Transactional
    public EmployeeDto updateEmployee(String userSubject, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(Integer.valueOf(employeeDto.getId()))
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        if (!employee.getUser().getId().equals(userSubject)) {
            throw new RuntimeException("Employee not found");
        }
        employee.setName(employeeDto.getName());
        employee.setHourlyWage(employeeDto.getHourlyWage());
        employee.setCardId(employeeDto.getCardId());
        return employeeMapper.employeeToEmployeeDto(employeeRepository.save(employee));
    }

    public Employee getEmployeeByCard(String cardId) {
        return employeeRepository.findByCardId(cardId);
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
