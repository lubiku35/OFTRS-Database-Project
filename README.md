
# Online Fitness Training Reservation System | Database Project

> **Author** | Ľuboslav Motošický, @lubiku35
>
> **Project Name** | OFTRS - Online Fitness Training Reservation System


## Introduction

The Online Fitness Training Reservation System project is an innovative demo database solution for any sports club or fitness center that is interested in enriching their reservation workflow by storing data about their clients, coaches, workouts, reservations, and payments in the database.

Specifically in this project you can discover a prototype database with testing data which includes exactly 32,000 users and of them approximately 50 trainers, moreover the database contains 50 training session types which are restricted to their capacity and several test reservations along with the payments made. 

## Navigation

- [Involved Technologies](#involved-technologies)
- [Essential Model Description](#essential-model-description)
    - [User](#user)
    - [Trainer](#trainer)
    - [Training](#training)
    - [Reservation](#reservation)
    - [Payment](#payment)
    - [Calendar](#calendar)
- [Conceptual Model](#conceptual-model)
- [Relational Model](#realtional-model)
    - [Entity User](#entity-user)
    - [Entity Trainer](#entity-trainer)
    - [Entity Training](#entity-training)
    - [Entity Reservation](#entity-reservation)
    - [Entity Payment](#entity-payment)
    - [Entity Calendar](#entity-calendar)
<br>
    
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

![Conceptual Model](assets\conceptual_model.png)

<br>

## Realtional Model

### Entity User

**User** (<u>UserID</u>, Full Name, <u>E-mail</u>, <u>Phone Number</u>, Nickname)

- **Full Name** (<u>User</u>, FirstName, LastName)


### Entity Trainer

**Trainer**: (<u>UserID</u>, <u>TrainerID</u>, Certification)

- **Certification** (<u>Trainer</u>, Specification, Type, ValidityDate)
- **FK**: (UserID) ⊆ User(<u>UserID</u>)  / References


### Entity Training


### Entity Reservation


### Entity Payment


### Entity Calendar

