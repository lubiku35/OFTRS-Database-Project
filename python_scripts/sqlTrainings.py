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
# CURSOR.execute('CREATE SCHEMA IF NOT EXISTS OFTRS')

CURSOR.execute("SELECT TrainerID, Specification FROM OFTRS.Trainer")
trainers = CURSOR.fetchall()

# define mapping of specification to training name
training_names = {
    "Fitness & Bodybuilding": "Fitness Training",
    "Tabata": "Tabata Training",
    "Yoga": "Yoga Training",
    "KickBox": "KickBox Training",
    "Box": "Boxing Training",
    "MMA": "MMA Training",
    "Crossfit": "Crossfit Training"
}

CURSOR.execute('''
    CREATE TABLE OFTRS.Training (
        TrainerID INTEGER NOT NULL,
        TrainingID VARCHAR(10) UNIQUE,
        TrainingCount VARCHAR(10) UNIQUE,
        TrainingName VARCHAR(100) NOT NULL,
        TrainingDate DATE NOT NULL,
        Place VARCHAR(120) NOT NULL,
        MaxCapacity INTEGER NOT NULL,
        Duration INTEGER NOT NULL,
        CONSTRAINT PK_Training PRIMARY KEY (TrainingID, TrainingCount),
        FOREIGN KEY (TrainerID) REFERENCES OFTRS.Trainer (TrainerID)
    );
''')



count = 0
for trainer in trainers:
    sample = ""
    count += 1

    for i in random.sample('ABCDEFGHIJKLMNOPQRSTUVWXYZ', 3): sample += i     
    
    TrainerID = trainer[0]
    TrainingID = str(trainer[0]) + "-" + sample
    
    if len(str(count)) == 1: TrainingCount = "AAA" + "00" + str(count) 
    elif len(str(count)) == 2: TrainingCount = "AAA" + "0" + str(count) 
    else: TrainingCount = "AAA" + str(count) 
    
    TrainingName = training_names[trainer[1]]
    Place = random.choice(["JohnReed - Karlovo nám. 2097/10, Nové Město, 120 00 Praha 2", "FormFactory - Václavské nám. 22 110 00 Praha 1", "SilliconGym - Vaníčkova 7 Břevnov Praha"]) # One of fitness centers
    TrainingDate = fake.date_between(start_date='+1y', end_date='+2y')
    MaxCapacity = random.choice([5, 10, 15, 20])
    Duration = random.choice([30, 45, 60, 90, 120])
    
    print(TrainerID, TrainingID, TrainingCount, TrainingName, TrainingDate, Place, MaxCapacity, Duration)
    
    CURSOR.execute('''
        INSERT INTO OFTRS.Training (TrainerID, TrainingID, TrainingCount, TrainingName,  TrainingDate, Place, MaxCapacity, Duration)
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s)
    ''', (TrainerID, TrainingID, TrainingCount, TrainingName, TrainingDate,  Place, MaxCapacity, Duration))

connection.commit()
connection.close()
