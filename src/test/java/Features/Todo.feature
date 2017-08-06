
Feature: Perform Todo actions - create, edit , delete, filter

  @simple
  Scenario Outline: Adding a new Todo Entry
    Given I am on todomvc home page
    When I click on "AngularJS" link
    And I add a Todo item entry with text <Todo>
    Then I see the new todo item <Todo> in the list of todos
    Examples:
    |Todo|
    |My first todo item|

