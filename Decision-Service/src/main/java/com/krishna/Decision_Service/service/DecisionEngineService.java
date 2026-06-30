package com.krishna.Decision_Service.service;

import org.springframework.stereotype.Service;

import com.krishna.Decision_Service.event.DecisionEvent;
import com.krishna.Decision_Service.event.FraudScoreEvent;

@Service
public class DecisionEngineService {

    public DecisionEvent decide(FraudScoreEvent fraudScore) {

        String decision;
        String reason;

        if (fraudScore.getFinalScore() < 40) {

            decision = "APPROVE";
            reason = "Low fraud risk.";

        } else if (fraudScore.getFinalScore() < 70) {

            decision = "REVIEW";
            reason = "Medium fraud risk. Manual verification recommended.";

        } else {

            decision = "BLOCK";
            reason = "High fraud risk detected.";
        }

        return DecisionEvent.builder()
                .transactionId(fraudScore.getTransactionId())
                .userId(fraudScore.getUserId())
                .amount(fraudScore.getAmount())
                .ruleScore(fraudScore.getRuleScore())
                .mlScore(fraudScore.getMlScore())
                .finalScore(fraudScore.getFinalScore())
                .riskLevel(fraudScore.getRiskLevel())
                .decision(decision)
                .reason(reason)
                .timestamp(fraudScore.getTimestamp())
                .build();
    }
}