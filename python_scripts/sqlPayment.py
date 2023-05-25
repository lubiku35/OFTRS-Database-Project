from faker import Faker
import random
import psycopg2
fake = Faker('sk_SK')

# Psycopg2 Remote Connection to PostgreSQL
try:
    connection = psycopg2.connect(dbname="", user="", password="", host="", port="")
    print("Connection to the database was successful!")
except psycopg2.Error as e: print(f"Error connecting to the database: {e}")

CURSOR = connection.cursor()

CURSOR.execute('''
    CREATE TABLE IF NOT EXISTS OFTRS.Payment (
        PaymentID VARCHAR(100) PRIMARY KEY,
        UserID INTEGER REFERENCES OFTRS.User(UserID),
        TrainingID VARCHAR(10) REFERENCES OFTRS.Training(TrainingID),
        PaymentAmount NUMERIC(10,2) NOT NULL,
        PaymentDate DATE NOT NULL,
        PaymentType VARCHAR(50) NOT NULL,
        CONSTRAINT unique_payment_user_training UNIQUE (UserID, TrainingID),
        CONSTRAINT check_payment_amount CHECK (PaymentAmount > 0),
        CONSTRAINT check_payment_date CHECK (PaymentDate <= CURRENT_DATE)
    )
''')

# Get 25 random user IDs excluding trainers
CURSOR.execute('''
    SELECT UserID FROM OFTRS.User
    WHERE NOT EXISTS (
        SELECT 1 FROM OFTRS.Trainer WHERE Trainer.UserID = OFTRS.User.UserID
    )
    ORDER BY random() LIMIT 25
''')

user_ids = [row[0] for row in CURSOR.fetchall()]

# Get list of unique training IDs
CURSOR.execute('SELECT DISTINCT TrainingID FROM OFTRS.Training')
training_ids = [row[0] for row in CURSOR.fetchall()]

# Generate a random payment date within the last year
payment_date = fake.date_between(start_date='-1y', end_date='today')

# Create payments for each user
for user_id in user_ids:
    # Randomly choose a training for the payment
    training_id = random.choice(training_ids)
    
    # Check if the user already paid for this training
    CURSOR.execute('SELECT PaymentID FROM OFTRS.Payment WHERE UserID = %s AND TrainingID = %s', (user_id, training_id))
    
    if CURSOR.fetchone() is not None:
        # User already paid for this training, skip to next user
        continue
    
    # Generate payment details
    amount = round(random.uniform(50, 1000), 2)
    payment_type = random.choice(["Paypal", "Visa", "Mastercard"])
    payment_id = f"{user_id}_{training_id}"

    # Insert payment into payments table
    CURSOR.execute('INSERT INTO OFTRS.Payment (PaymentID, UserID, TrainingID, PaymentAmount, PaymentDate, PaymentType) VALUES (%s, %s, %s, %s, %s, %s)', (payment_id, user_id, training_id, amount, payment_date, payment_type))

connection.commit()
connection.close()