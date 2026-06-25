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
public class FeatureEvent {

    private String transactionId;

    private String userId;

    private Double amount;

    private boolean newDevice;

    private boolean countryMismatch;

    private double amountDeviationPercent;

    private int txnPerMinute;

    private Instant timestamp;
}