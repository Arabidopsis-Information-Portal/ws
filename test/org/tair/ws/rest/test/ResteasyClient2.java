package org.tair.ws.rest.test;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.tair.ws.rest.domain.User;

public class ResteasyClient2 {

	public static void main(String[] args) throws Exception {
		
		////Resteasy Client using ClientResponse.get(User.class)
	
		ClientRequest request =
				new ClientRequest("http://localhost:1070/DW/users/{id}");
				request.accept("application/xml")
				.pathParameter("id", 54);
				ClientResponse<User> response = request.get(User.class);
				
				
				
				
				try {
					if (response.getStatus() != 200)
						throw new RuntimeException("Failed!");
					User user = response.getEntity();
					System.out.println("ID ==> " + user.getId());				
				
				} finally {
					response.releaseConnection();
				}
				
		
		
	}	
}
