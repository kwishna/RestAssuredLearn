package RestAssuredTwitter;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.PropertyReader;
import utils.Utils;

public class TwitterOne {
	
	PropertyReader reader;
	String id;
	Logger logger;
	
	@BeforeTest
	public void setUp() throws IOException {
		
		logger = LogManager.getLogger("RestAssuredTwitter");
		reader = PropertyReader.getPropertyFileReader("resources");
	}
	
	@Test(priority=0)
	public void getTweets() {
		
		RestAssured.baseURI="https://api.twitter.com";
		Response res = given()
				.param("count", 5) // Max No Of Tweets
				.accept(ContentType.JSON)
				.auth().oauth(reader.getValueFor("TWITTER_API_KEY"), reader.getValueFor("TWITTER_SECRET_KEY"),
						reader.getValueFor("TWITTER_ACCESS_TOKEN"), reader.getValueFor("TWITTER_ACCESS_SECRET"))
				.when()
				.get("/1.1/statuses/home_timeline.json");
	
		res.then().assertThat().statusCode(200);
		
	Utils.saveToFile(res.prettyPrint(), "TwitterGetOne.json");
	logger.trace(":: COM  :: LEVEL :: TRACE trace ::-");
	logger.info(":: COM  :: LEVEL :: TRACE info ::-");
	logger.debug(":: COM  :: LEVEL :: TRACE debug ::-");
	logger.warn(":: COM  :: LEVEL :: TRACE warn ::-");
	logger.error(":: COM  :: LEVEL :: TRACE error ::-");
	logger.fatal(":: COM  :: LEVEL :: TRACE fatal ::-");
	}
	
	@Test(priority=1)
	public void getSomeOnesTweet() {
		
		RestAssured.baseURI="https://api.twitter.com";
		
		Response res = given()
				.param("screen_name", "narendramodi") // From Account
				.param("count", 5) // No Of Tweets
				.accept(ContentType.JSON)
				.auth().oauth(reader.getValueFor("TWITTER_API_KEY"), reader.getValueFor("TWITTER_SECRET_KEY"),
						reader.getValueFor("TWITTER_ACCESS_TOKEN"), reader.getValueFor("TWITTER_ACCESS_SECRET"))
				.when()
				.get("/1.1/statuses/user_timeline.json");
	
		res.then().assertThat().statusCode(200);
		
	Utils.saveToFile(res.prettyPrint(), "TwitterGetTwo.json");
	logger.trace(":: COM  :: LEVEL :: TRACE trace ::-");
	logger.info(":: COM  :: LEVEL :: TRACE info ::-");
	logger.debug(":: COM  :: LEVEL :: TRACE debug ::-");
	logger.warn(":: COM  :: LEVEL :: TRACE warn ::-");
	logger.error(":: COM  :: LEVEL :: TRACE error ::-");
	logger.fatal(":: COM  :: LEVEL :: TRACE fatal ::-");
	}
	
	@Test(priority=2)
	public void postTweet() {
		
		RestAssured.baseURI="https://api.twitter.com";
		
		Response res = given()
				.param("status", "Hi! This Tweet Is From JAVA API. #BharatBoleModiModi @KrsnAsngH #RestAssured ") 
				.accept(ContentType.JSON)
				.auth().oauth(reader.getValueFor("TWITTER_API_KEY"), reader.getValueFor("TWITTER_SECRET_KEY"),
						reader.getValueFor("TWITTER_ACCESS_TOKEN"), reader.getValueFor("TWITTER_ACCESS_SECRET"))
				.when()
				.post("/1.1/statuses/update.json");
	
	id = res.jsonPath().get("id").toString();
		
	Utils.saveToFile(res.prettyPrint(), "TwitterPost.json");
	
	res.then().assertThat().statusCode(200);
	
	logger.trace(":: COM  :: LEVEL :: TRACE trace ::-");
	logger.info(":: COM  :: LEVEL :: TRACE info ::-");
	logger.debug(":: COM  :: LEVEL :: TRACE debug ::-");
	logger.warn(":: COM  :: LEVEL :: TRACE warn ::-");
	logger.error(":: COM  :: LEVEL :: TRACE error ::-");
	logger.fatal(":: COM  :: LEVEL :: TRACE fatal ::-");
	}
	
	@Test(priority=99)
	public void deleteTweet() {
		
		RestAssured.baseURI="https://api.twitter.com";
		
		Response res = given()
				.pathParam("id", id)
				.accept(ContentType.JSON)
				.auth().oauth(reader.getValueFor("TWITTER_API_KEY"), reader.getValueFor("TWITTER_SECRET_KEY"),
						reader.getValueFor("TWITTER_ACCESS_TOKEN"), reader.getValueFor("TWITTER_ACCESS_SECRET"))
				.when()
				.post("1.1/statuses/destroy/{id}.json");
	
		res.then().assertThat().statusCode(200);
		
	Utils.saveToFile(res.prettyPrint(), "TwitterDelete.json");
	logger.trace(":: COM  :: LEVEL :: TRACE trace ::-");
	logger.info(":: COM  :: LEVEL :: TRACE info ::-");
	logger.debug(":: COM  :: LEVEL :: TRACE debug ::-");
	logger.warn(":: COM  :: LEVEL :: TRACE warn ::-");
	logger.error(":: COM  :: LEVEL :: TRACE error ::-");
	logger.fatal(":: COM  :: LEVEL :: TRACE fatal ::-");
	}
	
	@Test(priority=4)
	public void replyTweet() {
		
		RestAssured.baseURI="https://api.twitter.com";
		
		Response res = given()
				.param("in_reply_to_status_id", id)
				.param("status", "Now! I Replied My Tweet Using Java. ")
				.accept(ContentType.JSON)
				.auth().oauth(reader.getValueFor("TWITTER_API_KEY"), reader.getValueFor("TWITTER_SECRET_KEY"),
						reader.getValueFor("TWITTER_ACCESS_TOKEN"), reader.getValueFor("TWITTER_ACCESS_SECRET"))
				.when()
				.post("1.1/statuses/update.json");
	
		res.then().assertThat().statusCode(200);
		
	Utils.saveToFile(res.prettyPrint(), "TwitterReply.json");
	logger.trace(":: COM  :: LEVEL :: TRACE trace ::-");
	logger.info(":: COM  :: LEVEL :: TRACE info ::-");
	logger.debug(":: COM  :: LEVEL :: TRACE debug ::-");
	logger.warn(":: COM  :: LEVEL :: TRACE warn ::-");
	logger.error(":: COM  :: LEVEL :: TRACE error ::-");
	logger.fatal(":: COM  :: LEVEL :: TRACE fatal ::-");
	}
	
	@Test(priority=5)
	public void reTweet() {
		
		RestAssured.baseURI="https://api.twitter.com";
		
		Response res = given()
				.pathParam("id", id)
				.accept(ContentType.JSON)
				.auth().oauth(reader.getValueFor("TWITTER_API_KEY"), reader.getValueFor("TWITTER_SECRET_KEY"),
						reader.getValueFor("TWITTER_ACCESS_TOKEN"), reader.getValueFor("TWITTER_ACCESS_SECRET"))
				.when()
				.post("1.1/statuses/retweet/{id}.json");
	
		res.then().assertThat().statusCode(200);
		
	Utils.saveToFile(res.prettyPrint(), "reTweet.json");
	logger.trace(":: COM  :: LEVEL :: TRACE trace ::-");
	logger.info(":: COM  :: LEVEL :: TRACE info ::-");
	logger.debug(":: COM  :: LEVEL :: TRACE debug ::-");
	logger.warn(":: COM  :: LEVEL :: TRACE warn ::-");
	logger.error(":: COM  :: LEVEL :: TRACE error ::-");
	logger.fatal(":: COM  :: LEVEL :: TRACE fatal ::-");
	}
	
	@Test(priority=6)
	public void unreTweet() {
		
		RestAssured.baseURI="https://api.twitter.com";
		
		Response res = given()
				.pathParam("id", id)
				.accept(ContentType.JSON)
				.auth().oauth(reader.getValueFor("TWITTER_API_KEY"), reader.getValueFor("TWITTER_SECRET_KEY"),
						reader.getValueFor("TWITTER_ACCESS_TOKEN"), reader.getValueFor("TWITTER_ACCESS_SECRET"))
				.when()
				.post("1.1/statuses/unretweet/{id}.json");
	
		res.then().assertThat().statusCode(200);
		
	Utils.saveToFile(res.prettyPrint(), "unreTweet.json");
	logger.trace(":: COM  :: LEVEL :: TRACE trace ::-");
	logger.info(":: COM  :: LEVEL :: TRACE info ::-");
	logger.debug(":: COM  :: LEVEL :: TRACE debug ::-");
	logger.warn(":: COM  :: LEVEL :: TRACE warn ::-");
	logger.error(":: COM  :: LEVEL :: TRACE error ::-");
	logger.fatal(":: COM  :: LEVEL :: TRACE fatal ::-");
	}
}
