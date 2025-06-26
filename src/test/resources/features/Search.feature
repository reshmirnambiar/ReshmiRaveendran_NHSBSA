@smoke
Feature: Search for jobs in the NHS

  Background: 
    Given I navigate to the search page

  Scenario Outline: Search for jobs with Job title
    When I search for job with "<Job Title>"
    And I click Search button
    Then I should see search result for "<Job Title>"
    And I sort the results by "Date Posted (newest)"
    And the results should be sorted by date

    Examples:
      | Job Title     |
      | Nurse        |
      | Developer    |
      | Pharmacist   |

  Scenario: Search for jobs with Postcode
    When I search for jobs using postcodes "NE15 8NY"
    And I click Search button
    Then I should see search result for postcode "NE15 8NY"
    And I sort the results by "Date Posted (newest)"
    And the results should be sorted by date

  Scenario: Search for jobs with Town/City
    When I search for jobs using town "newcastle upon tyne"
    And I click Search button
    Then I should see search results for town "newcastle upon tyne"
    And I sort the results by "Date Posted (newest)"
    And the results should be sorted by date

  Scenario: Verify More search options
    When I click on More Search Options
    Then I see all 4 search fields on search screen

  Scenario: Search for an job with Employer
    When I click on More Search Options
    When I search for job with employer "NHS Business Services Authority"
    And I click Search button
    Then I should see search result for employer "NHS Business Services Authority"
    And I sort the results by "Date Posted (newest)"
    And the results should be sorted by date

  Scenario: Search with invalid Job Reference/Title
    When I search for job with "INVALIDJOB"
    And I click Search button
    Then I should see a message "No result found"
    And the results should be sorted by date





