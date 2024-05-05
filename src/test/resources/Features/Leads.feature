Feature: Lead functionality




@test3
Scenario: Create Lead with mandatory fields TC01
Given user should be on login page
When user enters valid credentials
When user clicks on New lead link
And fill all mandatory fields and clicks on save
Then lead should be created successfully

@test3
Scenario: Create Lead with mandatory fields TC02
Given user should be on login page
When user enters valid credentials
When user clicks on New lead link
And fill all mandatory fields and clicks on save
Then lead should be created successfully



@test4
Scenario: Create Lead with mandatory fields with step Parameterization
Given user should be on login page
When user enters valid credentials
When user clicks creates the multiple leads with "<lastname>" and "<company>" and verified
|lastname|company|
|Mike|MGT|
|Nick|TCS|
|Harry|Wipro|
And user should click on logout button
