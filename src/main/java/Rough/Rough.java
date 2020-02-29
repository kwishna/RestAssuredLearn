package Rough;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import io.restassured.path.json.JsonPath;
import org.apache.http.client.methods.HttpGet;
//import com.jayway.jsonpath.JsonPath;

public class Rough {

	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
		
		HttpClient http = HttpClient.newBuilder()
				.build();
		
		HttpRequest req = HttpRequest.newBuilder()
				.uri(new URI("https://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22"))
				.GET()
				.build();

		HttpGet get = new HttpGet();
		get.setURI(new URI("https://www.google.co.in"));
				
		HttpResponse<String> res = http.send(req, BodyHandlers.ofString());
//		System.out.println(res.body());
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(new JsonParser().parse(res.body())));
		
//		System.out.println("JSONPATH QUERY ::: "+ JsonPath.read(res.body(), "$['weather']")); // jayway JsonPath
		System.out.println("JSONPATH QUERY ::: "+ JsonPath.from(res.body()).getString("weather.id")); // rest-assured JsonPath
		
//		Map<String, String> m = com.jayway.jsonpath.JsonPath.parse(res.body()).read("$['main']", HashMap.class);
//		System.out.println("Map ::: "+m);
		
	}

}
