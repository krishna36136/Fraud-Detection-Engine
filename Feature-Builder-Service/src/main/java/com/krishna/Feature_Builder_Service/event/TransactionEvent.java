package com.krishna.Feature_Builder_Service.event;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEvent {

    private String transactionId;

    private String userId;

    private Double amount;

    private String country;

    private String deviceId;

    private Instant timestamp;
}