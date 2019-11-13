package RestAssuredOne;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.ValidatableResponse;

import utils.PropertyReader;
import utils.Utils;

public class RestAssuredThreeXML {
	
	static PropertyReader prop;
	
	@BeforeTest
	public static void loadProperties() throws IOException {
		
		prop = PropertyReader.getPropertyFileReader("resources");		
	}
	
	@Test
	public static void getReq() {
		
		// BaseUrl Or Host
		RestAssured.baseURI = prop.getValueFor("GOOGLE_HOST");
		
		ValidatableResponse vres =	given()
										.param("location", "-33.8670522,151.1957362")
										.param("radius", 500)
										.param("key", prop.getValueFor("GOOGLE_KEY"))
										.when()
										.get(prop.getValueFor("GET_PLACE_XML"))
										.then()
										.assertThat().statusCode(200)
										.and().contentType(ContentType.XML);
		
		XmlPath xml = new XmlPath(vres.extract().asString()); // Utils.rawToXml(response);
		String name = xml.getString("PlaceSearchResponse.result[0].name");
		Assert.assertEquals(name, "Sydney");
		
		System.out.println("Get Request ::: "+vres.extract().response().prettyPrint());
		
	}
	
	@Test
	public static void postReq() {
		
		// BaseUrl Or Host
		RestAssured.baseURI = prop.getValueFor("UDEMY_HOST"); // For Post Request 
		
		ValidatableResponse vres =	given()
										.queryParam("key", prop.getValueFor("UDEMY_KEY"))
										.body(Utils.readFile("body.xml"))
										.when()
										.post(prop.getValueFor("POST_PLACE_XML"))
										.then()
										.assertThat().statusCode(200)
										.and().contentType(ContentType.XML)
										.and().statusLine("HTTP/1.1 200 OK");
		
//	with().parameters("firstName", "John", "lastName", "Doe").when().post("/greetXML").then().assertThat().body("greeting.firstName", equalTo("John"));
		
		XmlPath xml = new XmlPath(vres.extract().asString());
		String name = xml.getString("PlaceAddResponse.place_id");
		System.out.println("Name Is :-\n"+name);
		System.out.println("Post Request Response :-\n"+vres.extract().response().prettyPrint());
	}
}
