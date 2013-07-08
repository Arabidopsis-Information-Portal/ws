package org.tair.ws.rest.test;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.tair.ws.rest.domain.User;

public class ResteasyClientDelete {

	public static void main(String[] args) throws Exception {
		
		////Resteasy Client using ClientResponse.get(User.class)
	
		ClientRequest request =
				new ClientRequest("http://localhost:1070/DW/users/{id}");
				//request.accept("application/xml").pathParameter("id", 102);
		request.pathParameter("id", 102);
				//ClientResponse<User> response = request.delete(User.class);
				ClientResponse<User> response = request.delete();
			    //request.delete(User.class);
			    //System.out.println("DELETED");
				System.out.println("Status ==> " + response.getStatus());
				
				
				
				/*
				try {
					if (response.getStatus() != 200)
						throw new RuntimeException("Failed!");
					//User user = response.getEntity();
					System.out.println("DELETED");				
				
				} finally {
					response.releaseConnection();
				}
				*/
				
		
		
	}	
}
