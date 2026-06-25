package com.krishna.Feature_Builder_Service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.krishna.Feature_Builder_Service.event.FeatureEvent;
import com.krishna.Feature_Builder_Service.event.TransactionEvent;
import com.krishna.Feature_Builder_Service.producer.FeatureProducer;
import com.krishna.Feature_Builder_Service.service.FeatureExtractionService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransactionConsumer {

    private final FeatureExtractionService service;

    private final FeatureProducer producer;

    @KafkaListener(
            topics = "${topic.transactions}"
    )
    public void consume(
            TransactionEvent txn) {

        FeatureEvent features =
                service.buildFeatures(txn);

        producer.publish(features);
    }
}