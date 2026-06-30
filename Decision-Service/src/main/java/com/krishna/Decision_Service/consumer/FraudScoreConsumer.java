package com.krishna.Decision_Service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.krishna.Decision_Service.event.DecisionEvent;
import com.krishna.Decision_Service.event.FraudScoreEvent;
import com.krishna.Decision_Service.producer.DecisionProducer;
import com.krishna.Decision_Service.service.DecisionEngineService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FraudScoreConsumer {

    private final DecisionEngineService decisionService;

    private final DecisionProducer producer;

    @KafkaListener(
            topics = "${topic.fraud-scores}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(FraudScoreEvent fraudScore) {

        DecisionEvent decision =
                decisionService.decide(fraudScore);

        producer.publish(decision);

        System.out.println(
                "Decision Published : "
                        + decision.getDecision()
                        + " | Transaction : "
                        + decision.getTransactionId()
        );
    }
}