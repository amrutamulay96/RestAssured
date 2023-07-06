package sample;

import io.restassured.RestAssured;

import io.restassured.path.xml.XmlPath;
import static io.restassured.RestAssured.given;
import org.testng.Assert;

public class Soap {

	public static void main(String[] args) {

		// Declare the base URL
		RestAssured.baseURI = "https://www.dataaccess.com";

		// Declare request body
		String requestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap12:Envelope xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\r\n"
				+ "  <soap12:Body>\r\n"
				+ "    <NumberToDollars xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <dNum>25</dNum>\r\n" + "    </NumberToDollars>\r\n" + "  </soap12:Body>\r\n"
				+ "</soap12:Envelope>\r\n" + "";

		// Extract response body

		String responseBody = given().header("Content-Type", "text/xml; charset=utf-8").body(requestBody).when()
				.post("webservicesserver/NumberConversion.wso").then().extract().asString();
		System.out.println(responseBody);

		// parse the response body
		XmlPath XmlResponse = new XmlPath(responseBody);
		String responseParameter = XmlResponse.getString("NumberToDollarsResult");
		System.out.println(responseParameter);

		// validate response body
		Assert.assertEquals(responseParameter, "twenty five dollars");
	}
}
