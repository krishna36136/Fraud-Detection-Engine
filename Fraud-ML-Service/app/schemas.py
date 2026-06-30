from pydantic import BaseModel


class PredictionRequest(BaseModel):

    amount: float

    avgAmount: float

    amountDeviationPercent: float

    txnPerMinute: int

    newDevice: bool

    countryMismatch: bool


class PredictionResponse(BaseModel):

    fraudProbability: float

    predictedLabel: int

    modelVersion: str