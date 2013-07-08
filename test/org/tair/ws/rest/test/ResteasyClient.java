package org.tair.ws.rest.test;

import org.jboss.resteasy.client.ClientRequest;
import org.tair.ws.rest.domain.User;

public class ResteasyClient {

	public static void main(String[] args) throws Exception {
		
		//Resteasy Client using getTarget(User.class)
	
		ClientRequest request =
				new ClientRequest("http://localhost:1070/DW/users/{id}");
				request.accept("application/xml")
				.pathParameter("id", 53);
				User user = request.getTarget(User.class);
				System.out.println("ID ==> " + user.getId());
				
		
		
	}	
}
