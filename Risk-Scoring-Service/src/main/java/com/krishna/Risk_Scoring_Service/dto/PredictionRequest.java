package com.krishna.Risk_Scoring_Service.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PredictionRequest {

    private Double amount;

    private Double avgAmount;

    private Double amountDeviationPercent;

    private int txnPerMinute;

    private boolean newDevice;

    private boolean countryMismatch;
}