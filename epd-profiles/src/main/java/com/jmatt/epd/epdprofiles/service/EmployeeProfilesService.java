package com.jmatt.epd.epdprofiles.service;

import com.jmatt.epd.epdprofiles.model.EmployeeDocument;
import com.jmatt.epd.epdprofiles.repository.EmployeeProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeeProfilesService {

    private final MongoTemplate mongoTemplate;
    private final EmployeeProfileRepository employeeProfileRepository;

    public List<EmployeeProfile> getEmployeeProfiles() {
        List<EmployeeDocument> employeeDocuments = employeeProfileRepository.findAll();

        return employeeDocuments.stream()
                .map(employeeDocument -> new EmployeeProfile(employeeDocument.getName(), employeeDocument.getSurname()))
                .collect(Collectors.toList());
    }
}
