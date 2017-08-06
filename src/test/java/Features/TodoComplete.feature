
Feature: Perform Todo actions - create, edit , delete, filter

  @complete
  Scenario Outline: Editing a new Todo Entry
    Given I am on todomvc home page
    When I click on "AngularJS" link
    And I add a Todo item entry with text <Todo>
    And I edit the new entry <Todo> text to <NewText>
    And I mark the entry with text <NewText> as "Complete"
    And I mark the entry with text <NewText> as "Active"
    And I add a Todo item entry with text <SecondEntry>
    And I mark all todo items as "Complete"
    And I filter the todo entries by "Complete" state
    And I remove a todo entry with text <NewText>
    Then I clear all completed
    Examples:
      |Todo|NewText|SecondEntry|
      |My first todo item|Edited the first entry|My second todo item|