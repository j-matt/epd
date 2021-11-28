package com.jmatt.epd.epdprofiles.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@Document(collection = "employee")
public class EmployeeDocument {
    @Id
    private long id;
    private String name;
    private String surname;
    private Instant creationDate;
}