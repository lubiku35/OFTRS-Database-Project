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
CURSOR.execute('SELECT UserID FROM OFTRS.User')
user_ids = CURSOR.fetchall()

CURSOR.execute('''
    CREATE TABLE OFTRS.Calendar (
        CalendarID VARCHAR(100) PRIMARY KEY,
        UserID INTEGER REFERENCES OFTRS.User(UserID)
    );
''')

for user_id in user_ids:
    calendar_id = f"{user_id[0]}_Calendar"
    CURSOR.execute("INSERT INTO OFTRS.Calendar (UserID, CalendarID) VALUES (%s, %s)", (user_id[0], calendar_id))
    
connection.commit()
connection.close()