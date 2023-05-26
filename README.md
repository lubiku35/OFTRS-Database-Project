
# Online Fitness Training Reservation System | Database Project

> **Author** | Ľuboslav Motošický, @lubiku35
>
> **Project Name** | OFTRS - Online Fitness Training Reservation System


## Introduction

The Online Fitness Training Reservation System project is an innovative demo database solution for any sports club or fitness center that is interested in enriching their reservation workflow by storing data about their clients, coaches, workouts, reservations, and payments in the database.

Specifically in this project you can discover a prototype database with testing data which includes exactly 32,000 users and of them approximately 50 trainers, moreover the database contains 50 training session types which are restricted to their capacity and several test reservations along with the payments made. 

## Navigation

- [Online Fitness Training Reservation System | Database Project](#online-fitness-training-reservation-system--database-project)
  - [Introduction](#introduction)
  - [Navigation](#navigation)
  - [Involved Technologies](#involved-technologies)
  - [Essential Model Description](#essential-model-description)
    - [User](#user)
    - [Trainer](#trainer)
    - [Training](#training)
    - [Reservation](#reservation)
    - [Payment](#payment)
    - [Calendar](#calendar)
  - [Conceptual Model](#conceptual-model)
  - [Realtional Model](#realtional-model)
    - [Entity User](#entity-user)
    - [Entity Trainer](#entity-trainer)
    - [Entity Training](#entity-training)
    - [Entity Reservation](#entity-reservation)
    - [Entity Payment](#entity-payment)
    - [Entity Calendar](#entity-calendar)
  - [Database Creation and Data Manipulation Using Python and SQL](#database-creation-and-data-manipulation-using-python-and-sql)
    - [Python Psycopg2 Remote Connection to Database](#python-psycopg2-remote-connection-to-database)
    - [Python to SQL for Entity User](#python-to-sql-for-entity-user)
    - [Python to SQL for Entity Trainer](#python-to-sql-for-entity-trainer)
    - [Python to SQL for Entity Training](#python-to-sql-for-entity-training)
    - [Python to SQL for Entity Payment](#python-to-sql-for-entity-payment)
    - [Python to SQL for Entity Calendar](#python-to-sql-for-entity-calendar)
  - [SQL Schema Diagram](#sql-schema-diagram)
  - [Additional SQL Queries to Retrieve Data From The Database](#additional-sql-queries-to-retrieve-data-from-the-database)
    - [External Connection of Tables](#external-connection-of-tables)
    - [Internal Connection of Tables](#internal-connection-of-tables)
    - [Condition on Data](#condition-on-data)
    - [Aggregation and Condition on the Value of Aggregation Function](#aggregation-and-condition-on-the-value-of-aggregation-function)
    - [Sorting and Paging](#sorting-and-paging)
    - [Set Operations](#set-operations)
    - [Nested SELECT](#nested-select)
    - [Bonus Query](#bonus-query)
  - [SQL Reservations Table](#sql-reservations-table)
  - [JPA - Java Persistence API](#jpa---java-persistence-api)
    
## Involved Technologies

> PostgreSQL, Python, Faker, Psycopg2, Java (JPA), Maven, Hibernate

Online Fitness Training Reservation System is built using PostgreSQL, which provides a structured and efficient way to store and manage data. Python has been used to produce _'realistic'_ data for the database using the Faker library. Also, with Psycopg2 which is PostgreSQL adapter for Python I have achieved remote connection to the database from Python and then performing SQL queries.

JPA is a Java specification for managing relational data in applications. It provides an abstraction layer on top of the database, allowing me to work with objects instead of directly interacting with SQL.

Hibernate is a popular object-relational mapping (ORM) framework for Java. It provides a way to map Java objects to database tables and vice versa, abstracting the underlying SQL operations. In my project, Hibernate is used as the JPA implementation to interact with the PostgreSQL database. It handles the persistence of objects, executes queries, and manages database transactions.

<br>

## Essential Model Description

> The database model for OFTRS consists of five essential entities: **User**, **Trainer**, **Training**, **Reservation** and **Payment** and one weak entity: **Calendar**

### User

The User entity represents individuals who are registered users of the Online Fitness Training Reservation System. Each user has a unique identifier, along with attributes such as their name, email, phone number, and any additional details required for user management.

### Trainer

The Trainer entity represents fitness trainers or coaches who provide training sessions in the system. Each trainer has a unique identifier, along with attributes such as their name, specialization, experience, and contact information. Trainers are associated with specific training sessions.

### Training

The Training entity represents different types of training sessions offered in the Online Fitness Training Reservation System. Each training session has a unique identifier and attributes such as the session name, description, duration, capacity (the maximum number of participants allowed), and any other relevant details.

### Reservation

The Reservation entity represents a user's booking for a specific training session. Each reservation has a unique identifier and is associated with a user, trainer, and training session. It includes attributes such as the reservation date and time, along with any additional information related to the reservation status or special requirements.

### Payment

The Payment entity represents the payment details associated with a user's reservation. Each payment has a unique identifier and is linked to a specific reservation. It includes attributes such as the payment amount, transaction date, payment method, and any other relevant information.

### Calendar

The Calendar entity serves as a weak entity in the database model, representing the availability and schedule for training sessions.

<br>

## Conceptual Model

The conceptual model section presents an overview of the conceptual model for the OFTRS database. The conceptual model represents the high-level design and conceptual relationships between entities without specifying the implementation details. It focuses on the entities, their attributes, and the associations between them, providing a clear understanding of the data requirements and relationships in the system.

![Conceptual Model](./assets/conceptual_model.png)

<br>

## Realtional Model

In this section, the relational model of the Online Fitness Training Reservation System (OFTRS) database is discussed. The relational model represents the structure and relationships of the database entities using tables, primary keys, and foreign keys. It provides an overview of how the entities are connected and organized within the database.

### Entity User

**User** (<u>UserID</u>, Full Name, <u>E-mail</u>, <u>Phone Number</u>, Nickname)

- **Full Name**: (<u>User</u>, FirstName, LastName)


### Entity Trainer

**Trainer**: (<u>UserID</u>, <u>TrainerID</u>, Certification)

- **Certification**: (<u>Trainer</u>, Specification, Type, ValidityDate)
- **FK**: (UserID) ⊆ User(<u>UserID</u>)  / References


### Entity Training

**Training**: (<u>TrainerID</u>, <u>TrainingIdentity</u>, TrainingName, TrainingDate, Place, MaxCapacity, Duration)
- **TrainingIdentity**: (<u>Training</u>, <u>TrainingCount, TrainingID</u>)
- **FK**: (TrainerID) ⊆ Trainer(<u>TrainerID</u>)  / References


### Entity Reservation

**Reservation**: (<u>ReservationID</u>, <u>UserID</u>, <u>TrainingID</u>)
- **FK**: (UserID) ⊆ User(<u>UserID</u>)  / References
- **FK**: (TrainingID) ⊆ Training(<u>TrainingID</u>)  / References

### Entity Payment

**Payment**: (<u>PaymentID</u>, <u>UserID</u>, <u>TrainingID</u>, PaymentAmount, PaymentDate, PaymentType)
- **FK**: (UserID) ⊆ User(<u>UserID</u>)  / References
- **FK**: (TrainingID) ⊆ Training(<u>TrainingIdentity</u>)  / References

### Entity Calendar

**Calendar**: (<u>CalendarID</u>, <u>UserID</u>)
- **FK**: (UserID) ⊆ User(<u>UserID</u>)  / References

<br>

## Database Creation and Data Manipulation Using Python and SQL

This section demonstrates the process of creating the OFTRS database and manipulating data using Python and SQL. It covers the creation of database tables using SQL statements and shows how Python is used to execute these statements to create the database structure. Additionally, it showcases the insertion of data into the tables using Python, with the execution of SQL INSERT statements. It provides examples of common SQL queries to retrieve data from the created tables and demonstrates how Python can be used to execute these queries and fetch the desired results.

### Python Psycopg2 Remote Connection to Database

```python
try:
    connection = psycopg2.connect(dbname="", user="", password="", host="", port="")
    print("Connection to the database was successful!")
except psycopg2.Error as e: print(f"Error connecting to the database: {e}")
```

### Python to SQL for Entity User

> SQL query used in python to create User Table

```python
CREATE TABLE OFTRS.User (
    UserID INTEGER PRIMARY KEY,
    FirstName VARCHAR(75) NOT NULL, 
    LastName VARCHAR(75) NOT NULL, 
    Email VARCHAR(125) UNIQUE,
    PhoneNumber VARCHAR(45) UNIQUE,
    Nickname VARCHAR(55) NOT NULL,
    CONSTRAINT userValidateEmail CHECK (email LIKE '_%@_%.__%')
);
```

> SQL query used in python to insert generated data to User Table

```python
CURSOR.execute(
    "INSERT INTO OFTRS.User (UserID, FirstName, LastName, Email, PhoneNumber, Nickname) VALUES (%s, %s, %s, %s, %s, %s)",
    (user_id, first_name, last_name, email, phone_number, nickname))
```

> SQL query used in JetBrains - DataGrip Application for select first 25 rows

```sql
SELECT * FROM OFTRS.User LIMIT 25;
```

> Output

![User Table Output](./assets/user_table_output.png)


### Python to SQL for Entity Trainer

> SQL query used in python to create Trainer Table

```python
CREATE TABLE OFTRS.Trainer (
    TrainerID INTEGER PRIMARY KEY CHECK(TrainerID BETWEEN 100000 AND 999999),
    UserID INTEGER NOT NULL,
    Specification VARCHAR(100) NOT NULL,
    Type VARCHAR(100) NOT NULL,
    ValidityDate DATE NOT NULL,
    FOREIGN KEY (UserID)
    REFERENCES OFTRS.User (UserID)
);
```

> SQL query used in python to insert generated data to Trainer Table

```python
CURSOR.execute(
    "INSERT INTO OFTRS.trainer (TrainerID, UserID, Specification, Type, ValidityDate) VALUES (%s, %s, %s, %s, %s)", (trainer_id, personal_id, cert_spec, cert_type, cert_validitydate)
)
```

> SQL query used in JetBrains - DataGrip Application for select first 25 rows

```sql
SELECT * FROM OFTRS.Trainer LIMIT 25;
```

> Output

![Trainer Table Output](./assets/trainer_table_output.png)



### Python to SQL for Entity Training

> SQL query used in python to create Training Table

```python
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
```

> SQL query used in python to insert generated data to Training Table

```python
CURSOR.execute(
     '''INSERT INTO OFTRS.Training (TrainerID, TrainingID, TrainingCount,
        TrainingName,  TrainingDate, Place, MaxCapacity, Duration)
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s)''', 
     (TrainerID, TrainingID, TrainingCount, TrainingName, TrainingDate, Place,
     MaxCapacity, Duration)
)
```

> SQL query used in JetBrains - DataGrip Application for select first 25 rows

```sql
SELECT * FROM OFTRS.Training LIMIT 25;
```

> Output

![Trainer Table Output](./assets/training_table_output.png)

### Python to SQL for Entity Payment

> SQL query used in python to create Payment Table

```python
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
);
```

> SQL query used in python to insert generated data to Payment Table

```python
CURSOR.execute(
    'INSERT INTO OFTRS.Payment (PaymentID, UserID, TrainingID, PaymentAmount, PaymentDate, PaymentType) VALUES (%s, %s, %s, %s, %s, %s)', (payment_id, user_id, training_id, amount, payment_date, payment_type)
)
```

> SQL query used in JetBrains - DataGrip Application for select first 25 rows

```sql
SELECT * FROM OFTRS.Payment LIMIT 25;
```

> Output

![Trainer Table Output](./assets/payment_table_output.png)


### Python to SQL for Entity Calendar

> SQL query used in python to create Calendar Table

```python
CREATE TABLE OFTRS.Calendar (
    CalendarID VARCHAR(100) PRIMARY KEY,
    UserID INTEGER REFERENCES OFTRS.User(UserID)
);
```

> SQL query used in python to insert generated data to Calendar Table

```python
CURSOR.execute(
    "INSERT INTO OFTRS.Calendar (UserID, CalendarID) VALUES (%s, %s)",
    (user_id[0], calendar_id)
)
```

> SQL query used in JetBrains - DataGrip Application for select first 25 rows

```sql
SELECT * FROM OFTRS.Calendar LIMIT 25;
```

> Output

![Trainer Table Output](./assets/calendar_table_output.png)

<br>

## SQL Schema Diagram

![SQL Schema Diagram](./assets/SQL_schema_diagram.png)

<br>


## Additional SQL Queries to Retrieve Data From The Database 

This section presents specific use cases of SQL queries that can be employed to retrieve data from the database. These queries cater to various scenarios and requirements, allowing for efficient and targeted data retrieval.

### External Connection of Tables

This SQL query return a result set with the first name and last name of users who are also trainers, along with their respective training specifications and the name of the training they are currently conducting.

```sql
SELECT u.FirstName, u.LastName, t.Specification, tr.TrainingName
FROM OFTRS.User u
JOIN OFTRS.Trainer t ON u.UserID = t.UserID
JOIN OFTRS.Training tr ON t.TrainerID = tr.TrainerID
```

### Internal Connection of Tables

This query retrieves the names of training sessions, and the corresponding payment amounts for payments made between January 1, 2022 and March 31, 2023.

```sql
SELECT tr.TrainingName, p.PaymentAmount
FROM OFTRS.Training tr
JOIN OFTRS.Payment p ON tr.TrainingID = p.TrainingID
WHERE p.PaymentDate >= '2022-01-01' AND p.PaymentDate <= '2023-03-31'
```

### Condition on Data

This query retrieves all training sessions taking place in “Praha”.

```sql
SELECT *
FROM OFTRS.Training
WHERE Place LIKE '%Praha%'
```

### Aggregation and Condition on the Value of Aggregation Function

This query retrieves the name of each training session and the number of payments made for that session, but only for sessions with more than 5 payments.

```sql
SELECT tr.TrainingName, COUNT(p.PaymentID) AS NumPayments
FROM OFTRS.Training tr
LEFT JOIN OFTRS.Payment p ON tr.TrainingID = p.TrainingID
GROUP BY tr.TrainingName
HAVING COUNT(p.PaymentID) > 1
```

### Sorting and Paging

This query retrieves the 10 training sessions with the most recent dates.

```sql
SELECT *
FROM OFTRS.Training
ORDER BY TrainingDate DESC
OFFSET 10 ROWS FETCH NEXT 10 ROWS ONLY
```

### Set Operations

This query retrieves the first name, last name, and email of users who have the nickname ‘john2937’ and also have a phone number.

```sql
SELECT FirstName, LastName, Email
FROM OFTRS.User
WHERE Nickname = 'john2937'
INTERSECT
SELECT FirstName, LastName, Email
FROM OFTRS.User
WHERE PhoneNumber IS NOT NULL
```

### Nested SELECT

This query retrieves the name, date, and place of all training sessions where the trainer type is ‘MMA Intermediate’.

```sql
SELECT tr.TrainingName, tr.TrainingDate, tr.Place
FROM OFTRS.Training tr
WHERE tr.TrainerID IN (
    SELECT TrainerID
    FROM OFTRS.Trainer
    WHERE Type = 'MMA Intermediate'
)
```

### Bonus Query

This query joins the User, Trainer, and Training tables together to retrieve information about all users who are also trainers and the trainings they provide. The SELECT statement specifies which columns to retrieve: the UserID, FirstName, and LastName columns from the User table, the TrainerID column from the Trainer table, and the TrainingName and TrainingDate columns from the Training table.

```sql
SELECT u.UserID, u.FirstName, u.LastName, t.TrainerID, t.type, tr.TrainingName, tr.TrainingDate, tr.place
FROM OFTRS.User u
INNER JOIN OFTRS.Trainer t ON u.UserID = t.UserID
INNER JOIN OFTRS.Training tr ON t.TrainerID = tr.TrainerID;
```

<br>

## SQL Reservations Table

## JPA - Java Persistence API


