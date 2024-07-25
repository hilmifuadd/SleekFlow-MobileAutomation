@FirstTest
Feature: 00_Create_Project

#  Scenario: User creates a new project via API
#    Given a new project named "Test Project Hilmi"
#    When I create the project
#    Then the project should be created

  Scenario Outline: User verify in Mobile that Project already created
    Given user on the login page Todoist and want to log in with email
    When user enter email <email> and password <password> and Tap Login
    And user got successfully logged in and enters to menu browse
    And user create the project on API name <projectName>
    Then user verified that the project is successfully created
    And user create a task in mobile name <taskName>
    And user verified that the task is successfully created
    Examples:
      | email                   | password        | projectName         |  taskName |
      | "nyobasally1@gmail.com" | "initest123!@#" | "Mobile Project Hilmi" | "Test Task Hilmi"  |






