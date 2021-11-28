package com.jmatt.epd.api.service;

import com.jmatt.epd.api.service.external.producer.CreateEmployeeEvent;
import com.jmatt.epd.api.service.external.producer.CreateEmployeeProfileEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeService implements Resume {

    private final CreateEmployeeProfileEventProducer createEmployeeProfileEventProducer;

    @Override
    public long createNewEmployee(EmployeeDetails details) {
        CreateEmployeeEvent createEmployeeEvent = CreateEmployeeEvent.builder()
                .name(details.getName())
                .surname(details.getSurname())
                .build();
        createEmployeeProfileEventProducer.sendCreateEmployeeEvent(createEmployeeEvent);
        return 0;
    }
}
