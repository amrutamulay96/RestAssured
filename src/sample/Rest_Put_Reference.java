package sample;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;
import java.time.LocalDateTime;
import org.testng.Assert;

public class Rest_Put_Reference {

	public static void main(String[] args) {
		// Declare the base URL
		RestAssured.baseURI = "https://reqres.in/";

		// Declare the request body string variable
		String requestBody = "{\r\n" + "    \"name\": \"morpheus\",\r\n" + "    \"job\": \"zion resident\"\r\n" + "}";

		// Declare the expected results
		JsonPath JspRequest = new JsonPath(requestBody);
		String req_name = JspRequest.getString("name");
		String req_job = JspRequest.getString("job");
		LocalDateTime currentDate = LocalDateTime.now();
		String expectedDate = currentDate.toString().substring(0, 10);

		// declare given,when,then method
		String responseBody = given().header("Content-Type", "application/json").body(requestBody).put("api/users/2")
				.then().extract().response().asString();

		// create object of json path to parse the response body
		JsonPath Jspresponse = new JsonPath(responseBody);

		String res_name = Jspresponse.getString("name");
		String res_job = Jspresponse.getString("job");

		System.out.println(res_name);
		System.out.println(res_job);

		String res_updatedAt = Jspresponse.getString("updatedAt");
		res_updatedAt = res_updatedAt.substring(0, 10);
		System.out.println(res_updatedAt);

		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertEquals(res_updatedAt, expectedDate);
	}
}
