# 🚀 Real-Time Fraud Detection Platform

A microservices-based real-time fraud detection system that simulates payment transaction processing using an event-driven architecture. The platform streams transactions through Kafka, enriches them with user profile features, performs hybrid risk scoring using both rule-based logic and machine learning, and makes automated fraud decisions in real time.

The project demonstrates how modern fraud detection systems leverage **Kafka, Redis, Spring Boot, Docker, Python, FastAPI, and Machine Learning** to build scalable, low-latency event-processing pipelines.

---

# ✨ Features

* Real-time transaction streaming
* User profile enrichment
* Feature engineering for fraud detection
* Redis-backed low-latency profile lookups
* Hybrid risk scoring using rule-based and ML models
* Logistic Regression–based fraud prediction
* Automated transaction decisions (Approve, Review, Block)
* Event-driven microservices architecture
* Containerized infrastructure using Docker

---

# 🏗️ System Architecture

## 1. Transaction Service

* Spring Boot microservice acting as a Kafka Producer.
* Generates simulated payment transactions every **2 seconds**.
* Produces transactions to the **`transaction-topic`** Kafka topic.
* Includes a hard-coded user profile dataset for testing.
* Configured to generate approximately **10% fraudulent transactions** to simulate realistic fraud scenarios.

Each transaction contains:

* Transaction ID
* User ID
* Amount
* Country
* Device ID
* Timestamp

---

## 2. Feature Builder Service

* Spring Boot microservice acting as both a Kafka Consumer and Producer.
* Consumes transactions from the **`transaction-topic`**.
* Retrieves user profiles from **Redis**.
* Performs feature engineering by comparing incoming transactions with historical user behavior.
* Publishes engineered features to the **`transaction-features-topic`**.

Each feature event contains:

* Transaction ID
* User ID
* Transaction Amount
* User Average Amount
* New Device Flag
* Country Mismatch Flag
* Transactions Per Minute
* Timestamp

Redis is used for user profiles because it provides significantly lower-latency access than a relational database, making it ideal for real-time fraud detection workflows.

---

## 3. Risk Scoring Service

* Spring Boot microservice acting as both a Kafka Consumer and Producer.
* Consumes feature events from the **`transaction-features-topic`**.
* Computes fraud risk using a hybrid scoring approach.

The final risk score combines:

* Rule-based scoring using configurable fraud detection rules.
* Machine Learning scoring obtained from an external ML service.

The service communicates with the ML Scoring Service via HTTP and publishes the combined fraud score as a **Fraud Score Event** to the **`fraud-score-topic`**.

---

## 4. ML Scoring Service

* Python-based machine learning service built using **FastAPI**.
* Exposes REST APIs for fraud score prediction.
* Uses a **Logistic Regression** model trained on synthetic transaction data.
* Returns an ML-based fraud probability for each transaction.

Libraries used include:

* Pandas
* Scikit-learn

---

## 5. Decision Service

* Spring Boot Kafka Consumer.
* Consumes fraud score events from the **`fraud-score-topic`**.
* Applies configurable business rules to determine the final transaction outcome.

Possible decisions include:

* APPROVE
* REVIEW
* BLOCK

The final decision is published to the **`transaction-decisions-topic`** for downstream consumers.

---

# 🔄 End-to-End Workflow

```text
Transaction Service
        │
        ▼
Kafka (transaction-topic)
        │
        ▼
Feature Builder Service
        │
        ├────────► Redis (User Profiles)
        │
        ▼
Kafka (transaction-features-topic)
        │
        ▼
Risk Scoring Service
        │
        ├────────► Rule Engine
        │
        ├────────► ML Scoring Service (FastAPI)
        │
        ▼
Kafka (fraud-score-topic)
        │
        ▼
Decision Service
        │
        ▼
Kafka (transaction-decisions-topic)
```

---

# 🛠️ Tech Stack

### Backend

* Java
* Spring Boot
* Spring Web

### Messaging

* Apache Kafka

### Data Store

* Redis

### Machine Learning

* Python
* FastAPI
* Scikit-learn
* Pandas
* Logistic Regression

### DevOps

* Docker

---

# 💡 Design Highlights

* Event-driven microservices architecture powered by Apache Kafka.
* Low-latency user profile retrieval using Redis for real-time feature enrichment.
* Hybrid fraud detection combining deterministic business rules with machine learning predictions.
* Separate Python-based ML service communicating with Spring Boot services via REST APIs.
* Decoupled processing pipeline where each microservice has a single responsibility.
* Containerized infrastructure using Docker for consistent local deployment and simplified setup.
