# NHS Job  Automated Test Suite
This project is an automated testing framework for NHS Job search applications using Selenium WebDriver, Cucumber, and Maven.
It supports multiple browsers and allows running tests by tags or the full test suite via command-line parameters.

## Project Overview
This automation framework uses:
- 
- **Selenium WebDriver** for browser automation
- **Cucumber** for BDD-style test writing with Gherkin
- **Maven** for build and dependency management
- **WebDriverManager** for automatic driver management

## Prerequisites
- Java JDK 11 or higher
- Maven 3.6+
- Internet connection (for WebDriverManager to download browser drivers)
- Chrome or Edge browser installed on the machine


## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/nhs-automation.git
   cd nhs-automation
   
2. Build the project and download dependencies:
   mvn clean install

## Running Tests:
1. Run all tests (default browser is Edge if not specified):
 --mvn test

2. Run tests on a specific browser (Chrome or Edge):
 --mvn test -Dbrowser=chrome
 --mvn test -Dbrowser=edge

3. Run tests with specific tag(s):
--mvn test -Dcucumber.filter.tags="@smoke"

## Reporting

This project is configured to generate detailed test execution reports in two formats:

### 1. Cucumber HTML Report
- After the test run, a Cucumber-generated HTML report is created at:  
  `target/cucumber-reports 

### 2. Extent Report
- The Extent report file is saved under:  
  `target/extent-reports/extent-report.html`





