import joblib
import pandas as pd

from sklearn.linear_model import LogisticRegression
from sklearn.metrics import (
    accuracy_score,
    precision_score,
    recall_score,
    f1_score,
    confusion_matrix,
    classification_report,
)
from sklearn.model_selection import train_test_split

# -------------------------------
# Load Dataset
# -------------------------------

df = pd.read_csv("app/data/training_data.csv")

print("\nDataset Loaded Successfully\n")

print(df.head())

# -------------------------------
# Features
# -------------------------------

X = df[
    [
        "amount",
        "avgAmount",
        "amountDeviationPercent",
        "txnPerMinute",
        "newDevice",
        "countryMismatch",
    ]
]

# -------------------------------
# Labels
# -------------------------------

y = df["fraud"]

# -------------------------------
# Split Dataset
# -------------------------------

X_train, X_test, y_train, y_test = train_test_split(
    X,
    y,
    test_size=0.20,
    random_state=42,
)

print("\nTraining Records :", len(X_train))
print("Testing Records  :", len(X_test))

# -------------------------------
# Train Model
# -------------------------------

model = LogisticRegression(max_iter=1000)

model.fit(X_train, y_train)

print("\nModel Trained Successfully")

# -------------------------------
# Predictions
# -------------------------------

predictions = model.predict(X_test)

# -------------------------------
# Evaluation
# -------------------------------

print("\n========== MODEL EVALUATION ==========\n")

print("Accuracy :", accuracy_score(y_test, predictions))
print("Precision:", precision_score(y_test, predictions))
print("Recall   :", recall_score(y_test, predictions))
print("F1 Score :", f1_score(y_test, predictions))

print("\nConfusion Matrix\n")

print(confusion_matrix(y_test, predictions))

print("\nClassification Report\n")

print(classification_report(y_test, predictions))

# -------------------------------
# Save Model
# -------------------------------

joblib.dump(model, "app/model/fraud_model.pkl")

print("\nModel saved successfully!")