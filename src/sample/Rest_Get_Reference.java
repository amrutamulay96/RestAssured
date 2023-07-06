package sample;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class Rest_Get_Reference {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://reqres.in/";

		// declare response body

		String responseBody = given().header("Content-Type", "application/json").body("requestBody").when()
				.get("api/users?page=2").then().extract().response().asString();
		System.out.println(responseBody);

	}

}
