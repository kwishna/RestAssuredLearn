package RestAssuredOne;

import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class RestTwoUdemyOne {
	

	@Ignore
	@Test
	public static void getRequest() {
		
		// BaseUrl Or Host
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		ValidatableResponse vres =	given()
										.param("location", "-33.8670522,151.1957362")
										.param("radius", 500)
										.param("key", "AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y")
										.when()
										.get("/maps/api/place/nearbysearch/json")
										.then()
				.log().all(true)
										.assertThat().statusCode(200)
										.and().contentType(ContentType.JSON)
										.and().body("results[0].name", equalTo("Sydney"));
			
		System.out.println("Get Request ::: "+vres.extract().response().prettyPrint());
	}

	@Test
	public static void getRequestAnother() {

		// BaseUrl Or Host
		RestAssured.baseURI = "http://216.10.245.166";

		ValidatableResponse vres =	given()
				.param("location", "-33.8670522,151.1957362")
				.param("radius", 500)
				.param("key", "AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y")
				.when()
				.get("/maps/api/place/nearbysearch/json")
				.then()
				.log().all(true)
				.assertThat().statusCode(200)
				.and().contentType(ContentType.JSON)
				.and().body("results[0].name", equalTo("Sydney"));

		System.out.println("Get Request ::: "+vres.extract().response().prettyPrint());
	}
	
	@Ignore
	@Test
	public static void postRequest() {
		
		// BaseUrl Or Host
		RestAssured.baseURI = "http://216.10.245.166"; // For Post Request 
		
		ValidatableResponse vres =	given()
										.queryParam("key", "qaclick123")
										.body("{\r\n" + 
												"	 \"location\": {\r\n" + 
												"                    \"lat\": 27.173113,\r\n" + 
												"                    \"lng\": 78.040637\r\n" + 
												"                },\r\n" + 
												"      \"accuracy\" : 50,\r\n" + 
												"      \"name\"	 : \"Taj Mahal!\",\r\n" + 
												"      \"phone_number\" : \"(02) 9374 4000\",\r\n" + 
												"      \"address\"	 : \"Taj Museum, Agra, Uttar Pradesh, India\",\r\n" + 
												"      \"types\"	 : [\"Historical Place\"],\r\n" + 
												"      \"website\"	 : \"https://www.google.co.in\",\r\n" + 
												"      \"language\" : \"en-IN\"\r\n" + 
												"}")
										.when()
										.post("/maps/api/place/add/json")
										.then()
										.assertThat().statusCode(200)
										.and().contentType(ContentType.JSON)
										.and().statusLine("HTTP/1.1 200 OK");
			
		System.out.println("Post Request ::: "+vres.extract().response().prettyPrint());
	}
	
	@Ignore
	@Test
	public static void postAndDeleteRequest() {
		
		// BaseUrl Or Host
		RestAssured.baseURI = "http://216.10.245.166"; // For Post Request 
						
					// POST Request To Create
					String place_id =	given()
										.queryParam("key", "qaclick123")
										.body(new File(System.getProperty("user.dir")+"/body.json")) // Using File
										.when()
										.post("/maps/api/place/add/json")
										.then()
										.assertThat().statusCode(200)
										.and().contentType(ContentType.JSON)
										.and().statusLine("HTTP/1.1 200 OK")
										.extract()
										.path("place_id");  // Or, .extract().response(); returns Response res; Now, res.asString();
													  // Then, JsonPath jp = new JsonPath(res.asString()); String pId = jp.get("place_id");						
										
					System.out.println("Post Response Place Id Is :: "+place_id);
		
					// Delete The Data Using Value Acquired From The Above Request
										given()
										.accept(ContentType.JSON)
										.queryParam("key", "qaclick123")
										.body("{\r\n" + 
												"	\"place_id\" : \""+place_id+"\"\r\n" + 
												"}")
										.when()
										.post("/maps/api/place/delete/json")
										.then()
										.assertThat().statusCode(200)
										.and().contentType(ContentType.JSON)
										.and().statusLine("HTTP/1.1 200 OK")
										.and().body("status", equalTo("OK"));
	}
}