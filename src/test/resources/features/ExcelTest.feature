@Excel @desktopAutomation @ignore
Feature: MSExcel Test

  Scenario: Verify data can be write/read from worksheet
    Given User launches the MS Excel
    When User creates new project
    And User navigates to View tab
    And User writes Test in "A" 1
    Then Cell "A" 1 has Test value