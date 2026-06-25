package com.krishna.Feature_Builder_Service.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBehaviorState {

    private String userId;

    private long transactionCount;

    private double totalAmount;

    private double avgAmount;

    private String lastCountry;

    private Set<String> knownDevices;

    private List<Long> recentTransactions;

    public static UserBehaviorState create(String userId) {

        return UserBehaviorState.builder()
                .userId(userId)
                .transactionCount(0)
                .totalAmount(0)
                .avgAmount(0)
                .lastCountry(null)
                .knownDevices(new HashSet<>())
                .recentTransactions(new ArrayList<>())
                .build();
    }
}