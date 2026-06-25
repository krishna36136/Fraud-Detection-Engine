package com.krishna.Risk_Scoring_Service.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.krishna.Risk_Scoring_Service.event.FraudScoreEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FraudScoreProducer {

    private final KafkaTemplate<String, FraudScoreEvent> kafkaTemplate;

    @Value("${topic.fraud-scores}")
    private String fraudScoreTopic;

    public void publish(FraudScoreEvent event) {

        kafkaTemplate.send(
                fraudScoreTopic,
                event.getUserId(),
                event
        );
    }
}