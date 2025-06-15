# Loan Calculator UI Tests

Automated UI tests for a loan calculator web application using **Java**, **Selenide** and **TestNG**.

---

## Stack

- **Java 17**
- **Selenide** for browser automation
- **TestNG** for test framework
- **WebDriverManager** for driver management
- **Allure** for test reports


---

## How to Run the Tests

### 1. Clone the repository

```bash
git clone https://github.com/your-username/loan-calculator-tests.git
cd loan-calculator-tests
```

### 2. Run tests with Maven

```bash
mvn clean test
```
This will run  all tests with the `@Test` tag and generate result files in `target/allure-results`


### 3. View Allure report
After running the tests, use
```bash
allure serve target/allure-results
``` 
to generate and open the report.

## Test coverage
The automated tests in this project focus on some of the core user flows:
- Changing loan amount and period both via input fields and by dragging the sliders
- Verifying that monthly payment is updated correctly based on user inputs


## My vision for a good test automation framework
A test automation framework should be:
- Simple to run, preferably with a single command (such as `mvn clean test`)
- Readable by others beside the author
- Easily maintainable, e.g. following Page Object Model to isolate test logic from changes made in the UI
- Visually observable with appropriate reporting tools (Allure in this project)
- Self-contained, with no external setup

## Author
Liis Nelis
[Github](https://github.com/liisnelis)

