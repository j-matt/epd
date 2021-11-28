package com.jmatt.epd.epdprofiles.service.external.consumer;


import com.jmatt.epd.epdprofiles.model.EmployeeDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Instant;


@Component
@Slf4j
@RequiredArgsConstructor
public class MessageConsumer {

    @Autowired
    MongoTemplate mongoTemplate;

    @KafkaListener(
            topics = "create-employee",
            containerFactory = "eventKafkaListenerContainerFactory"
    )
    public void readCommand(CreateEmployeeEvent createEmployeeEvent) {
        mongoTemplate.save(EmployeeDocument.builder()
                .name(createEmployeeEvent.getName())
                .surname(createEmployeeEvent.getSurname())
                .creationDate(Instant.now()), "employee");

        log.info("Event received: name {} surname {}", createEmployeeEvent.getName(), createEmployeeEvent.getSurname());
    }
}
