Feature: Test saucedemo.com

  @loginTest1
  Scenario: Login functionality
    Given Login page is opened

    When Username and password "standard_user" and "secret_sauce" are entered
    And Login is attempted
    Then Catalog is displayed

    @addToCartTest

    Scenario: Add product to cart
      Given Login page is opened
      When Username and password "standard_user" and "secret_sauce" are entered
      And Login is attempted
      Then Catalog is displayed

      When User adds to cart product "Sauce Labs Backpack"
      And User goes to shopping cart
      Then Product "Sauce Labs Backpack" is added to cart check

  @checkoutTest

  Scenario Outline: Checkout functionality
    Given Login page is opened
    When Username and password "<username>" and "<password>" are entered
    And Login is attempted
    Then Catalog is displayed

    When User adds to cart product "<productName>"
    And User goes to shopping cart
    Then Product "<productName>" is added to cart check

    When User clicks checkout button
    Then Checkout Info page is displayed

    When Enter checkout info "<checkoutFirstName>" "<checkoutLastName>" "<postalCode>"
    And Click checkout button
    Then Checkout Overview is displayed

    When Click finish on Checkout Overview page
    Then Checkout Complete page is displayed
    And "<message>" is displayed

    When Go back to Products page
    Then Catalog is displayed

    Examples:
      | username      | password     | productName         | checkoutFirstName | checkoutLastName | postalCode | message                  |
      | standard_user | secret_sauce | Sauce Labs Backpack | Standard          | user             | 12346      | Thank you for your order |

  @removeItemTest

  Scenario: Add product to cart
    Given Login page is opened
    When Username and password "standard_user" and "secret_sauce" are entered
    And Login is attempted
    Then Catalog is displayed

    When User adds to cart product "Sauce Labs Backpack"
    And User goes to shopping cart
    Then Product "Sauce Labs Backpack" is added to cart check

    When Click remove button
    Then Product is removed check