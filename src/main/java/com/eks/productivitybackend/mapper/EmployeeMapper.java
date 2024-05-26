package com.eks.productivitybackend.mapper;

import com.eks.productivitybackend.dao.Employee;
import com.eks.productivitybackend.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "lastWorkstation.name", target = "lastWorkstation")
    EmployeeDto employeeToEmployeeDto(Employee employee);

    List<EmployeeDto> employeesToEmployeeDtos(List<Employee> employees);

    @Mapping(source = "name", target = "name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastWorkstation", ignore = true)
    @Mapping(source = "hourlyWage", target = "hourlyWage")
    @Mapping(source = "cardId", target = "cardId")
    @Mapping(source = "lastEarning", target = "lastEarning")
    @Mapping(source = "isWorking", target = "isWorking")
    @Mapping(source = "lastWorkedTime", target = "lastWorkedTime")
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);
}