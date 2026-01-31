# API Automation Testing – GraphQL

This repository contains API automation tests for a **GraphQL-based service**.  
The tests are designed to validate request and response behavior and to support regression testing through automated execution.

---

## API Under Test

**API Type**  
GraphQL

**Endpoint URL**  
https://lmsb2b.do.dibimbing.id/graphql

---

## Automation Scope

- Automation testing for GraphQL APIs
- Validation of GraphQL queries and mutations
- Response verification (data, errors, and schema consistency)
- Regression-ready test scenarios

---

## Tech Stack

- Java
- GraphQL API Testing
- Gradle
- Allure Report
- GitHub Actions (CI/CD)

---

## Project Structure

src
└── test
└── java
├── api # GraphQL API test cases
└── utils # Configuration and helper classes


---

## Environment Configuration

Create an environment file based on the example provided:

```bash
cp .env.example .env

Update the required values in the .env file before running the tests.
