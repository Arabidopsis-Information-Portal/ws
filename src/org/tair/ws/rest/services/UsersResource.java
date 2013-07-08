package org.tair.ws.rest.services;


import org.tair.ws.rest.domain.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

//ADDED
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import java.awt.PageAttributes.MediaType;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

//import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;

@Path("/users")
public class UsersResource {
	//Usage to find all users: http://localhost:1070/DW/users
	//Usage to find specific user: http://localhost:1070/DW/users/31
   //private Map<Integer, User> userDB = new ConcurrentHashMap<Integer, User>();
	//private Map<Integer, User> mapin = new ConcurrentHashMap<Integer, User>();
   private AtomicInteger idCounter = new AtomicInteger(21);

   public UsersResource() {
   }

@GET
//@Produces({"application/xhtml+xml", "application/json"})
@Produces("application/xml")
//@Produces("application/json")
//@Produces({"application/json", "application/xml"})
public List<User> getUsersResource() throws SQLException {
	Dao<User, Integer> userDao;
	   String DATABASE_URL = "jdbc:oracle:thin:@wales:1521:tairtest";
		String user = "rftest";
		//String password = "rf0test";
		String password = "rf1tst";
		ConnectionSource connectionSource = null;
		
		try {
			connectionSource = new JdbcConnectionSource(DATABASE_URL, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userDao = DaoManager.createDao(connectionSource, User.class);

	List<User> users = userDao.queryForAll();
	
	System.out.println("TEST for queryForAll ");
	
	
	
	return users;
}
 
   
@Path("{id}")
	public UserResource getUserResource(@PathParam("id") int id) {
	System.out.println("id in call UserResource ==> " + id);
	return new UserResource(id);
}
   

   protected void outputUser(OutputStream os, User usr) throws IOException {
      PrintStream writer = new PrintStream(os);
      writer.println("<user id=\"" + usr.getId() + "\">");
      writer.println("   <first-name>" + usr.getFirstName() + "</first-name>");
      writer.println("   <last-name>" + usr.getLastName() + "</last-name>");
      writer.println("   <street>" + usr.getStreet() + "</street>");
      writer.println("   <city>" + usr.getCity() + "</city>");
      writer.println("   <state>" + usr.getState() + "</state>");
      writer.println("   <zip>" + usr.getZip() + "</zip>");
      writer.println("   <country>" + usr.getCountry() + "</country>");
      writer.println("</user>");
   }

   protected User readUser(InputStream is) {
      try {
         DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
         Document doc = builder.parse(is);
         Element root = doc.getDocumentElement();
         User usr = new User();
         if (root.getAttribute("id") != null && !root.getAttribute("id").trim().equals(""))
            usr.setId(Integer.valueOf(root.getAttribute("id")));
         NodeList nodes = root.getChildNodes();
         for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);
            if (element.getTagName().equals("first-name")) {
               usr.setFirstName(element.getTextContent());
            }
            else if (element.getTagName().equals("last-name")) {
               usr.setLastName(element.getTextContent());
            }
            else if (element.getTagName().equals("street")) {
               usr.setStreet(element.getTextContent());
            }
            else if (element.getTagName().equals("city")) {
               usr.setCity(element.getTextContent());
            }
            else if (element.getTagName().equals("state")) {
               usr.setState(element.getTextContent());
            }
            else if (element.getTagName().equals("zip")) {
               usr.setZip(element.getTextContent());
            }
            else if (element.getTagName().equals("country")) {
               usr.setCountry(element.getTextContent());
            }
         }
         return usr;
      }
      catch (Exception e) {
         throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
      }
   }

}
