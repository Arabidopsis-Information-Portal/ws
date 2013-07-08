package org.tair.ws.rest.download;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import javax.ws.rs.PathParam;
//import java.awt.PageAttributes.MediaType;
import javax.ws.rs.core.MediaType;
import java.io.FileInputStream;


@Path("/file/more")
public class AnyFileService {
	//Usage: http://localhost:1080/DW/file/more/download2/pdf-file.pdf
	//Usage: http://localhost:1080/DW/file/more/download2/xml-file.xml
	
	//private static final String FILE_PATH = "c:\\file.log";
	//private static final String FILE_PATH = "/users/dkhuang/DATA/MISC/file.log";
/*
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
*/
	
	@Path("/download2/{param}")
	@GET
	public javax.ws.rs.core.Response getFile(@PathParam("param") String filename) throws Exception {
	    
		if (filename.equals("pdf-file.pdf")) {
	        File file = new File("/users/dkhuang/DATA/MISC/pdf-file.pdf");
	        //return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
	          //  .header("content-disposition", "attachment; filename =" + file.getName())
	            //.build();
	        //return Response.ok(new File("/users/dkhuang/DATA/MISC/pdf-file.pdf")).type("application/pdf").build();
	        return Response.ok(new File("/users/dkhuang/DATA/MISC/pdf-file.pdf")).header("Content-Disposition", "attachment; filename=" + file.getName()).build();
	        
	    }

	    /* default to xml file */
	    //return Response.ok(new FileInputStream("custom.xml")).type("application/xml").build();
	    //return Response.ok(new FileInputStream("/users/dkhuang/DATA/MISC/xml-file.xml")).type("application/xml").build();
	    File file = new File("/users/dkhuang/DATA/MISC/xml-file.xml");
	    return Response.ok(new FileInputStream("/users/dkhuang/DATA/MISC/xml-file.xml")).header("Content-Disposition", "attachment; filename=" + file.getName()).build();
	}
	

}