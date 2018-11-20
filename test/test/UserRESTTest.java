package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class UserRESTTest {
	public final String BASE_URL="http://localhost:8080/Cacic2018/api";
	public final HttpClient client = HttpClientBuilder.create().build();

	@Test
	public void UserTest() throws ClientProtocolException, IOException {
		createUser();
		listUsers();
		updateUser();
//		deleteUser();
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

	public void createUser() throws ClientProtocolException, IOException {

		String url = BASE_URL + "/user";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("dni", 42156987);
		jsonObject.put("first_name", "Esteban");
		jsonObject.put("last_name", "Pedersen");
		jsonObject.put("email", "e_pedersen@gmail.com");
		String jsonString = jsonObject.toString();
	
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		HttpResponse response = client.execute(post);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		String resultContent = getResult(response);
		System.out.println("Response Content : " + resultContent);
		
		jsonObject = mapper.createObjectNode();
		jsonObject.put("dni", 22156987);
		jsonObject.put("first_name", "Maria");
		jsonObject.put("last_name", "Salas");
		jsonObject.put("email", "salasmaria@gmail.com");
		jsonString = jsonObject.toString();
	
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		resultContent = getResult(response);
		System.out.println("Response Content : " + resultContent);
	}
	
	public void listUsers() throws ClientProtocolException, IOException {

		String url = BASE_URL + "/user";

		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		
		System.out.println("\nGET "+url);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		String resultContent = getResult(response);

		System.out.println("Response Content : " + resultContent);

	}
	
	public void updateUser() throws ClientProtocolException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("first_name", "Test");
		jsonObject.put("last_name", "JUnit");
		String jsonString = jsonObject.toString();

		String url = BASE_URL + "/user/1";
		HttpPut request = new HttpPut(url);
		request.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		HttpResponse response = client.execute(request);

		System.out.println("\nPUT "+url);
		
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		String resultContent = getResult(response);

		System.out.println("Response Content : " + resultContent);

	}
	
	public void deleteUser() throws ClientProtocolException, IOException {

		String url = BASE_URL + "/user/1";
		
		HttpDelete request = new HttpDelete(url);
		HttpResponse response = client.execute(request);
		
		System.out.println("\nDELETE "+url);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		String resultContent = getResult(response);

		System.out.println("Response Content : " + resultContent);

	}
}
