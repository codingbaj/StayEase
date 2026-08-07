# StayEase - PG Management System

## Project Overview

StayEase is a PG (Paying Guest) Management System developed using Spring Boot and MySQL. The project will provide role-based access for Admin, Staff, and Tenant to manage buildings, rooms, tenants, rent, payments, complaints, notices, and visitors.

---

# Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Git & GitHub
- Postman

Future:
- Spring Security
- JWT Authentication
- React
- Tailwind CSS
- Spring Mail
- Scheduler

---

# Current Progress

## Completed

### User Module

Implemented complete CRUD.

APIs

POST    /user/add

GET     /user/all

GET     /user/{id}

PUT     /user/update/{id}

PUT     /user/update-role/{id}

DELETE  /user/delete/{id}

Features

- Add User
- Get All Users
- Get User By ID
- Update User Details
- Update User Role
- Delete User

---

### Building Module

Implemented complete CRUD.

APIs

POST    /building/add

GET     /building/all

GET     /building/{id}

PUT     /building/update/{id}

DELETE  /building/delete/{id}

Features

- Add Building
- Get All Buildings
- Get Building By ID
- Update Building
- Delete Building

---

# Common Architecture

Controller

↓

ApiResponse

↓

Service

↓

Repository

↓

MySQL

---

# Exception Handling

Package

exception

Classes

- ResourceNotFoundException
- ErrorResponse
- GlobalExceptionHandler

Purpose

Automatically handles resource not found errors.

Example Response

```json
{
  "timestamp": "...",
  "status":404,
  "message":"User not found"
}
```

---

# ApiResponse

All successful API responses return

```json
{
  "success": true,
  "message": "Success message",
  "data": { }
}
```

---

# Database Tables Created

- users
- building
- floor
- room
- roomtype
- bed
- tenant
- allocation
- rent
- payment
- complaint
- notice
- visitor

Relationships will be completed while implementing CRUD.

---

# User Roles

ADMIN

- Full Access

STAFF

- Manage Building
- Room
- Bed
- Complaint
- Visitor
- Rent

TENANT

- Login
- View Profile
- View Rent
- Payment
- Complaint
- Notice
- Notification

---

# Current Entity Fields

## User

- id
- name
- email
- password
- role
- phone

---

## Building

- id
- buildingCode
- buildingName
- address
- totalFloors
- description

Relationship

Building

↓

OneToMany

↓

Floor

---

# Coding Pattern

Service

Returns Entity

Example

```java
return repo.save(user);
```

Controller

Returns ApiResponse

Example

```java
return new ApiResponse(
    true,
    "User Added Successfully",
    savedUser
);
```

---

# Git Workflow

Before Working

git pull origin main

After Completing Module

git add .

git commit -m "Complete Building CRUD module"

git push origin main

---

# Remaining Modules

1. Floor

2. RoomType

3. Room

4. Bed

5. Tenant

6. Allocation

7. Rent

8. Payment

9. Complaint

10. Notice

11. Visitor

---

# Future Features

- JWT Authentication
- Spring Security
- Email Notification
- Monthly Rent Scheduler
- PDF Receipt
- Dashboard
- Charts
- React Frontend
- WhatsApp Notification (Optional)

---

# Coding Rules

- Controller returns ApiResponse.
- Service returns Entity.
- Repository communicates with Database.
- Exception handled using GlobalExceptionHandler.
- Business Logic must be written inside Service.
- React should never calculate rent or fine.

---

# Next Module

Floor Module

Frontend will send

```json
{
    "floorNumber":1,
    "buildingId":1
}
```

Service will

- Find Building
- Set Relationship
- Save Floor

---

Project Status

User Module          ✅

Building Module      ✅

Floor Module         ⏳

RoomType Module      ⏳

Room Module          ⏳

Bed Module           ⏳

Tenant Module        ⏳

Allocation Module    ⏳

Rent Module          ⏳

Payment Module       ⏳

Complaint Module     ⏳

Notice Module        ⏳

Visitor Module       ⏳

Authentication       ⏳

React Frontend       ⏳

Email Scheduler      ⏳

# Constants & Allowed Values

This section defines all allowed values used throughout the project.

---

## User Roles

Only these roles are allowed.

| Role | Description |
|------|-------------|
| ADMIN | Full system access |
| STAFF | Manages buildings, rooms, beds, tenants, rent, complaints, visitors |
| TENANT | Can login, view rent, payment, complaints, notices and profile |

Example

```json
{
    "role":"ADMIN"
}
```

---

## Room Status

| Status |
|---------|
| AVAILABLE |
| OCCUPIED |
| MAINTENANCE |

Example

```json
{
    "status":"AVAILABLE"
}
```

---

## Bed Status

| Status |
|---------|
| AVAILABLE |
| OCCUPIED |
| MAINTENANCE |

---

## Allocation Status

| Status |
|---------|
| ACTIVE |
| COMPLETED |
| CANCELLED |

---

## Rent Status

| Status |
|---------|
| PENDING |
| PAID |
| OVERDUE |

---

## Payment Status

| Status |
|---------|
| SUCCESS |
| FAILED |
| PENDING |

---

## Payment Method

| Method |
|---------|
| CASH |
| UPI |
| CARD |
| NET_BANKING |

---

## Complaint Status

| Status |
|---------|
| OPEN |
| IN_PROGRESS |
| RESOLVED |
| CLOSED |

---

## Visitor Purpose

Examples

- Family Visit
- Friend Visit
- Food Delivery
- Maintenance
- Courier
- Other

---

## Building Code

Building code must be unique.

Examples

A

B

C

D

E

---

## Room Number Format

Recommended

A101

A102

B201

C301

Meaning

A = Building

1 = Floor

01 = Room Number

---

## Date Format

Use

```text
yyyy-MM-dd
```

Example

```text
2026-08-07
```

---

## API Response Format

Successful Response

```json
{
    "success": true,
    "message": "User added successfully",
    "data": { }
}
```

Error Response

```json
{
    "timestamp":"2026-08-07T14:30:10",
    "status":404,
    "message":"User not found"
}
```
