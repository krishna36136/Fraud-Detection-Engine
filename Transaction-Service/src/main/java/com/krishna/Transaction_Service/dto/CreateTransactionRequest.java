package com.krishna.Transaction_Service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CreateTransactionRequest {

    @NotBlank
    private final String userId;

    @Positive
    private final Double amount;

    @NotBlank
    private final String country;

    @NotBlank
    private final String deviceId;

    private CreateTransactionRequest(Builder builder) {
        this.userId = builder.userId;
        this.amount = builder.amount;
        this.country = builder.country;
        this.deviceId = builder.deviceId;
    }

    public String getUserId() {
        return userId;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCountry() {
        return country;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String userId;
        private Double amount;
        private String country;
        private String deviceId;

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

        public CreateTransactionRequest build() {
            return new CreateTransactionRequest(this);
        }
    }
}