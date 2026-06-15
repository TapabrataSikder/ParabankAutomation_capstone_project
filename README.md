# ParaBank Automation Framework

**Selenium + TestNG + Maven | Page Object Model**

Capstone Project — SDET Java Training 
**Author:** Tapabrata Sikder 

---

## Overview

This project is an end-to-end automation test suite for the [ParaBank](https://parabank.parasoft.com/parabank/index.htm) demo banking application. It is built using the **Page Object Model (POM)** design pattern with **Selenium WebDriver**, **TestNG**, and **Maven**, and is integrated with a **Jenkins CI/CD pipeline** for automated execution.

The suite covers the full user journey — from registration and login through account management, fund transfers, bill payments, and logout — with **data-driven testing** powered by Excel and **automatic screenshot capture** on failures.

---

## Tech Stack

| Tool / Library        | Version   | Purpose                          |
|-----------------------|-----------|----------------------------------|
| Java (JDK)            | 1.8+      | Programming language             |
| Selenium WebDriver    | 4.44.0    | Browser automation               |
| TestNG                | 7.12.0    | Test framework & execution       |
| Maven                 | 3.x       | Build & dependency management    |
| Apache POI            | 5.5.1     | Excel-based data-driven testing  |
| WebDriverManager      | 5.9.1     | Automatic ChromeDriver setup     |
| Commons IO            | 2.16.1    | File handling utilities          |
| Jenkins               | —         | CI/CD pipeline                   |

---

## Project Structure

```
ParabankAutomation/
│
├── src/
│   ├── main/java/
│   └── test/
│       ├── java/
│       │   ├── base/
│       │   │   └── BaseTest.java              # WebDriver setup & teardown
│       │   ├── pages/                         # Page Object classes
│       │   │   ├── RegistrationPage.java
│       │   │   ├── LoginPage.java
│       │   │   ├── OpenAccountPage.java
│       │   │   ├── AccountOverviewPage.java
│       │   │   ├── TransferFundsPage.java
│       │   │   ├── BillPayPage.java
│       │   │   └── LogoutPage.java
│       │   ├── testcases/                     # Test classes
│       │   │   ├── RegistrationTest.java
│       │   │   ├── LoginTest.java
│       │   │   ├── NegativeLoginTest.java
│       │   │   ├── OpenAccountTest.java
│       │   │   ├── AccountOverviewTest.java
│       │   │   ├── TransferFundsTest.java
│       │   │   ├── BillPayTest.java
│       │   │   └── LogoutTest.java
│       │   └── utilities/                     # Helper/utility classes
│       │       ├── ConfigReader.java
│       │       ├── ExcelUtils.java
│       │       ├── ScreenshotUtil.java
│       │       └── TestListener.java
│       └── resources/
│           ├── config.properties              # App URL & browser config
│           └── TestData.xlsx                  # Excel test data (Sheet1, Sheet2, Sheet3)
│
├── screenshots/                               # Auto-captured screenshots
├── target/                                    # Maven build output
├── test-output/                               # TestNG reports
├── testng.xml                                 # TestNG suite configuration
├── Jenkinsfile                                # CI/CD pipeline definition
└── pom.xml                                    # Maven project configuration
```

---

## Test Cases

| # | Test Class             | Description                                        | Group                |
|---|------------------------|----------------------------------------------------|----------------------|
| 1 | `RegistrationTest`     | Registers a new user using Excel data (Sheet1)     | —                    |
| 2 | `LoginTest`            | Validates successful login with valid credentials  | `LoginGroup`         |
| 3 | `NegativeLoginTest`    | Verifies error display for invalid credentials     | —                    |
| 4 | `OpenAccountTest`      | Opens a new SAVINGS account after login            | `OpenAccountGroup`   |
| 5 | `AccountOverviewTest`  | Reads and validates account IDs and balances       | `AccountOverviewGroup` |
| 6 | `TransferFundsTest`    | Transfers funds between accounts with balance check| `TransferFundsGroup` |
| 7 | `BillPayTest`          | Completes bill payment and validates balance update| `BillPaymentGroup`   |
| 8 | `LogoutTest`           | Logs out and verifies return to login page         | `LogoutGroup`        |

**Test Execution Order** (managed via `dependsOnGroups` in TestNG):
`Registration → Login → OpenAccount → AccountOverview → TransferFunds → BillPay → Logout`

---

## Test Data

Test data is maintained in `src/test/resources/TestData.xlsx`:

- **Sheet1** — Registration data (first name, last name, address, city, state, zip, phone, SSN, username, password, confirm password)
- **Sheet2** — Valid login credentials
- **Sheet3** — Invalid login credentials (for negative testing)

Application configuration is in `src/test/resources/config.properties`:

```properties
browser=chrome
appurl=https://parabank.parasoft.com/parabank/index.htm
```

---

## How to Run

### Prerequisites

- Java JDK 1.8 or higher installed
- Maven 3.x installed and added to PATH
- Google Chrome browser installed
- ChromeDriver is managed automatically by WebDriverManager

### Run via Maven

```bash
# Clone the repository
git clone <your-github-repo-url>
cd ParabankAutomation

# Run the full test suite
mvn clean test
```

### Run via TestNG XML (Eclipse/IntelliJ)

Right-click on `testng.xml` → **Run As → TestNG Suite**

### Run via Jenkins

The project includes a `Jenkinsfile` for CI/CD. The pipeline:
1. Checks out source code from GitHub
2. Installs Maven and JDK tools
3. Runs `mvn clean test` inside the `ParabankAutomation` directory
4. Archives TestNG XML reports from `target/surefire-reports/`

---

## Key Design Patterns & Features

**Page Object Model (POM)** — Each web page is represented by a dedicated Java class with locators and methods, keeping test logic separate from UI interaction.

**Data-Driven Testing** — Test data is read from Excel files using Apache POI via `ExcelUtils.java`, fed to tests through TestNG `@DataProvider`.

**Explicit Waits** — `WebDriverWait` with `ExpectedConditions` is used throughout page classes for reliable element interaction.

**Auto Screenshot on Failure** — `TestListener.java` implements `ITestListener` and calls `ScreenshotUtil.takeScreenshot()` automatically whenever a test fails. Screenshots are timestamped and saved to the `screenshots/` folder.

**Centralized Configuration** — `ConfigReader.java` reads from `config.properties`, making it easy to switch environments without touching test code.

**Group-Based Execution & Dependencies** — TestNG groups and `dependsOnGroups` enforce correct execution order and allow selective test runs.

---

## Test Results

All 8 test cases passed successfully across all execution cycles:

```
ParaBank Suite
Total tests run: 8, Passes: 8, Failures: 0, Skips: 0
```

> **Note on Console Warnings:** During execution, CDP (Chrome DevTools Protocol) version mismatch warnings appear in red. These are framework-level informational notices from Selenium WebDriver and have **no impact** on test behaviour or results. This project does not use any CDP-dependent features.

---


## Contact

**Tapabrata Sikder**
