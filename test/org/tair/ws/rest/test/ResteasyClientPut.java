package org.tair.ws.rest.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.tair.ws.rest.domain.User;

public class ResteasyClientPut {

	public static void main(String[] args) throws Exception {
		
		////Resteasy Client for Put
	
		try {
			//ClientRequest request = new ClientRequest(
				//	"http://localhost:8080/RESTfulExample/json/product/post");
			ClientRequest request =
					new ClientRequest("http://localhost:1070/DW/users/{id}");

			request.pathParameter("id", 200);

			//String input = "{\"qty\":100,\"name\":\"iPad 4\"}";
			//String input = "{\"first-name\":\"Rob\", \"last-name\":\"Hanson\", \"street\": \"456 Ardon Ave\", \"city\": \"Mibrae\", \"state\":\"CA\", \"zip\":\"12345\", \"country\":\"USA\"}";
			
			String input = "<user><first-name>Vicentput3</first-name><last-name>Borgeput3</last-name><street>345 Caldon Street</street><city>Houston</city><state>TX</state><zip>21345</zip><country>USA</country></user>";
			request.body("application/xml", input);
			
			ClientResponse<User> response = request.put(User.class);
			
			//BufferedReader br = new BufferedReader(new InputStreamReader( new ByteArrayInputStream(response.getEntity().toString())));
			User user = response.getEntity();
			
			System.out.println("ResponseStatus ==> " + response.getResponseStatus());
			response.releaseConnection();
			
			} catch (Exception e) {
				e.printStackTrace();
			
			}


			//BufferedReader br = new BufferedReader(new InputStreamReader(
					//new ByteArrayInputStream(response.getEntity().getBytes())));

			//String output;
			//System.out.println("Output from Server .... \n");
			//while ((output = br.readLine()) != null) {

				//System.out.println(output);
			//}

		//} 
	}	
}
