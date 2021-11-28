package com.jmatt.epd.epdprofiles.service.external.configuration;

import com.jmatt.epd.epdprofiles.service.external.consumer.CreateEmployeeEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Strings.isNullOrEmpty;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${app.kafka.group.id.suffix}")
    private String groupIdSuffix;

    @Bean
    public ConsumerFactory<String, CreateEmployeeEvent> eventConsumerFactory() {
        Map<String, Object> props = getCommonProps();
        props.put("spring.json.value.default.type", CreateEmployeeEvent.class.getCanonicalName());

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CreateEmployeeEvent> eventKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CreateEmployeeEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(eventConsumerFactory());
        return factory;
    }

    private Map<String, Object> getCommonProps() {
        String groupId = appName;
        if (!isNullOrEmpty(groupIdSuffix)) {
            groupId = appName + "-" + groupIdSuffix;
        }

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put("spring.json.use.type.headers", "false");

        return props;
    }
}
