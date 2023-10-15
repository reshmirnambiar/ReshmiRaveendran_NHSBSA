@smoke
Feature: NHS_Service_CheckForHelpPayingNHSCosts

  Background: 
    Given I launch application

  Scenario Outline: Verify what help Patient get to pay for NHS costs
    Given I am a citizen of the UK
    When I put my "<circumstances>" into the "<CheckerTool>"
    Then I should get a result of whether I will get help or not

     Examples: 
      | circumstances| CheckerTool       |
      |          1 	 | CheckerList       |
      |          2   | CheckerList       |
      
