# Banking Application

## Project Overview

This project is a simple banking application that allows users to store account and transaction information in a MySQL database. The project demonstrates fundamental CRUD (Create, Read, Update, Delete) operations, transaction handling, and basic fund transfer functionalities. It was developed as part of a Udemy course by instructor **Ramesh Fadatare**, using Spring Boot and MySQL as the database.

## Features

1. **Add an Account**: Create a new account and store it in the database.
2. **View Account by ID**: Retrieve account information using an account ID.
3. **Deposit Funds**: Deposit an amount to a specific account.
4. **Withdraw Funds**: Withdraw an amount from a specific account.
5. **Transfer Funds**: Transfer funds between two accounts.
6. **View All Accounts**: Fetch details of all accounts stored in the database.
7. **Delete Account**: Remove an account by its ID.
8. **View Account Transactions**: Fetch all transactions associated with a specific account.

## Technologies Used

- **Spring Boot**: Backend framework for building the REST API.
- **MySQL**: Relational database to store account and transaction details.
- **Spring Data JPA**: ORM tool for database interaction.
- **FasterXML Jackson**: To handle JSON serialization/deserialization.
- **Spring Web MVC**: To build the REST API.
- **Maven**: Dependency management.

### Controller: `AccountController.java`

Handles the RESTful API endpoints for managing accounts and transactions. The following endpoints are provided:

- **POST** `/api/accounts`: Add a new account.
- **GET** `/api/accounts/{id}`: Get account by ID.
- **PUT** `/api/accounts/{id}/deposit`: Deposit an amount into an account.
- **PUT** `/api/accounts/{id}/withdraw`: Withdraw an amount from an account.
- **POST** `/api/accounts/transfer`: Transfer funds between accounts.
- **GET** `/api/accounts`: Get a list of all accounts.
- **DELETE** `/api/accounts/{id}`: Delete an account by ID.
- **GET** `/api/accounts/{id}/transactions`: Fetch all transactions related to an account.

### DTOs

- **AccountDto**: Data Transfer Object for handling account information.
- **TransactionDto**: Data Transfer Object for handling transaction information.
- **TransferFundDto**: Data Transfer Object for handling fund transfers between accounts.

### Database Schema

- **Account**: Stores account information like account number, balance, etc.
- **Transaction**: Stores transactions such as deposits, withdrawals, and fund transfers.

### API Documentation

For detailed API documentation, you can use tools like **Postman** to test the endpoints. The typical request/response format for each API call is in JSON.

## Acknowledgments

This project was developed under the guidance of **Ramesh Fadatare**, as part of his Udemy course on Spring Boot. Special thanks to his tutorial series for providing the foundational knowledge.
