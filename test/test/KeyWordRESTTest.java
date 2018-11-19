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

public class KeyWordRESTTest {

	public final String BASE_URL="http://localhost:8080/Cacic2018/api";
	public final HttpClient client = HttpClientBuilder.create().build();

	@Test
	public void KeyWordTest() throws ClientProtocolException, IOException {
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
		jsonObject.put("word", "Machine Learning");
		String jsonString = jsonObject.toString();
	
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		HttpResponse response = client.execute(post);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		String resultContent = getResult(response);
		System.out.println("Response Content : " + resultContent);
		
		
		jsonObject = mapper.createObjectNode();
		jsonObject.put("word", "IOT");
		jsonString = jsonObject.toString();
	
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		resultContent = getResult(response);
		System.out.println("Response Content : " + resultContent);
		
		jsonObject = mapper.createObjectNode();
		jsonObject.put("word", "Seguridad");
		jsonString = jsonObject.toString();
	
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		resultContent = getResult(response);
		System.out.println("Response Content : " + resultContent);
		
		
		jsonObject = mapper.createObjectNode();
		jsonObject.put("word", "Red");
		jsonString = jsonObject.toString();
	
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		resultContent = getResult(response);
		System.out.println("Response Content : " + resultContent);
		
		
		jsonObject = mapper.createObjectNode();
		jsonObject.put("word", "Javascript");
		jsonString = jsonObject.toString();
	
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		resultContent = getResult(response);
		System.out.println("Response Content : " + resultContent);
		
		jsonObject = mapper.createObjectNode();
		jsonObject.put("word", "Python");
		jsonString = jsonObject.toString();
	
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		resultContent = getResult(response);
		System.out.println("Response Content : " + resultContent);
		
		jsonObject = mapper.createObjectNode();
		jsonObject.put("word", "Micro Servicios");
		jsonString = jsonObject.toString();
	
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		resultContent = getResult(response);
		System.out.println("Response Content : " + resultContent);
		
		jsonObject = mapper.createObjectNode();
		jsonObject.put("word", "Arquitectura");
		jsonString = jsonObject.toString();
	
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		resultContent = getResult(response);
		System.out.println("Response Content : " + resultContent);
	}
}