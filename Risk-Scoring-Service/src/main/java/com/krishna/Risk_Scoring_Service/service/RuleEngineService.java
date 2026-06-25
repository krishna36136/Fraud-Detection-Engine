package com.krishna.Risk_Scoring_Service.service;


import org.springframework.stereotype.Service;

import com.krishna.Risk_Scoring_Service.event.FeatureEvent;

@Service
public class RuleEngineService {

    public int calculateRuleScore(
            FeatureEvent feature) {

        int score = 0;

        if(feature.isNewDevice()) {
            score += 25;
        }

        if(feature.isCountryMismatch()) {
            score += 30;
        }

        if(feature.getAmountDeviationPercent() > 100) {
            score += 20;
        }

        if(feature.getTxnPerMinute() > 5) {
            score += 25;
        }

        return Math.min(score, 100);
    }
}