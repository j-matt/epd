package com.jmatt.epd.api.rest;

import lombok.Data;

@Data
public class EmployeeCreateRequest {
    private String name;
    private String surname;
}
