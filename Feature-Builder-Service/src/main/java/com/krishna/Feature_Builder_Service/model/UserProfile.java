package com.krishna.Feature_Builder_Service.model;


import java.util.Deque;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    private String userId;

    private Double avgAmount;

    private String lastCountry;

    private Set<String> knownDevices;

    private Deque<Long> transactionTimes;
}