package com.krishna.Transaction_Service.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class TransactionEvent {

    private final String transactionId;
    private final String userId;
    private final Double amount;
    private final String country;
    private final String deviceId;
    private final Instant timestamp;

    @JsonCreator
    public TransactionEvent(
            @JsonProperty("transactionId") String transactionId,
            @JsonProperty("userId") String userId,
            @JsonProperty("amount") Double amount,
            @JsonProperty("country") String country,
            @JsonProperty("deviceId") String deviceId,
            @JsonProperty("timestamp") Instant timestamp
    ) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.amount = amount;
        this.country = country;
        this.deviceId = deviceId;
        this.timestamp = timestamp;
    }

    public String getTransactionId() { return transactionId; }
    public String getUserId() { return userId; }
    public Double getAmount() { return amount; }
    public String getCountry() { return country; }
    public String getDeviceId() { return deviceId; }
    public Instant getTimestamp() { return timestamp; }

    // ---------- BUILDER ----------
    public static class Builder {

        private String transactionId;
        private String userId;
        private Double amount;
        private String country;
        private String deviceId;
        private Instant timestamp;

        public Builder transactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder deviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public Builder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public TransactionEvent build() {
            return new TransactionEvent(
                    transactionId,
                    userId,
                    amount,
                    country,
                    deviceId,
                    timestamp
            );
        }
    }
}