package com.eks.productivitybackend.service;


import com.eks.productivitybackend.controller.EmployeeSseController;
import com.eks.productivitybackend.dao.Earning;
import com.eks.productivitybackend.dao.Employee;
import com.eks.productivitybackend.dao.ProductivityTracker;
import com.eks.productivitybackend.dao.WorkSession;
import com.eks.productivitybackend.dto.EmployeeDto;
import com.eks.productivitybackend.mapper.EmployeeMapper;
import com.eks.productivitybackend.repository.ProductivityTrackerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ProductivityTrackerService {

    private final EmployeeService employeeService;
    private final ProductivityTrackerRepository productivityTrackerRepository;
    private final WorkSessionService workSessionService;
    private final EarningService earningService;
    private final EmployeeSseController employeeSseController;
    private final EmployeeMapper employeeMapper;

    public String handleProductivity(String cardId, String productivityTrackerId, String workSessionId) {
        Employee employee = employeeService.getEmployeeByCard(cardId);
        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }
        ProductivityTracker productivityTracker = productivityTrackerRepository
                .findById(Integer.valueOf(productivityTrackerId))
                .orElseThrow();

        if (!employee.getIsWorking()) {
            employee.setIsWorking(true);
            employee.setLastWorkedTime(new Date(System.currentTimeMillis()));
            employee.setLastWorkstation(productivityTracker.getWorkstation());

            employeeService.saveEmployee(employee);

            WorkSession workSession = workSessionService.saveWorkSession(
                    WorkSession.builder()
                            .employee(employee)
                            .isFinished(false)
                            .workstation(productivityTracker.getWorkstation())
                            .startTime(new Date(System.currentTimeMillis()))
                            .build()
            );

            employeeSseController.sendSseEvent(
                    employee.getUser().getId(),
                    employeeMapper.employeeToEmployeeDto(employeeService.getEmployeeByCard(cardId))
            );


            return workSession.getId().toString();

        } else {
            employee.setIsWorking(false);
            employee.setLastWorkedTime(new Date(System.currentTimeMillis()));
            WorkSession workSession = workSessionService.getById(workSessionId);
            if(workSession.getIsFinished()) {
                throw new RuntimeException("Work session already ended");
            }
            workSession.setIsFinished(true);
            workSession.setEndTime(new Date(System.currentTimeMillis()));
            workSession.setDuration((long) ((int) (workSession.getEndTime()
                    .getTime() - workSession.getStartTime().getTime()) / 1000));
            workSession.setEarning(
                    earningService
                            .addEarning(Earning.builder()
                            .employee(employee)
                            .date(new Date(System.currentTimeMillis()))
                            .amount(employee.getHourlyWage() * workSession.getDuration() / 3600)
                            .build())

            );
            employee.setLastEarning(workSession.getEarning());
            employeeService.saveEmployee(employee);

            employeeSseController.sendSseEvent(
                    employee.getUser().getId(),
                    employeeMapper.employeeToEmployeeDto(employeeService.getEmployeeByCard(cardId))
            );

            return "Work session ended successfully. Earning: " + workSession.getEarning().getAmount() + " USD " +
                    "Duration: " + workSession.getDuration() + " seconds";
        }
    }
}
