# Mini Wallet Management System

A backend implementation of a prepaid Mini Wallet system built as a machine-coding / LLD exercise.

The system supports user registration, wallet top-ups, wallet-to-wallet transfers, transaction history, filtering, and sorting.

---

## Problem Statement

Design and implement a prepaid Mini Wallet system where users can:

- Register themselves
- Top up their wallet using different payment methods
- Send money to another wallet
- Receive money from another wallet
- Fetch their wallet balance
- View transaction history
- Filter transactions by Send / Receive
- Sort transactions by amount or transaction timestamp

The implementation uses in-memory data structures and does not require a database or external payment integrations.

---

## Functional Requirements

### 1. User Registration

A user can register using a unique username.

On registration:

- A unique user ID is generated.
- A wallet is created for the user.
- Initial wallet balance is `0`.

### 2. Wallet Top-Up

A user can add money to their wallet using:

- UPI
- Credit Card
- Debit Card

The actual external payment integration is out of scope. Payment strategies simply acknowledge the top-up.

### 3. Fetch Wallet Balance

A user's current wallet balance can be fetched using their username.

### 4. Send Money

A user can transfer money from their wallet to another user's wallet.

Rules:

- Transfer amount must be greater than zero.
- Sender must have sufficient balance.
- Sender and receiver must exist.

### 5. Transaction History

Users can fetch their transaction history.

Transactions can be:

- Filtered by `SEND`
- Filtered by `RECEIVE`
- Sorted by `AMOUNT`
- Sorted by `TIMESTAMP`

Top-up transactions are represented separately using `TransactionType.TOP_UP`.

---

### Running the Application

1. Clone the repository:

`git clone <repository-url>`

2. Navigate to the project:

`cd mini-wallet-management-system`

3. Run the application:

`./gradlew bootRun`