# Train Ticket Reservation API

## Overview

The Train Ticket Reservation API allows users to submit ticket purchases, allocate seats, view receipts, and manage seat allocations for a train. The train has two sections: **Section A** and **Section B**. This API provides endpoints to:

1. Submit a ticket purchase and allocate a seat.
2. View receipt details for a user.
3. View the users and the seats they are allocated in the requested section.
4. Remove a user from the train.
5. Modify a user's seat.

## API Endpoints

### 1. Submit Ticket Purchase and Allocate Seat

**POST** `/api/tickets/purchase`

This endpoint allows users to submit a purchase for a ticket, which includes details such as the **fromLocation** location, **toLocation** location, **User's Name**, **Email Address**, **Section** and the **Price Paid**. Once the ticket is purchased, the user will be allocated a seat in either Section A or Section B.

**Request Body:**

```json
{
  "fromLocation": "London",
  "toLocation": "France",
  "price_paid": 5.0
}
```

**Request Paran:** UserID

**Response:**

```json
{
  "id": 1,
  "fromLocation": "London",
  "toLocation": "France",
  "user": {
    "firstName": "John",
    "lastName": "Lynch",
    "email": "john.lynch@example.com"
  },
  "section": "A"
  "price_paid": 5.0,
  "seatNumber": A1
}
```

### 2. View Receipt Details

**GET** `/api/tickets/receipt/{user_id}`

This endpoint returns the receipt details for a user based on their **user_id**. The receipt includes the user’s information, travel details, and the seat allocated.

**Response:**

```json
{
  "id": 1,
  "fromLocation": "London",
  "toLocation": "France",
  "user": {
    "firstName": "John",
    "lastName": "Lynch",
    "email": "john.lynch@example.com"
  },
  "section": "A"
  "price_paid": 5.0,
  "seatNumber": A1
}
```

### 3. View Users and Seat Allocations by Section

**GET** `/api/sections/{section}/users`

This endpoint allows you to view all users and the seats they are allocated within a specific section of the train. The available sections are **A** and **B**.

**Parameters:**

- `section`: Either "A" or "B".

**Response:**

```json
{
  "id": 1,
  "fromLocation": "London",
  "toLocation": "France",
  "user": {
    "firstName": "John",
    "lastName": "Lynch",
    "email": "john.lynch@example.com"
  },
  "section": "A"
  "price_paid": 5.0,
  "seatNumber": A1
}
```

### 4. Remove User from Train

**DELETE** `/api/tickets/remove/{user_id}`

This endpoint allows you to remove a user from the train by **user_id**. Once removed, their seat will be available for other users.

**Response:**

200 OK Status

### 5. Modify User’s Seat

**PUT** `/api/tickets/modify/{user_id}`

This endpoint allows you to modify a user’s seat. The user’s current seat will be updated to a new seat in either Section A or Section B.

**Request Param:** New Seat

**Path Variable:** User Id

**Response:**

```json
{
  "id": 1,
  "fromLocation": "London",
  "toLocation": "France",
  "user": {
    "firstName": "John",
    "lastName": "Lynch",
    "email": "john.lynch@example.com"
  },
  "section": "A"
  "price_paid": 5.0,
  "seatNumber": A1
}
```

## Installation

### Prerequisites

- Java 17
- Maven
- GIT
- And definitely an internet connection
- In-memory storage or a database for ticket information

### Step 1: Clone the Repository

```bash
git clone https://github.com/IamAkshayBhat/train-reservation.git
```

### Step 2: Build Project

```bash
mvn clean install 
```

### Step 3: Run Your Spring Boot App


The API will be running on `http://localhost:8080`.

### Step 3: To Play with the app after running your application.

You can do it using 'http://localhost:8080//swagger-ui/index.html'

## Example Usage

---

**Add a User:**

![AddUser](https://github.com/user-attachments/assets/722c8107-0f5d-4cd8-83c4-47888e3a26b6)


---

**View Receipt:**

![GetReceipt](https://github.com/user-attachments/assets/e7ef1f7e-a8a4-4855-b550-7abb46491e21)



---

**Get Details By Section**

![GetSection](https://github.com/user-attachments/assets/467443be-6220-4d1c-ba19-b0da83dc5258)



---

**Purchase a Ticket:**


![PurchaseTicket](https://github.com/user-attachments/assets/1e73fcd4-0bd5-4a9a-b6e6-e28f583b5b05)
