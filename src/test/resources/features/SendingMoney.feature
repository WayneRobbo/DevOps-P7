Feature: Sending money to a friend using Revolut.
  This feature describes various scenarios for users sending money to a friends Revolut.

  @testSuite1
  #Part 3 - Demonstrate understanding of BBD with Cucumber by implementing - b.Sending money to a friend using Revolut.
  Scenario: Remove money from account after sending money to a friend.
    Given Danny selects his Joe to send money to
    And Danny has a revolut balance of 50 euros
    When Danny sends 10 using the correct cvvPin 111 to joe
    Then Danny has a new revolut balance of 40

  @testSuite1
  # Check receivers account after action.
  Scenario: Add money to account after receiving money from a friend.
    Given Joe has received money from Danny.
    And Danny has a revolut balance of 50
    And Joe already has an initial balance of 70.
    When Danny sends 10 euro using the correct cvvPin 111 to joe
    Then Joe's account increases to 80.

  @testSuite1
  # Funds not available in senders account, check senders account after action.
  Scenario: Insufficient funds in senders account.
    Given Danny has 50 euro in his euro Revolut account
    When Danny sends 100 euro using the correct cvvPin 111 to joe
    Then Danny still has a account balance of 50.

  @testSuite1
  # Funds not available in senders account, check receivers account after action.
  Scenario: Insufficient funds in senders account, check receivers account.
    Given Joe already has an initial balance of 70.
    And Danny has a revolut balance of 50
    When Danny sends 100 using the correct cvvPin 111 to joes account.
    Then Joe's account should remain the same with 70.

  @testSuite1
  Scenario Outline: Sending various amounts to friend
    Given Danny has a starting balance of <startBalance>
    And Danny send money to joe.
    When Danny sends <sendAmount> using the correct cvvPin <pin> to joe
    Then The balance in his euro account should be <remainingAmount>
    Examples:
      | startBalance  | sendAmount    |   pin    |   remainingAmount  |
      | 200           | 100           |   111    |         100        |
      | 50            | 25            |   111    |          25        |
      | 100           | 200           |   111    |         100        |