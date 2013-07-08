package org.tair.ws.rest.services;

import java.io.File;
import java.net.URISyntaxException;
import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;


@Path("/")
public class Index {
	
	@GET	
	@Produces("text/html")
	public Response  index() throws URISyntaxException {		
	
		//File f = new File("index.html");
		//File f = new File(System.getProperty("user.dir")+"/index.html");
		//File f = new File(System.getProperty("jboss.home.dir")+"/index.html");
		File f = new File(System.getProperty("jboss.webapp.dir")+"/index.html");
		
		String mt = new MimetypesFileTypeMap().getContentType(f);
		//return Response.ok(f, mt).build();	
		
		return Response.status(200).entity("HTTP GET method called").build();

		
	}
	
	@GET
	@Path("/loginform.html")
	@Produces("text/html")
	public Response  loginform() throws URISyntaxException {		
	
		//File f = new File("index.html");
		//File f = new File(System.getProperty("user.dir")+"/index.html");
		//File f = new File(System.getProperty("jboss.home.dir")+"/index.html");
		File f = new File(System.getProperty("jboss.webapp.dir")+"/loginform.html");
		
		String mt = new MimetypesFileTypeMap().getContentType(f);
		//return Response.ok(f, mt).build();	
		
		return Response.status(200).entity("HTTP GET method called").build();

		
	}	

	@GET
	
	@Path("/login?{zip}")
	@Produces("text/html")
	public String login(@QueryParam("zip") String zip) {
	
	return "Id is " + zip;
	
	}
	
	@GET
	@Path("/query")
	public Response getUsers(@DefaultValue("1000") @QueryParam("from") int from,
			@DefaultValue("999")@QueryParam("to") int to,
			@DefaultValue("name") @QueryParam("orderBy") List<String> orderBy) {

		return Response
				.status(200)
				.entity("getUsers is called, from : " + from + ", to : " + to
						+ ", orderBy" + orderBy.toString()).build();

	}
	
	@GET
	@Path("/query2")
	public Response getUsers2(@Context UriInfo info) {
 
		String from = info.getQueryParameters().getFirst("from");
		String to = info.getQueryParameters().getFirst("to");
		List<String> orderBy = info.getQueryParameters().get("orderBy");
 
		return Response
		   .status(200)
		   .entity("getUsers2 is called, from : " + from + ", to : " + to
			+ ", orderBy" + orderBy.toString()).build();
 
	}
 

 

	
	@GET	
	@Path("/hello")
	public Response  helloGet() {					
		
		return Response.status(200).entity("HTTP GET method called").build();
		
	}	

        @POST
	@Path("/hello")
	public Response  helloPost(){					
		
		return Response.status(200).entity("HTTP POST method called").build();
		
	}
        
        @POST
        @Path("/login")
        public String login(@FormParam("email") String e, @FormParam("password") String p) {  
        
        return "Logged with " + e + " ::: " + p;
        
        }
	
}