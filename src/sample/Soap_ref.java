package sample;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

public class Soap_ref {

	public static void main(String[] args) {
		// Declare the base URL
		RestAssured.baseURI = "https://www.dataaccess.com";

		// Declare request body
		String requestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>100</ubiNum>\r\n" + "    </NumberToWords>\r\n" + "  </soap:Body>\r\n"
				+ "</soap:Envelope>\r\n" + "\r\n" + "";
		// Extract responsebody
		String responseBody = given().header("content-type", "text/xml; charset=utf-8").body(requestBody).when()
				.post("webservicesserver/NumberConversion.wso").then().extract().response().asString();
		System.out.println(responseBody);

		// parse the responsebody
		XmlPath xmlResponse = new XmlPath(responseBody);
		String responseParameter = xmlResponse.getString("NumberToWordsResult");
		System.out.println(responseParameter);

		// validate the responsebody
		Assert.assertEquals(responseParameter, "one hundred ");
	}
}
