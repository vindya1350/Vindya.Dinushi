Feature: Shopping Toys

  Scenario: Verify total cost of bought toys are correct
    Given I login to site
    When I am in shop page
    And I buy following items
      | Product        | Count |
      | Stuffed Frog   | 2     |
      | Fluffy Bunny   | 5     |
      | Valentine Bear | 3     |
    And I navigate to cart page
    Then I can see correct quantity, product and subtotal are correct
    And I can see Total cost is correct
