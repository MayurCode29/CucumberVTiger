Feature: Login functionality

Background:
Given user should be on login page
@test1
Scenario: Valid Login
When user enters valid credentials
Then user should be navigated to homepage
And user can see logout link


@test2
Scenario Outline: invalid Login
When user enters userid as "<userid>" and password as "<password>" invalid credentials
Then user should be navigated to Login page
And user can see error message
Examples:
|userid | password |
|admin1 |admin1 |
|admin2 |admin2 |
|admin3 |admin3 |

