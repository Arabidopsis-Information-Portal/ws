package org.tair.ws.rest.download;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import javax.ws.rs.PathParam;

@Path("/file/more")
public class FileService {
	//Usage: http://localhost:1080/DW/file/more/download/filetwo.log
	
	//private static final String FILE_PATH = "c:\\file.log";
	//private static final String FILE_PATH = "/users/dkhuang/DATA/MISC/file.log";
	private static final String FILE_PATH = "/users/dkhuang/DATA/MISC";

	@GET
	@Path("/download/{param}")
	@Produces("text/plain")
	public Response getFile(@PathParam("param") String filename) {

		File file = new File("/users/dkhuang/DATA/MISC/" + filename);

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
				"attachment; filename=\"file_from_server.log\"");
		//response.header("Content-Disposition",
				//"attachment; filename=" + file.getName());
		//response.header("Content-Disposition",
				//"attachment; filename=" + "file_from_server.log");
		//response.header("Content-Disposition",
				//file.getName());
		//response.header("Content-Disposition",
				//"attachment; filename= file.getName()");
		return response.build();

	}
	/*
		@GET
		@Path("/{param}")
		public Response getMsg(@PathParam("param") String msg) {
	 
			String output = "RESTEasy say : " + msg;
	 
			return Response.status(200).entity(output).build();
	 
		}
		*/
	

}