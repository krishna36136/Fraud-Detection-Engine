package com.krishna.Risk_Scoring_Service.service;

import org.springframework.stereotype.Service;

import com.krishna.Risk_Scoring_Service.event.FeatureEvent;



@Service
public class MlScoringService {

    public int calculateMlScore(
            FeatureEvent feature) {

        double score = 0;

        score += feature.getAmountDeviationPercent() * 0.15;

        score += feature.getTxnPerMinute() * 5;

        if(feature.isNewDevice()) {
            score += 20;
        }

        if(feature.isCountryMismatch()) {
            score += 25;
        }

        return (int)Math.min(score,100);
    }
}