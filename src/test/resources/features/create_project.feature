Feature: Create Project

  Scenario: User creates a new project via API
    Given a new project named "Test Project Hilmi"
    When I create the project
    Then the project should be created
