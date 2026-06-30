package com.krishna.Decision_Service.event;

import java.time.Instant;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DecisionEvent {

    private String transactionId;

    private String userId;

    private Double amount;

    private int ruleScore;

    private int mlScore;

    private int finalScore;

    private String riskLevel;

    private String decision;

    private String reason;

    private Instant timestamp;
}