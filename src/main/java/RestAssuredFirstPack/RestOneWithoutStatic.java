package RestAssuredFirstPack;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


// key = AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y, radius = 500, location = -33.8670522, 151.1957362
public class RestOneWithoutStatic {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		RestAssured.basePath = "/maps/api/place/nearbysearch/json";
// 		Just Create RequestSpecification Object And Use All The Functions If You Are Not Using static Import. Finally, Get The Response.
		RequestSpecification spec = RestAssured.given();
		spec.body("");
//		spec.pathParam("", "");
//		spec.formParam("", "");
//		spec.queryParam("", ""); // Query Parameter, Form Parameter, Path Parameter
		spec.params("location", "-33.8670522,151.1957362", "radius", 500, "key", "AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y");
//		spec.cookies("someKey1", "someValue1", "someKey2", "someValue2");
		Response res = spec.get();
		System.out.println(res.prettyPrint());
		
		ValidatableResponse  vRes = res.then();
		vRes.assertThat();
		vRes.statusCode(200);
		String name = vRes.extract().path("results[0].name");
		Assert.assertEquals(name, "Sydney");
	}
}
