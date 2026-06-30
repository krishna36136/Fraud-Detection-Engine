import random
import pandas as pd

NUM_RECORDS = 20000

rows = []

for _ in range(NUM_RECORDS):

    avg_amount = random.randint(200, 5000)

    amount = round(random.uniform(50, 20000), 2)

    deviation = abs(amount - avg_amount) / avg_amount * 100

    txn_per_minute = random.randint(1, 12)

    new_device = random.choice([0, 1])

    country_mismatch = random.choice([0, 1])

    fraud = 0

    if (
        deviation > 150
        or txn_per_minute > 7
        or (new_device and country_mismatch)
    ):
        fraud = 1

    rows.append([
        amount,
        avg_amount,
        deviation,
        txn_per_minute,
        new_device,
        country_mismatch,
        fraud
    ])

df = pd.DataFrame(
    rows,
    columns=[
        "amount",
        "avgAmount",
        "amountDeviationPercent",
        "txnPerMinute",
        "newDevice",
        "countryMismatch",
        "fraud"
    ]
)

df.to_csv(
    "app/data/training_data.csv",
    index=False
)

print(df.head())
print()
print("Dataset generated successfully.")