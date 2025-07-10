

## Description

- **Java**: Ensure you have Java 11 or higher installed.
- **Maven**: Install Maven for dependency management and build automation.
- **Allure**: Reporting tool.
- **OpenApi Generator**: Used for automatic DTO generation.

## Running Tests

To execute the tests, use the following Maven command:
```bash
mvn clean test
mvn clean test -Dallure.results.directory=target/allure-results - if results aren't created with previous command
```

## Generating Allure Report

1. After running the tests, generate the Allure report:
   ```bash
   allure generate target/allure-results -o target/allure-report --clean
   ```

2. Serve the report locally to view it in a browser:
   ```bash
   allure serve target/allure-results
   ```

## Project Structure

- `src/main/java`: Contains the main source code, including utilities and listeners.
- `src/test/java`: Contains the test cases.
- `src/test/resources/testng.xml`: TestNG configuration file for running tests.
