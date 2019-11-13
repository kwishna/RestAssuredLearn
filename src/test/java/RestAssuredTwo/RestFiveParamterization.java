package RestAssuredTwo;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.PropertyReader;

public class RestFiveParamterization {

	static PropertyReader prop;

	@BeforeTest
	public static void loadProperties() throws IOException {

		prop = PropertyReader.getPropertyFileReader("resources");
	}
	
	@Test(dataProvider="dp")
	public void addBookPost(String isbn, String isle) {
		
		RestAssured.baseURI = prop.getValueFor("UDEMY_HOST");
	
		Response res =	given()
			.header("Content-Type", "application/json")
			.body(BooksHandler.getABook(isbn, isle))
			.when()
			.post("Library/Addbook.php")
			.then()
			.log().body(true) // Remember
			.assertThat().statusCode(200)
			.and()
			.extract()
			.response();
		
		JsonPath jp = res.jsonPath();
		System.out.println("ID From Response :: "+jp.get("ID"));

	}
	
	@DataProvider
	public Object[][] dp() {
		
//		return new Object[][] {{"",""}, {"",""}, {"",""}};
		
		Object[][] obj = {{"isbn1","aisle1"}, {"isbn2","isle2"}, {"isbn3","isle3"}};
		return obj;
	}

}

class BooksHandler{
	
	public static String getABook(String isbn, String aisle) {
		
		return "{ \"name\":\"Learn Appium Automation with Java\", \"isbn\":\""+isbn+"\", \"aisle\":\""+aisle+"\", \"author\":\"John foe\" }";
	}
}
