package com.krishna.Transaction_Service.producer;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.krishna.Transaction_Service.event.TransactionEvent;

@Component
public class TransactionProducer {

    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    @Value("${topic.transactions}")
    private String topic;
    

    public TransactionProducer(KafkaTemplate<String, TransactionEvent> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}
    
    private static final Logger logger =
            org.slf4j.LoggerFactory.getLogger(TransactionProducer.class);



	public void publish(TransactionEvent event) {

        kafkaTemplate.send(
                topic,
                event.getUserId(),
                event
        );

        logger.info(
                "Transaction Published: txnId={}, userId={}",
                event.getTransactionId(),
                event.getUserId()
        );
    }   
}