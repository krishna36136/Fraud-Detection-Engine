package com.krishna.Risk_Scoring_Service.consumer;



import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.krishna.Risk_Scoring_Service.event.FeatureEvent;
import com.krishna.Risk_Scoring_Service.event.FraudScoreEvent;
import com.krishna.Risk_Scoring_Service.producer.FraudScoreProducer;
import com.krishna.Risk_Scoring_Service.service.RiskScoringService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class FeatureConsumer {

    private final RiskScoringService riskScoringService;
    private final FraudScoreProducer fraudScoreProducer;

    @KafkaListener(
            topics = "${topic.features}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(FeatureEvent featureEvent) {

        log.info("Received Feature Event: {}", featureEvent);

        FraudScoreEvent fraudScoreEvent =
                riskScoringService.score(featureEvent);

        log.info("Generated Fraud Score Event: {}", fraudScoreEvent);

        fraudScoreProducer.publish(fraudScoreEvent);
    }
}