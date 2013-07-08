package org.tair.ws.rest.test;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.tair.ws.rest.domain.User;

public class ResteasyClientPost {

	public static void main(String[] args) {

//Good to make input string for xml.
/*
		ClientRequest request = new ClientRequest("http://url/resource/{id}");

		StringBuilder sb = new StringBuilder();
		sb.append("<user id=\"0\">");
		sb.append("   <username>Test User</username>");
		sb.append("   <email>test.user@test.com</email>");
		sb.append("</user>");


		String xmltext = sb.toString();

		request.accept("application/xml").pathParameter("id", 1).body( MediaType.APPLICATION_XML, xmltext);

		String response = request.postTarget( String.class); //get response and automatically unmarshall to a string.

		//or

		ClientResponse<String> response = request.post();
	*/
		
		
		try {
			//ClientRequest request = new ClientRequest(
				//	"http://localhost:8080/RESTfulExample/json/product/post");
			ClientRequest request =
					new ClientRequest("http://localhost:1070/DW/users/{id}");

			request.pathParameter("id", 200);

			//String input = "{\"qty\":100,\"name\":\"iPad 4\"}";
			//String input = "{\"first-name\":\"Rob\", \"last-name\":\"Hanson\", \"street\": \"456 Ardon Ave\", \"city\": \"Mibrae\", \"state\":\"CA\", \"zip\":\"12345\", \"country\":\"USA\"}";
			
			String input = "<user><first-name>Vicent</first-name><last-name>Borge</last-name><street>345 Caldon Street</street><city>Houston</city><state>TX</state><zip>21345</zip><country>USA</country></user>";
			request.body("application/xml", input);
			
			ClientResponse<User> response = request.post(User.class);

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
