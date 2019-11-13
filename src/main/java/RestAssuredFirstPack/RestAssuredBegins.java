package RestAssuredFirstPack;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestAssuredBegins {
	
	@Ignore
	@Test
	public void prettyResponse() {	
		
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		
		Response res = given()
						.when()
						.get("http://api.zippopotam.us/IN/110001");
		
	// Or Simply, Response res = get("http://api.zippopotam.us/IN/110001");
		
		System.out.println(res.prettyPrint());
		
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	}
	
	@Ignore
	@Test
	public void headers() {	
		
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		
		ValidatableResponse res = given()
										.when()
										.get("http://api.zippopotam.us/IN/110001")
										.then();
		
//		Response reponse = res.extract().response(); // Converting Validatable Response To Response Object
//		String prettyJson = reponse.prettyPrint();	// Printing Pretty JSON
//		Object result = reponse.as(Object.class); // Converting Response To Object
		
//		String resul = res.extract().path("user_id"); // Extracting Value From JSON
//		String userId = response.path("user_id");
//		extract().jsonPath().getLong("user_id");
//		
//		JsonPath jsonPathEvaluator = response.jsonPath();
//		return jsonPathEvaluator.get("user_id").toString();
		
		System.out.println(res.extract().headers());
		
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	}
	
	@Ignore
	@Test
	public void validatingStatusCodeAndValue() {	
		
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		
		ValidatableResponse res = given()
									.when()
									.get("http://api.zippopotam.us/IN/110001")
									.then()
									.assertThat()
									.statusCode(200)
									.and()
									.body("places[0].'place name'", equalTo("Janpath"))
									.and()
									.body("places.'place name'", hasItem("Janpath"))
									.and()
									.body("places.'place name'", not(hasItem("Agra")));
		
		System.out.println(res.extract().statusLine());
		
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	}
	
	@Ignore
	@Test
	public void validatingContentType() {	
		
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		
								given()
									.when()
									.get("http://api.zippopotam.us/IN/110001")
									.then()
									.assertThat()
									.contentType(ContentType.JSON)
									.and()
									.contentType("application/json");
								
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	}
	
	@Ignore
	@Test
	public void logging() {	
		
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		
								given()
									.when()
									.log().all()
									.get("http://api.zippopotam.us/IN/110001")
									.then()
									.log().body();
		
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");	
	}
	
	@Test(enabled=false)
	public void logging2() {	
		
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		
								given()
									.when()
									.log().all()
									.get("http://api.zippopotam.us/IN/110001")
									.then()
									.log().body();
		
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");	
	}
	
	/**
	 * Parameterization :
	 * 	  2 Types :-
	 * a) Query Parameters : something.com/?text=apple, something.com/?text=mango
	 * b) Path Parameters : something.com/qa/test, something.com/dev/deploy
	 */
	
	@Test(enabled=false)
	public void parameterization() {	// Pass Parameters With DataProviders
		
		String country = "IN";
		String pinCode = "110001";
		
								given()
									.pathParam("country", country).pathParam("zip", pinCode)
									.when()
									.log().all()
									.get("http://api.zippopotam.us/{country}/{zip}")
									.then()
									.log().body();
		
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");	
	}
	
	@Ignore
	@Test
	public void requestSpecification() {
		
		RequestSpecification reqBuilder = new RequestSpecBuilder() // For Re-usability Again & Again In Every Request
													.setBaseUri("http://api.zippopotam.us")
													.setContentType(ContentType.JSON)
													.build();
		
								given()
									.spec(reqBuilder)
									.when()
									.get("/IN/110001")
									.then()
									.assertThat()
									.statusCode(200);
		
		
	}
	
	@Ignore
	@Test
	public void responseSpecification() {

		ResponseSpecification resp = new ResponseSpecBuilder()	// For Re-usability Again & Again In Every Request. Put In @Before Methods
											.expectContentType(ContentType.JSON)
											.expectStatusCode(200)
											.log(LogDetail.COOKIES)
											.build();
		
								given()
									.when()
									.get("http://api.zippopotam.us/IN/110001")
									.then()
									.spec(resp)
									.and()
									.assertThat()
									.body("places.'place name'", not(hasItem("Desh")));
	
	}
	
	@Test
	public void responseReuse() {

		ResponseSpecification resp = new ResponseSpecBuilder()	// For Re-usability Again & Again In Every Request. Put In @Before Methods
											.expectContentType(ContentType.JSON)
											.expectStatusCode(200)
											.log(LogDetail.COOKIES)
											.build();
		
			List<String> places =	given() // We Can Use List In Another Tests
									.when()
									.get("http://api.zippopotam.us/IN/110001")
									.then()
									.spec(resp)
									.and()
									.extract()
									.path("places.'place name'");
			
			assertEquals(places.size(), 19);
			
			System.out.println(places);
	}
	
	@Test
	public void deSerialization() {
	
//			Location loc =	given()
//									.when()
//									.get("http://api.zippopotam.us/IN/110001")
//									.as(Location.class)

	}
	
	@Test
	public void serialization() {
	
//		Location loc = new Location();
//		
//							given()
//							.contentType(ContentType.JSON)
//							.body(loc)
//							.log().body()
//							.when()	
//							.post("http://api.zippopotam.us/IN/110001")
//							.then()
//							.assertThat()
//							.statusCode(201);

	}	
}
