package com.jmatt.epd.epdprofiles.api;

import com.jmatt.epd.epdprofiles.service.EmployeeProfile;
import com.jmatt.epd.epdprofiles.service.EmployeeProfilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/profiles/employee")
@RequiredArgsConstructor
public class EmployeeProfileController {

    private final EmployeeProfilesService employeeProfilesService;
    private final EmployeeProfileMapper mapper;

    @GetMapping
    public ResponseEntity<List<EmployeeProfileResponse>> getEmployeeProfiles(){
        List<EmployeeProfile> employeeProfiles = employeeProfilesService.getEmployeeProfiles();
        List<EmployeeProfileResponse> employeeProfileResponses = mapper.toEmployeeProfileResponses(employeeProfiles);
        return ResponseEntity.ok(employeeProfileResponses);
    }
}
