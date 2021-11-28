package com.jmatt.epd.api.service.external.producer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateEmployeeEvent {
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
}
