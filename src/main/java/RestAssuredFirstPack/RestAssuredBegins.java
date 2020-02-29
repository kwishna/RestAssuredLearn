package RestAssuredFirstPack;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.parsing.Parser;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Reporter;
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
import pojo.AnotherLocation;
import pojo.AnyLocation;
import pojo.Location;

public class RestAssuredBegins {

	/**
	 * Main method for logging data
	 */
	public Response filter(FilterableRequestSpecification requestSpec,
						   FilterableResponseSpecification responseSpec, FilterContext ctx) {
		// Invoke the request by delegating to the next filter in the filter chain.
		final Response response = ctx.next(requestSpec, responseSpec);

		if (responseSpec.getStatusCode() != null) {
			if (responseSpec.getStatusCode().matches(response.statusCode())) {
				Reporter.log("Status code matched what was expected");
			} else {
				Reporter.log("REQUEST: " + System.lineSeparator() + "Request method: "
						+ requestSpec.getMethod() + System.lineSeparator() + "Request Path: "
						+ requestSpec.getURI() + System.lineSeparator() + "Request Params: "
						+ requestSpec.getRequestParams() + System.lineSeparator()
						+ "Query Params: " + requestSpec.getQueryParams()
						+ System.lineSeparator() + "Form Params: "
						+ requestSpec.getFormParams() + System.lineSeparator()
						+ "Path Params: " + requestSpec.getPathParams()
						+ System.lineSeparator() + "Headers: " + requestSpec.getHeaders()
						+ System.lineSeparator() + "Body: " + System.lineSeparator()
						+ requestSpec.getBody()+ System.lineSeparator()
						+ System.lineSeparator() + System.lineSeparator() + "RESPONSE: "
						+ System.lineSeparator() + " " + response.getStatusLine() + System.lineSeparator()
						+ response.getHeaders() + System.lineSeparator()
						+ response.getBody() + System.lineSeparator()
						+ "SPLUNK SEARCH: " + System.lineSeparator()
						+ System.lineSeparator() + System.lineSeparator()
						+ System.lineSeparator());
			}
		} else {
			 Reporter.log("Status code was null");
		}
		return response;
	}
	
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
				.addFilter(RequestLoggingFilter.logRequestTo(System.out))
				.addFilter(ResponseLoggingFilter.logResponseTo(System.out))
				.addFilter(ErrorLoggingFilter.logErrorsTo(System.out))
				.addFilter(new Filter() {
					@Override
					public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
						MatcherAssert.assertThat(requestSpec.getHeaders().getList("Host").size(), CoreMatchers.equalTo(1));
						MatcherAssert.assertThat(requestSpec.getHeaders().get("Host").getValue(), CoreMatchers.equalTo("host url"));
						return ctx.next(requestSpec, responseSpec);
					}})
				/**
				 * Filter the incoming request and response specifications and outgoing response.
				 * You need to call FilterContext.next(FilterableRequestSpecification, FilterableResponseSpecification)
				 * when you're done otherwise the request will not be delivered.
				 * It's of course possible to abort the filter chain execution by returning a Response directly.
				 */
				//CookieFilter, ErrorLoggingFilter, FormAuthFilter, RequestLoggingFilter, ResponseLoggingFilter, SendRequestFilter, SessionFilter, TimingFilter
							.log(LogDetail.ALL)
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
									.log().all(true)
									.and()
									.extract()
									.path("places.'place name'");
			
			assertEquals(places.size(), 19);
			
			System.out.println(places);
	}

	// Use : http://www.jsonschema2pojo.org/
	@Test
	public void deSerialization() {
	
			Location loc =	given()
					.expect()
					.parser("application/json; charset=utf-8", Parser.JSON)
					.when()
					.get("http://api.zippopotam.us/IN/110001")
					.as(Location.class);

		System.out.println(loc);
	}
	
	@Test
	public void serialization() {

		Location loc = new Location();
							given()
							.contentType(ContentType.JSON)
							.body(loc)
							.log().body()
							.when()
							.post("http://api.zippopotam.us/IN/110001")
							.then()
							.log().all(true)
							.assertThat()
							.statusCode(201);

	}

	@Test
	public static void serialize() {

		AnotherLocation anotherLocation = new AnotherLocation();
		anotherLocation.setLat(50.0);
		anotherLocation.setLng(150.0);

		AnyLocation anyLocation = new AnyLocation();
		anyLocation.setAccuracy(5);
		anyLocation.setAddress("Address Serialize");
		anyLocation.setLanguage("Hi");
		anyLocation.setLocation(anotherLocation);
		anyLocation.setName("Taj");
		anyLocation.setPhoneNumber("9771451456");
		anyLocation.setTypes("Tourism, Old");
		anyLocation.setWebsite("https://gjghgj.com");

		// BaseUrl Or Host
		RestAssured.baseURI = "https://rahulshettyacademy.com/"; // For Post Request

		ValidatableResponse vres =	given()
				.queryParam("key", "qaclick123")
				.body(anyLocation)
				.when()
				.post("/maps/api/place/add/json")
				.then()
				.log().all(true)
				.assertThat().statusCode(200)
				.and().contentType(ContentType.JSON)
				.and().statusLine("HTTP/1.1 200 OK");

		System.out.println("Post Request ::: "+vres.extract().response().prettyPrint());
	}


	@Test
	public void getReqAndDeSerialize() {

		baseURI = "https://rahulshettyacademy.com/";

		ValidatableResponse response =	given()
				.param("place_id", "18c6c82b4942bcec93964b8304956bbe")
				.param("key", "qaclick123")
				.when()
				.log().everything()
				.get("/maps/api/place/get/json")
				.then()
				.log().all(true);

		AnyLocation loc = response.extract().response().as(AnyLocation.class);
		System.out.println(loc);
	}

}
