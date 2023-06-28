package Common_API_Methods;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class Common_Methods_Get {
	public static int ResponseStatusCode(String BaseURI, String Resource) {

		RestAssured.baseURI = BaseURI;
		int statusCode = given().header("Content-Type", "application/json").when().get(Resource)
				.then().extract().statusCode();

		return statusCode;
	}

	public static String ResponseBody(String BaseURI, String Resource) {
		RestAssured.baseURI = BaseURI;
		String responseBody = given().header("Content-Type", "application/json").when().get(Resource)
				.then().extract().response().asPrettyString();

		return responseBody;
	}

}
