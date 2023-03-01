Feature: Contact submission


  Scenario: Verify contact submission fail when try to directly click on submit button
    Given I am in the shopping site
    When I submit contact form without filling any mandatory fields
    Then I can see following error messages are populated for Mandatory fields
      | FieldName | ErrorMsg             |
      | Forename  | Forename is required |
      | Email     | Email is required    |
      | Message   | Message is required  |
    When I submit after filling all mandatory fields with 'John','John@gmail.com' and 'Test Text'
    Then I can not see error messages

  @Test(invocationCount=5)
  Scenario: Verify correct contact submission
    Given I am in the shopping site
    When I am in contact page
    And I submit after filling all mandatory fields with 'Dan','dan@gmail.com' and 'Test'
    Then I can see success messages as 'Thanks Dan, we appreciate your feedback'