package com.jmatt.epd.api.rest;

import com.jmatt.epd.api.service.EmployeeDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeRequestMapper {
    EmployeeCreateRequest employeeDetailsToRequest(EmployeeDetails employeeDetails);
    EmployeeDetails requestToEmployeeDetails(EmployeeCreateRequest employeeCreateRequest);
}
