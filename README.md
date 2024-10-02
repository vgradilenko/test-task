# Demo API project

This repository contains a **Test Task** designed to demonstrate basic project setup, structure, and functionality.

## Table of Contents

- [Project Overview](#project-overview)
- [Architecture Overview](#architecture-overview)
- [Technologies Used](#technologies-used)
- [Project Setup](#project-setup)
- [Running the Tests](#running-the-tests)
- [CI Pipeline](#ci-pipeline)

## Project Overview
This demo project is focused on automating and validating certain features for an application as part of a test task. It uses Java, Maven, Retrofit and TestNG to manage and run the test cases. The goal is to provide a framework for effective testing, including comprehensive test automation and reporting.

## Architecture Overview
The project follows a layered architecture to ensure modularity, scalability, and ease of maintenance. Below are the key components of the architecture:

### 1. **Test Layer**
This layer contains all the test cases, written using TestNG. Each test case is structured around the following principles:
- **Modularity**: Tests are independent and modular to ensure they can be run in isolation.
- **Parameterization**: Tests are parameterized to handle different input conditions, allowing flexible execution across environments.
- **Thread Safety**: The tests are designed to be thread-safe, ensuring reliable execution in parallel test runs. This allows tests to be run concurrently without conflicts or interference between threads.
### 2. **MVC Architecture**
The project follows the **Model-View-Controller (MVC)** architectural pattern, which ensures separation of concerns and improves maintainability.
### 3. **Utilities Layer**
- **Helper Classes**: These provide utility functions for actions such as data generation, API calls, and file handling.
- **Test Data Management**: Test data is stored in external files (JSON, XML, etc.), ensuring easy updates without code changes. The data is loaded dynamically into the test cases.
### 4. **Configuration Layer**
- **Environment Configuration**: Configuration settings like environment URLs and other dynamic data are defined externally in `remote.properties.` This allows easy switching between different environments (remote, local) without changing the test code.
- **Logging**: The project includes logging at various levels (INFO, DEBUG, ERROR) to capture detailed runtime information.
### 5. **Reporting**
- **Allure Reporting**: The project integrates with Allure to generate detailed test execution reports. Allure reports provide insights into test results, including failure logs, step-by-step execution, and detailed logs.

## Technologies Used

- **Java 17**: Core programming language.
- **Maven**: Dependency management and build automation tool.
- **TestNG**: Test framework used for writing and executing test cases.
- **Allure**: Reporting tool to generate detailed reports on the test results.
- **Retrofit**: REST Client for Java allowing to retrieve and upload JSON (or other structured data) via a REST based You can configure which converters are used for the data serialization
- **Git**: Distributed version control system that tracks versions of files
- **Owner**: Property management system for java

## Project Setup

### Prerequisites

Make sure you have the following installed on your machine:

- Java 17 (or higher)
- Maven
- Git (to clone the repository)

### Clone the Repository

To clone the repository, use the following command:

```bash
git clone https://github.com/vgradilenko/test-task.git
cd test-task 
```
### Install Dependencies
```bash
mvn install -DskipTests
```
### Run the Tests
```bash
mvn clean test
```
### Run and check report
```bash
mvn allure:report
mvn allure:serve
```
## CI Pipeline
The project uses GitHub Actions for continuous integration. The pipeline automatically runs on every push to the main branch and includes the following steps:

Checkout the code.
Set up Java 17.
Cache Maven dependencies.
Run the tests.
Generate and upload Allure reports (if configured).
You can view the pipeline configuration in the .github/workflows folder.