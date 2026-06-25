package com.krishna.Risk_Scoring_Service.event;

import java.time.Instant;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FraudScoreEvent {

    private String transactionId;

    private String userId;

    private Double amount;

    private int ruleScore;

    private int mlScore;

    private int finalScore;

    private String riskLevel;

    private Instant timestamp;
}