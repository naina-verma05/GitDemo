Feature: Error Validation

    @Error
  Scenario Outline:
    Given I landed on Ecommerce Home Page
    And I Logged in with "<username>" and "<password>"
    Then I verify the error message.


    Examples:
      | username                  | password      |
      | nancy.verma05gmail.com | Ndiljuly@2025 |