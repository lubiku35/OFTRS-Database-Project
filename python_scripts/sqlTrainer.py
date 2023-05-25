from faker import Faker
import random
import psycopg2
fake = Faker('sk_SK')



try:
    connection = psycopg2.connect(dbname="", user="", password="", host="", port="")
    print("Connection to the database was successful!")
except psycopg2.Error as e:
    print(f"Error connecting to the database: {e}")

    
CURSOR = connection.cursor()
CURSOR.execute('CREATE SCHEMA IF NOT EXISTS OFTRS')

CURSOR.execute('''
    CREATE TABLE OFTRS.Trainer (
    TrainerID INTEGER PRIMARY KEY CHECK(TrainerID BETWEEN 100000 AND 999999),
    UserID INTEGER NOT NULL,
    Specification VARCHAR(100) NOT NULL,
    Type VARCHAR(100) NOT NULL,
    ValidityDate DATE NOT NULL,
    FOREIGN KEY (UserID)
        REFERENCES OFTRS.User (UserID)
    )
''')

cert_types = {
    "Crossfit": ["Crossfit Level 1", "Crossfit Level 2", "Crossfit Level 3"],
    "Boxing": ["Boxing Level Advanced", "Boxing Level Intermediate"],
    "MMA": ["MMA Intermediate", "MMA Level Advanced"],
    "KickBoxing": ["KickBoxing Level Advanced", "KickBoxing Level Intermediate"],
    "Fitness": ["Fitness Specialized", "Fitness Level Advanced", "Fitness for Proffesionals", "Fitness Level Intermediate"],
    "Tabata": ["Tabata Trainer"],
    "Yoga": ["Yoga Trainer"]
}

cert_specs = ["Fitness & Bodybuilding", "Tabata", "Yoga", "KickBox", "Box", "MMA", "Crossfit"]
used_personal_ids = set()
trainer_ids = set()

for i in range(50):
    while True:
        trainer_id = random.randrange(100000, 1000000)

        if trainer_id not in trainer_ids:
            trainer_ids.add(trainer_id)
            break
    
    while True:
        personal_id = random.randint(1, 32000)
        
        if personal_id not in used_personal_ids:
            used_personal_ids.add(personal_id)
            break
        else: print("Choosed same id")
    
    while True:     
        CURSOR.execute("SELECT UserID FROM OFTRS.user WHERE UserID = %s", (personal_id,))
        if CURSOR.fetchone() is not None: break

    cert_spec = random.choice(cert_specs)
    if cert_spec == "Crossfit": cert_type = random.choice(cert_types["Crossfit"])
    elif cert_spec == "Box": cert_type = random.choice(cert_types["Boxing"])
    elif cert_spec == "MMA": cert_type = random.choice(cert_types["MMA"])
    elif cert_spec == "KickBox": cert_type = random.choice(cert_types["KickBoxing"])
    elif cert_spec == "Fitness": cert_type = random.choice(cert_types["Fitness"])
    elif cert_spec == "Tabata": cert_type = random.choice(cert_types["Tabata"])
    else: cert_type = random.choice(cert_types["Yoga"])

    cert_validitydate = fake.date_between(start_date='+1y', end_date='+5y')

    CURSOR.execute("INSERT INTO OFTRS.trainer (TrainerID, UserID, Specification, Type, ValidityDate) VALUES (%s, %s, %s, %s, %s)", (trainer_id, personal_id, cert_spec, cert_type, cert_validitydate))
    print(f"{personal_id}, {trainer_id}, {cert_spec}, {cert_type}, {cert_validitydate}")

connection.commit()
connection.close()
