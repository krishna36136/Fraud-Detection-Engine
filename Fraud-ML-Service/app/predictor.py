import joblib
import numpy as np


class FraudPredictor:

    def __init__(self):

        self.model = joblib.load("app/model/fraud_model.pkl")

    def predict(
            self,
            amount,
            avgAmount,
            amountDeviationPercent,
            txnPerMinute,
            newDevice,
            countryMismatch):

        features = np.array([
            [
                amount,
                avgAmount,
                amountDeviationPercent,
                txnPerMinute,
                int(newDevice),
                int(countryMismatch)
            ]
        ])

        probability = self.model.predict_proba(features)[0][1]

        prediction = self.model.predict(features)[0]

        return probability, prediction