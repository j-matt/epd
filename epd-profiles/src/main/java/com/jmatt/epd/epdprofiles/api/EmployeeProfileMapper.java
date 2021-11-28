package com.jmatt.epd.epdprofiles.api;

import com.jmatt.epd.epdprofiles.service.EmployeeProfile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeProfileMapper {
    EmployeeProfileResponse toEmployeeProfileResponse(EmployeeProfile employeeProfile);
    List<EmployeeProfileResponse> toEmployeeProfileResponses(List<EmployeeProfile> employeeProfiles);
}
