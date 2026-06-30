package com.krishna.Risk_Scoring_Service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.krishna.Risk_Scoring_Service.dto.PredictionRequest;
import com.krishna.Risk_Scoring_Service.dto.PredictionResponse;

@Component
public class MlServiceClient {

    private final RestClient restClient;

    public MlServiceClient(
            @Value("${ml.service.url}") String mlServiceUrl) {

        this.restClient = RestClient.builder()
                .baseUrl(mlServiceUrl)
                .build();
    }

    public PredictionResponse predict(PredictionRequest request) {

        return restClient.post()
                .uri("/predict")
                .body(request)
                .retrieve()
                .body(PredictionResponse.class);
    }
}