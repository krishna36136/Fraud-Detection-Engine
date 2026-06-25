package com.krishna.Risk_Scoring_Service.service;


import org.springframework.stereotype.Service;

import com.krishna.Risk_Scoring_Service.event.FeatureEvent;
import com.krishna.Risk_Scoring_Service.event.FraudScoreEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RiskScoringService {

    private final RuleEngineService ruleEngine;

    private final MlScoringService mlService;

    public FraudScoreEvent score(
            FeatureEvent feature) {

        int ruleScore =
                ruleEngine.calculateRuleScore(feature);

        int mlScore =
                mlService.calculateMlScore(feature);

        int finalScore =
                (int)(
                        ruleScore * 0.7
                        +
                        mlScore * 0.3
                );

        String riskLevel;

        if(finalScore < 40) {
            riskLevel = "LOW";
        }
        else if(finalScore < 70) {
            riskLevel = "MEDIUM";
        }
        else {
            riskLevel = "HIGH";
        }

        return FraudScoreEvent.builder()
                .transactionId(
                        feature.getTransactionId()
                )
                .userId(
                        feature.getUserId()
                )
                .amount(
                        feature.getAmount()
                )
                .ruleScore(
                        ruleScore
                )
                .mlScore(
                        mlScore
                )
                .finalScore(
                        finalScore
                )
                .riskLevel(
                        riskLevel
                )
                .timestamp(
                        feature.getTimestamp()
                )
                .build();
    }
}