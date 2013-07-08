package org.tair.ws.rest.test;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class MyClient {
	
	public static void main(String[] args) throws Exception {
		
		//This is Apache HttpClient
		
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet("http://localhost:1080/DW/users");
		get.addHeader("accept", "application/xml");
		//get.addHeader("accept", "application/json");
		//get.addHeader("accept", "text/html");
		//get.addHeader("accept", "text/xml");
		//get.addHeader("accept", "application/xml");
		HttpResponse response = client.execute(get);
		//if (response.getStatusLine().getStatusCode() != 200) { throw new RuntimeException("Operation failed: " + response.getStatusLine().getStatusCode()); }
		System.out.println("Content-Type: " +
		response.getEntity().getContentType().getValue());
		BufferedReader reader = new BufferedReader(new
				InputStreamReader(response.getEntity()
				.getContent()));
		String line = reader.readLine();
		while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
		client.getConnectionManager().shutdown();
	}
}	
		

