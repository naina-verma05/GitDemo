Feature: Purchase the order from e-commerce Website

  Background:
    Given I landed on Ecommerce Home Page

  Scenario Outline: Positive test of submitting the order
    Given I Logged in with "<username>" and "<password>"
    When I add product <productName> to the cart
    And I checkout "<productName>" and submit the order
    Then I verify the "Thankyou for the order." message is displayed on the screen.


    Examples:
      | username                  | password      |productName      |
      | nancy.verma0591@gmail.com | Ndiljuly@2025 | ZARA COAT 3     |
#      | naina.verma0591@gmail.com | Ndiljuly@2025 | ADIDAS ORIGINAL |