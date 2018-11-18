package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class KeyWordRESTServiceTest {

	public final String BASE_URL="http://localhost:8080/Cacic2018/rest";
	public final HttpClient client = HttpClientBuilder.create().build();

	@Test
	public void UserRESTServiceTest() throws ClientProtocolException, IOException {
		createWords();
	}

	private String getResult(HttpResponse response) throws IOException {
		HttpEntity entity = response.getEntity();
		if ( entity != null ) {
			BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			return result.toString();
		}else {
			return "";
		}
	}

	public void createWords() throws ClientProtocolException, IOException {

		String url = BASE_URL + "/keyword";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("word", "IOT");
		String jsonString = jsonObject.toString();
	
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		HttpResponse response = client.execute(post);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		String resultContent = getResult(response);
		System.out.println("Response Content : " + resultContent);
	}
}