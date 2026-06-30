package com.krishna.Feature_Builder_Service.service;

import java.time.Instant;
import java.util.Deque;

import org.springframework.stereotype.Service;

import com.krishna.Feature_Builder_Service.event.FeatureEvent;
import com.krishna.Feature_Builder_Service.event.TransactionEvent;
import com.krishna.Feature_Builder_Service.model.UserProfile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeatureExtractionService {

    private final UserProfileService profileService;

    public FeatureEvent buildFeatures(TransactionEvent txn) {

        // Get user profile from Redis
        UserProfile profile = profileService.getOrCreate(txn.getUserId());

        // Store the historical average BEFORE updating the profile
        double avgAmount = profile.getAvgAmount();

        // Check if transaction is from a new device
        boolean newDevice = !profile.getKnownDevices()
                                    .contains(txn.getDeviceId());

        // Check if transaction is from a different country
        boolean countryMismatch =
                profile.getLastCountry() != null
                && !profile.getLastCountry().equals(txn.getCountry());

        // Calculate deviation using historical average
        double deviationPercent =
                calculateDeviation(
                        txn.getAmount(),
                        avgAmount
                );

        // Calculate transaction velocity
        int velocity =
                calculateVelocity(
                        profile,
                        txn.getTimestamp()
                );

        // Update user profile for future transactions
        updateProfile(profile, txn);

        // Save updated profile to Redis
        profileService.save(profile);

        // Publish engineered features
        return FeatureEvent.builder()
                .transactionId(txn.getTransactionId())
                .userId(txn.getUserId())
                .amount(txn.getAmount())
                .avgAmount(avgAmount)
                .newDevice(newDevice)
                .countryMismatch(countryMismatch)
                .amountDeviationPercent(deviationPercent)
                .txnPerMinute(velocity)
                .timestamp(txn.getTimestamp())
                .build();
    }

    private int calculateVelocity(
            UserProfile profile,
            Instant currentTime) {

        long now = currentTime.getEpochSecond();

        Deque<Long> txns = profile.getTransactionTimes();

        while (!txns.isEmpty()
                && now - txns.peekFirst() > 60) {

            txns.pollFirst();
        }

        txns.addLast(now);

        return txns.size();
    }

    private double calculateDeviation(
            Double amount,
            Double avgAmount) {

        if (avgAmount == null || avgAmount == 0) {
            return 0;
        }

        return Math.abs(amount - avgAmount) * 100 / avgAmount;
    }

    private void updateProfile(
            UserProfile profile,
            TransactionEvent txn) {

        // Remember this device
        profile.getKnownDevices().add(txn.getDeviceId());

        // Update latest country
        profile.setLastCountry(txn.getCountry());

        // Update running average amount
        double oldAvg = profile.getAvgAmount();

        if (oldAvg == 0) {
            profile.setAvgAmount(txn.getAmount());
        } else {
            profile.setAvgAmount(
                    (oldAvg * 0.8)
                    + (txn.getAmount() * 0.2)
            );
        }
    }
}