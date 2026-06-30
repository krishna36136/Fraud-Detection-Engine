from fastapi import FastAPI

from app.predictor import FraudPredictor
from app.schemas import PredictionRequest, PredictionResponse

app = FastAPI(
    title="Fraud Detection ML Service",
    version="1.0.0"
)

predictor = FraudPredictor()


@app.get("/")
def health():

    return {
        "status": "UP",
        "model": "Logistic Regression"
    }


@app.post("/predict", response_model=PredictionResponse)
def predict(request: PredictionRequest):

    probability, prediction = predictor.predict(
        request.amount,
        request.avgAmount,
        request.amountDeviationPercent,
        request.txnPerMinute,
        request.newDevice,
        request.countryMismatch
    )

    return PredictionResponse(
        fraudProbability=round(probability, 4),
        predictedLabel=int(prediction),
        modelVersion="1.0.0"
    )