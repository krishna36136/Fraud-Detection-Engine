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

    public FeatureEvent buildFeatures(
            TransactionEvent txn) {

        UserProfile profile =
                profileService.getOrCreate(
                        txn.getUserId()
                );

        boolean newDevice =
                !profile.getKnownDevices()
                        .contains(
                            txn.getDeviceId()
                        );

        boolean countryMismatch =
                profile.getLastCountry() != null
                &&
                !profile.getLastCountry()
                        .equals(txn.getCountry());

        double deviationPercent =
                calculateDeviation(
                        txn.getAmount(),
                        profile.getAvgAmount()
                );

        int velocity =
                calculateVelocity(
                        profile,
                        txn.getTimestamp()
                );

        updateProfile(profile, txn);

        profileService.save(profile);

        return FeatureEvent.builder()
                .transactionId(txn.getTransactionId())
                .userId(txn.getUserId())
                .amount(txn.getAmount())
                .newDevice(newDevice)
                .countryMismatch(countryMismatch)
                .amountDeviationPercent(
                        deviationPercent
                )
                .txnPerMinute(velocity)
                .timestamp(txn.getTimestamp())
                .build();
        
    }
        
        
        private int calculateVelocity(
                UserProfile profile,
                Instant currentTime) {

            long now =
                    currentTime.getEpochSecond();

            Deque<Long> txns =
                    profile.getTransactionTimes();

            while(!txns.isEmpty()
                    &&
                    now - txns.peekFirst() > 60) {

                txns.pollFirst();
            }

            txns.addLast(now);

            return txns.size();
        }
        
        private double calculateDeviation(
                Double amount,
                Double avgAmount) {

            if(avgAmount == null
                || avgAmount == 0) {

                return 0;
            }

            return Math.abs(
                    amount - avgAmount
            ) * 100 / avgAmount;
        }
        
        private void updateProfile(
                UserProfile profile,
                TransactionEvent txn) {

            profile.getKnownDevices()
                   .add(txn.getDeviceId());

            profile.setLastCountry(
                    txn.getCountry()
            );

            double oldAvg =
                    profile.getAvgAmount();

            if(oldAvg == 0) {

                profile.setAvgAmount(
                        txn.getAmount()
                );
            } else {

                profile.setAvgAmount(
                        (oldAvg * 0.8)
                        +
                        (txn.getAmount() * 0.2)
                );
            }
        }
    }