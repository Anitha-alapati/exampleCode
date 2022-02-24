package stepDefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import riverUtils.testUtils;

public class testSteps {

	Response response;
	String ids[],newid;
	JsonPath js;
	

	@Given("I have all votes API")
	public void i_have_all_votes_api() {

		response = testUtils.getAllVotes();

	}

	@When("I am able to make a sucessfull request")
	public void i_am_able_to_make_a_sucessfull_request() {
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Then("I should be able to get all the Votes")
	public void i_should_be_able_to_get_all_the_votes() {
		js = response.jsonPath();
		String ids = js.getString("id");
		assertThat(ids.length() > 0);
	}

	@Given("I have an ID to get the vote details")
	public void i_have_an_id_to_get_the_vote_details() {
		response = testUtils.getAllVotes();
		js = response.jsonPath();
		ids = js.getString("id").split(",");

	}

	@When("I make a get request for voteID api")
	public void i_make_a_get_request_for_vote_id_api() {

		response = testUtils.getVotesID(ids[1]);
		response.prettyPrint();
		js = response.jsonPath();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Then("I should see the deails for the givenID")
	public void i_should_see_the_deails_for_the_given_id() {
		Assert.assertEquals(js.getString("id").replace(" ", ""), ids[1].replace(" ", ""));
	}

	@Given("I have image_id: {string}, sub_id: {string}, value: {int}")
	public void i_have_image_id_sub_id_value(String image_id, String sub_id, int value) {
		response = testUtils.postNewVote(image_id, sub_id, value);
		response.prettyPrint();
		js = response.jsonPath();

	}

	@When("I make a POST request to create newVote")
	public void i_make_a_post_request_to_create_new_vote() {
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Then("I should be able to get the meassage: {string}")
	public void i_should_be_able_to_get_the_meassage(String message) {
		Assert.assertEquals(js.getString("message"), "SUCCESS");
		ids = js.getString("id").split(",");

		String rid = ids[0].replace("[", "");
		
		// Making get request
		System.out.println((rid.replace("]", "")));
		response = testUtils.getVotesID(rid.replace("]", ""));
		response.prettyPrint();
		js = response.jsonPath();
		newid = js.getString("id");
		Assert.assertEquals(js.getString("id"), (rid.replace("]", "")));

	}
	
	@And("I verify new vote using GET api")
	public void i_verify_new_vote_using_get_api() {
		Assert.assertEquals(response.getStatusCode(), 200);
	
	}


	@And("I make a delete request for voteID api")
	public void i_make_a_delete_request_for_vote_id_api() {
		response = testUtils.deleteVotesID(newid);
		js = response.jsonPath();
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Then("I verify vote is deleted")
	public void i_verify_vote_is_deleted() {
		Assert.assertEquals(js.getString("message"), "SUCCESS");

		// Get request to verify the the vote is deleted 
		response = testUtils.getVotesID(newid);
		response.prettyPrint();
		js = response.jsonPath();
		Assert.assertEquals(response.getStatusCode(), 404);
		Assert.assertEquals(js.getString("message"), "NOT_FOUND");
	}

}
