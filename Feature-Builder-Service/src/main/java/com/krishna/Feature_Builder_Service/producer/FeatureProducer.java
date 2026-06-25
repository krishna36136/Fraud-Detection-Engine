package com.krishna.Feature_Builder_Service.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.krishna.Feature_Builder_Service.event.FeatureEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FeatureProducer {

    private final KafkaTemplate<String,
            FeatureEvent> kafkaTemplate;

    @Value("${topic.features}")
    private String topic;

    public void publish(
            FeatureEvent event) {

        kafkaTemplate.send(
                topic,
                event.getUserId(),
                event
        );
    }
}