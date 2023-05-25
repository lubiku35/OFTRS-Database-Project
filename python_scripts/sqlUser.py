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
    CREATE TABLE OFTRS.User (
        UserID INTEGER PRIMARY KEY,
        FirstName VARCHAR(75) NOT NULL, 
        LastName VARCHAR(75) NOT NULL, 
        Email VARCHAR(125) UNIQUE,
        PhoneNumber VARCHAR(45) UNIQUE,
        Nickname VARCHAR(55) NOT NULL,
        CONSTRAINT userValidateEmail CHECK (email LIKE '_%@_%.__%')
    )
''')

DOMAINS = ['gmail.com', 'outlook.com', 'proton.me', 'seznam.cz', 'yahoo.com', 'amazon.eu']
used_names = set()
used_surnames = set()
used_phones = set()

for i in range(32000):
    user_id = i + 1
    while True:
        first_name = fake.first_name()
        last_name = fake.last_name()
        if (first_name, last_name) not in used_names.union(used_surnames):
            used_names.add(first_name)
            used_surnames.add(last_name)
            break
    email_domain = random.choice(DOMAINS)
    email = f"{first_name.lower()}.{last_name.lower()}{random.randint(0, 999):03}@{email_domain}"
    while True:
        phone_number = fake.phone_number()
        phone_number = phone_number.replace(" ", "")
        if phone_number not in used_phones:
            used_phones.add(phone_number)
            break
    nickname = f"{first_name.lower()}{random.randint(0, 9999):04}"
    CURSOR.execute("INSERT INTO OFTRS.User (UserID, FirstName, LastName, Email, PhoneNumber, Nickname) VALUES (%s, %s, %s, %s, %s, %s)", (user_id, first_name, last_name, email, phone_number, nickname))
    print(f"{i+1}, {first_name}, {last_name}, {email}, {phone_number}, {nickname}")

connection.commit()
connection.close()