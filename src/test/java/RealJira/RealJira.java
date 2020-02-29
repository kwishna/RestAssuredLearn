package RealJira;

/*
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AUTH;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class RealJira {
	public static void main(String[] args) throws IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("https://www.atlassian.com/software/jira");
		StringBody body = new StringBody("{\"status\" : \"pass\"}", ContentType.APPLICATION_JSON);
		HttpResponse resp = client.execute(post);
		List.of(resp).forEach(System.out::println);
		resp.getEntity().writeTo(System.out);
	}
}
*/
