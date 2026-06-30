package com.krishna.Risk_Scoring_Service.service;

import org.springframework.stereotype.Service;

import com.krishna.Risk_Scoring_Service.event.FeatureEvent;

import org.springframework.stereotype.Service;

import com.krishna.Risk_Scoring_Service.client.MlServiceClient;
import com.krishna.Risk_Scoring_Service.dto.PredictionRequest;
import com.krishna.Risk_Scoring_Service.dto.PredictionResponse;
import com.krishna.Risk_Scoring_Service.event.FeatureEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MlScoringService {

    private final MlServiceClient mlServiceClient;

    public int calculateMlScore(FeatureEvent feature) {

        PredictionRequest request =
                PredictionRequest.builder()
                        .amount(feature.getAmount())
                        .avgAmount(feature.getAvgAmount())
                        .amountDeviationPercent(feature.getAmountDeviationPercent())
                        .txnPerMinute(feature.getTxnPerMinute())
                        .newDevice(feature.isNewDevice())
                        .countryMismatch(feature.isCountryMismatch())
                        .build();

        PredictionResponse response =
                mlServiceClient.predict(request);

        return (int)(response.getFraudProbability() * 100);
    }
}