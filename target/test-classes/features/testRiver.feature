@River_APITests
Feature: River API Automation for Votes

  @getAllVotes
  Scenario: Verify if I can get all the Votes fron GET request
    Given I have all votes API
    When I am able to make a sucessfull request
    Then I should be able to get all the Votes
    
  @get_vote_byID
  Scenario: Verify if I can get the Vote by ID
    Given I have an ID to get the vote details
    When I make a get request for voteID api
    Then I should see the deails for the givenID
      
  @create&delete_vote
  Scenario: Verify if I am able to create new Vote and delete the created vote
    Given I have image_id: "asA2", sub_id: "my-user-1234", value: 1
    When I make a POST request to create newVote
    Then I should be able to get the meassage: "SUCCESS"
    And I verify new vote using GET api
    And I make a delete request for voteID api
    Then I verify vote is deleted    