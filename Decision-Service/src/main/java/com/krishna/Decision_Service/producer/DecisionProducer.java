package com.krishna.Decision_Service.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.krishna.Decision_Service.event.DecisionEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DecisionProducer {

    private final KafkaTemplate<String, DecisionEvent> kafkaTemplate;

    @Value("${topic.decisions}")
    private String decisionTopic;

    public void publish(DecisionEvent event) {

        kafkaTemplate.send(
                decisionTopic,
                event.getUserId(),
                event
        );
    }
}