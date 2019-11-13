package RestAssuredOne;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import utils.PropertyReader;
import utils.Utils;

public class RestFourParsingJson {
	
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
										.log().all()
										.when()
										.get(prop.getValueFor("GET_PLACE_JSON"))
										.then()
										.assertThat().statusCode(200)
										.and().contentType(ContentType.JSON)
										.and().body("results[0].name", equalTo("Sydney"))
										.log().ifError()
										.log().ifValidationFails();
		
//		System.out.println("Get Request ::: "+vres.extract().response().prettyPrint());	
		
		JsonPath json = Utils.rawToJson(vres.extract().response().asString());
		int size = json.get("results.size");
		
		for(int i=0; i<size; i++){
			
			System.out.println(json.getString("results["+i+"].name"));
		}
		
//		((List<Map>)json.get("results")).forEach(obj->System.out.println(((Map<String, String>)obj).get("name")));
	}
}
