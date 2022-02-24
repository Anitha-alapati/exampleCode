package riverUtils;

import io.cucumber.messages.internal.com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import riverDTO.POSTNewVoteRequestDTO;

public class testUtils {

	public static String voteID = "";

	public static String BASE_URL = "https://api.thecatapi.com/V1";

	public static Response getAllVotes() {
		Response response = RestAssured.given().log().all().header("x-api-key", "DEMO-API-KEY")
				.get(BASE_URL + "/Votes");
		return response;

	}

	public static Response getVotesID(String id) {
		Response response = RestAssured.given().log().all().header("x-api-key", "DEMO-API-KEY")
				.get(BASE_URL + "/votes/" + id);

//		voteID = response.asString();
		return response;
	}

	public static Response postNewVote(String image_id, String sub_id, int value) {
		POSTNewVoteRequestDTO postNewVoteRequestDTO = new POSTNewVoteRequestDTO();

		postNewVoteRequestDTO.setImage_id(image_id);
		postNewVoteRequestDTO.setISub_id(sub_id);
		postNewVoteRequestDTO.setvalue(value);

		Response response = RestAssured.given().log().all().headers("Content-Type", "application/json")
				.header("x-api-key", "DEMO-API-KEY").body(new Gson().toJson(postNewVoteRequestDTO))
				.post(BASE_URL + "/votes");
//		 newVoteStatus = response.asString();
		return response;
	}

	public static Response deleteVotesID(String id) {
		Response response = RestAssured.given().log().all().header("x-api-key", "DEMO-API-KEY")
				.delete(BASE_URL + "/votes/" + id);

		voteID = response.asString();
		return response;
	}

}
