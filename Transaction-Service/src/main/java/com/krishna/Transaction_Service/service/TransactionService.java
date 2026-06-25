package com.krishna.Transaction_Service.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.krishna.Transaction_Service.dto.CreateTransactionRequest;
import com.krishna.Transaction_Service.dto.CreateTransactionResponse;
import com.krishna.Transaction_Service.event.TransactionEvent;
import com.krishna.Transaction_Service.producer.TransactionProducer;

@Service
public class TransactionService {

    private final TransactionProducer producer;
    
    

    public TransactionService(TransactionProducer producer) {
		super();
		this.producer = producer;
	}



	public CreateTransactionResponse createTransaction(
            CreateTransactionRequest request) {

        String transactionId =
                UUID.randomUUID().toString();

        TransactionEvent event =
                new TransactionEvent.Builder()
                        .transactionId(transactionId)
                        .userId(request.getUserId())
                        .amount(request.getAmount())
                        .country(request.getCountry())
                        .deviceId(request.getDeviceId())
                        .timestamp(Instant.now())
                        .build();

        producer.publish(event);

        return new CreateTransactionResponse.Builder()
                .transactionId(transactionId)
                .status("ACCEPTED")
                .build();
    }
}