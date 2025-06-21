# Bookstore API AUtomation Suite

This repository contains the API Automation tests for the Bookstore backend. It includes integration with **RestAssured**, **TestNG**, **Allure Reports**
and a Python based backend powered by **FastAPI**. CI/CD is managed via **Github Actions**.

## Test Flows

### JAVA (RestAssured + TestNG)

- 'HealthcheckTest' : Verifies the backend server is reachable
- 'BookTests' : CRUD opterations (Create, Get, Update, Delete)
- 'NegativeTests' : Tests invalid inputs and unauthorized access

## How to run tests locally

### 1. Start python backend
- cd bookstore
- pip install -r requirements.txt
- uvicorn main:app --reload

API will be available at http://localhost:8000

### 2. Run JAVA Tests
cd automation-java/automation-java
mvn clean test

### 3. Viewing Allure Reports
allure serve target/allure-results

### 4. GitHub Actions:
Reports are published on each push to main under Actions → Artifacts, or on the GitHub Pages URL.

### 5. CI/CD - GitHub Actions
On every push to main, the workflow will:

- Checkout the code
- Install Python dependencies
- Start the backend server
- Run Java API tests
- Generate and upload Allure report
- Deploy report to GitHub Pages



