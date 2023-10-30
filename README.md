# Simple Book API Testing

This project is designed to demonstrate how to use RestAssured, Cucumber, Java, and Maven to test a simple book API.

## Table of Contents

- [Description](#description)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Running the Tests](#running-the-tests)
- [Test Structure](#test-structure)
- [Reporting](#reporting)
- [License](#license)

## Description

This project provides a set of automated tests for a simple book API using RestAssured for API testing, Cucumber for behavior-driven development (BDD), Java for the programming language, and Maven for project management.

## Features

- Automated testing of a simple book API endpoints
- Behavior-driven development (BDD) with Cucumber
- Easy integration with CI/CD pipelines
- Maven-based project management

## Prerequisites

Before running the automated tests, make sure you have the following prerequisites installed:

- Java Development Kit (JDK) 8 or above
- Apache Maven
- Git

## Setup

1. Clone the repository using the following command:
```bash
git clone https://github.com/andytheqaguy/SimpleBookAPITesting
```
2. Navigate to the project directory:
```bash
cd simple-book-api-testing
```
3. Build the project using Maven:

```bash
mvn clean install
```

## Running the Tests

To run the tests, use the following Maven command:
```bash
mvn clean verify
```
This will trigger the tests written using Cucumber and RestAssured against the "Simple Book API".

## Test Structure

The automated tests in this repository are structured using the BDD (Behavior-Driven Development) approach and written in Gherkin syntax. The feature files can be found in the `src/test/resources/features` directory, and the corresponding step definitions are implemented in Java under the `src/test/java/keywords` directory.

- `src/test/resources/features`: Contains feature files written in Gherkin syntax that describe the desired behavior of this project.
- `src/test/java/keywords`: Contains Java step definition classes that map the Gherkin steps to the corresponding automation code.

## Reporting

Serenity BDD generates comprehensive and easily understandable reports after test execution. The reports are available in HTML format and can be found in the `target/site/serenity` directory `index.html` file after running the tests.

## License

This project is licensed under the [MIT License](LICENSE).
