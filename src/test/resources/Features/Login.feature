Feature: Login functionality


Scenario: Verify_Title_TC01
Given user should be on login page
And user can verify the title

Scenario: Verify_Logo_TC02
Given user should be on login page
And user can verify the logo

Scenario: Verify_KeyModule_text_TC03
Given user should be on login page
And user can verify keymodule text


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

