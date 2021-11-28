package com.jmatt.epd.api.service.external.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateEmployeeProfileEventProducer {

    private final KafkaTemplate<String, CreateEmployeeEvent> kafkaTemplate;

    @Value("${topic.name.create-employee}")
    private String createEmployeeTopic;

    public void sendCreateEmployeeEvent(CreateEmployeeEvent createEmployeeEvent){
        log.info("Sending event for name {} and surname {}", createEmployeeEvent.getName(), createEmployeeEvent.getSurname());
        ListenableFuture<SendResult<String, CreateEmployeeEvent>> result = kafkaTemplate.send(createEmployeeTopic, createEmployeeEvent);
        if(result.isDone()){
            log.info("Event sent  for name {} and surname {}", createEmployeeEvent.getName(), createEmployeeEvent.getSurname());
        }
    }

}
