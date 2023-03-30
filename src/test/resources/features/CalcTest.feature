@calculator @desktopAutomation
Feature: Windows calculator tests

  Scenario: Summing two numbers
    Given User starts the calculator application
    When User inputs numbers
    And User presses equals
    Then Results should be correct