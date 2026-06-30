package com.krishna.Risk_Scoring_Service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredictionResponse {

    private double fraudProbability;

    private int predictedLabel;

    private String modelVersion;
}