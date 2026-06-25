package com.krishna.Transaction_Service.dto;

public class CreateTransactionResponse {

    private final String transactionId;
    private final String status;

    private CreateTransactionResponse(Builder builder) {
        this.transactionId = builder.transactionId;
        this.status = builder.status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getStatus() {
        return status;
    }

    // Optional: makes it look like Lombok's @Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String transactionId;
        private String status;

        public Builder transactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public CreateTransactionResponse build() {
            return new CreateTransactionResponse(this);
        }
    }
}