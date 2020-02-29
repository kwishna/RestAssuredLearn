package OAuth;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.PropertyReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * https://developer.okta.com/blog/2017/06/21/what-the-heck-is-oauth
 */
public class OAuthOne {

	ThreadLocal<Map<String, String>> local = InheritableThreadLocal.withInitial(HashMap::new);

	static PropertyReader prop;

	@BeforeTest
	public static void loadProperties() throws IOException {

		prop = PropertyReader.getPropertyFileReader("resources");
	}

	@Test
	public static void getAccessToken() throws InterruptedException {

	/*	// Google Login Not Possible Through Automation Framework. #Google@2020 Rule.
		System.setProperty("webdriver.chrome.driver", "E:/lib/chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		//driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
		driver.findElement(By.cssSelector("[type='email']")).sendKeys("ssfs");
		driver.findElement(By.cssSelector("[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("[type='password']")).sendKeys("sda");
		driver.findElement(By.cssSelector("[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		String url=driver.getCurrentUrl();
		System.out.println(url);
		//https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FwwG-EJP4BAbFQt34VLJK6HnHiPLX4H0wRebF-iWGOTlM3tZ6Xd0H9i32wszWeAsYHd82s3JoLFxghgkohN-yNSk&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent#
		String partialcode=url.split("code=")[1];
		String code=partialcode.split("&scope")[0];
		System.out.println(code);*/

	// below code can only be grabbed through url while logging into google after entering credential.
		String code1 = "4%2FwwG-EJP4BAbFQt34VLJK6HnHiPLX4H0wRebF-iWGOTlM3tZ6Xd0H9i32wszWeAsYHd82s3JoLFxghgkohN-yNSk";

		final String ACCESS_TOKEN_URL = "https://www.googleapis.com/oauth2/v4/token";
		final String CLIENT_ID = "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com";
		final String CLIENT_SECRET = "erZOWM9g3UtwNRj340YYaK_W";
		final String REDIRECT_URL = "https://rahulshettyacademy.com/getCourse.php";
		final String GRANT_TYPE = "authorization_code";

		ValidatableResponse response =	given()
				.urlEncodingEnabled(false)
				.queryParam("code", code1)
				.queryParam("client_id", CLIENT_ID)
				.queryParam("client_secret", CLIENT_SECRET)
				.queryParam("redirect_uri", REDIRECT_URL)
				.queryParams("grant_type", GRANT_TYPE)
				.queryParams("state", "verifyfjdss")
				.post(ACCESS_TOKEN_URL)
				.then()
				.log().all(true)
				.assertThat().statusCode(200)
				.and().contentType(ContentType.JSON);

		System.out.println("Get Request ::: "+response.extract().response().prettyPrint());

	}

	@Test(priority = 1)
	public static void getCourses() {

		// BaseUrl Or Host
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		ValidatableResponse response =	given()
				.when()
				.queryParam("access_token", "")
				.get("/getCourse.php")
				.then()
				.log().all(true)
				.assertThat().statusCode(200)
				.and().contentType(ContentType.JSON);

		System.out.println("Get Request ::: "+response.extract().response().prettyPrint());

	}
}
