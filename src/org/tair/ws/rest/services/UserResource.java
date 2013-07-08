package org.tair.ws.rest.services;


import org.tair.ws.rest.domain.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
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

//ADDED

import java.io.File;
import java.net.URISyntaxException;
import javax.activation.MimetypesFileTypeMap;



public class UserResource {
   //private Map<Integer, User> userDB = new ConcurrentHashMap<Integer, User>();
	//private Map<Integer, User> mapin = new ConcurrentHashMap<Integer, User>();
   private AtomicInteger idCounter = new AtomicInteger(21);
   int id;

   public UserResource() {
   }
   public UserResource(int id2) {
	   this.id = id2;
	   System.out.println("id in constructor ==> " + this.id);
   }

   
   
  
   @GET  
   //@Produces("application/json")
   @Produces("application/xml")
   //@Produces({"application/xhtml+xml", "application/json"})
   //@Produces("application/xml+json")
   //public User getUser(@PathParam("id") int id) throws SQLException{
   public User getUser() throws SQLException{
   //public User getUser(int id) throws SQLException{
	   //Usage: http://localhost:1070/DW/users/31
	   //Usage with curl client, --data doesn't matter, can be any data
	   //curl -X GET -HContent-type:application/xml http://localhost:1070/DW/users/22
	   //curl -X GET -HContent-type:application/xml --data 
	   //"<user><first-name>Vicent</first-name><last-name>Borge</last-name>
	   //<street>345 Caldon Street</street><city>Houston</city><state>TX</state>
	   //<zip>21345</zip><country>USA</country></user>" http://localhost:1070/DW/users/22
	   
	   Dao<User, Integer> userDao;
	   String DATABASE_URL = "jdbc:oracle:thin:@wales:1521:tairtest";
		String userdao = "rftest";
		//String password = "rf0test";
		String password = "rf1tst";
		ConnectionSource connectionSource = null;
		
		
		
		try {
			connectionSource = new JdbcConnectionSource(DATABASE_URL, userdao, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		userDao = DaoManager.createDao(connectionSource, User.class);
	   
	
				System.out.println();
				System.out.println("II. loop through items in the database");
				
				for (User user2 : userDao) {
					//verifyAccount(account, account2);
					//Check the record in which id equals to id on url.
					System.out.println(user2.getId() + " " + user2.getFirstName() + " " + user2.getLastName() + " " + user2.getStreet() + " " + user2.getCity() + " " + user2.getState() + " " + user2.getZip() + " " + user2.getCountry());
				}
				
			
			  
		
	//I. queryForAll()
		System.out.println();
		System.out.println("I. query for all items in the database");
		//List<User> users = userDao.queryForAll();
		System.out.println("this.id ==> " + this.id);
		User user = userDao.queryForId(this.id);
		
		if (user == null) {
	         throw new WebApplicationException(Response.Status.NOT_FOUND);
	      }
		return user;
		
		
	
      
  	
   }  
    
   @POST
   @Consumes("application/xml")
   //@Consumes("application/json")
   public Response createUser(InputStream is) throws SQLException {
	
	   //USAGE
	   //curl -X POST -HContent-type:application/xml --data "<user><first-name>Bill260</first-name><last-name>Burke260</last-name>
	   //<street>256 Clarendon Street4</street><city>Boston4</city><state>MA4</state><zip>021154</zip><country>USA4</country></user>" 
	   //http://localhost:1070/DW/users/260
	   
	   Dao<User, Integer> userDao;
	   String DATABASE_URL = "jdbc:oracle:thin:@wales:1521:tairtest";
		String userdao = "rftest";
		//String password = "rf0test";
		String password = "rf1tst";
		ConnectionSource connectionSource = null;
		
		try {
			connectionSource = new JdbcConnectionSource(DATABASE_URL, userdao, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//userDao = DaoManager.createDao(connectionSource, User.class);
		userDao = DaoManager.createDao(connectionSource, User.class);
		
		//TableUtils.createTable(connectionSource, User.class);
		
		
      User user = readUser(is);
      //User.setId(idCounter.incrementAndGet());
      //idCounter.incrementAndGet();
      //user.setId(idCounter.intValue());
      user.setId(id);
      //System.out.println("idCounter ==> " + idCounter);

      //mapin.put(user.getId(), user);
      userDao.create(user);
      
      System.out.println("Created user " + user.getId());
      return Response.created(URI.create("/users/" + user.getId())).build();

   }
   
   @PUT
   //@Path("{id}")
   @Consumes("application/xml")
   //public Response createUser(InputStream is) throws SQLException {
   //public void updateUser(@PathParam("id") int id, InputStream is)  throws SQLException {
   public void updateUser(InputStream is)  throws SQLException {
   
	   //USAGE
	   //curl -X PUT -HContent-type:application/xml --data "<user><first-name>BillUPDATE</first-name><last-name>BurkeUPDATE</last-name>
	   //<street>256 Clarendon Street4</street><city>Boston4</city><state>MA4</state><zip>021154</zip><country>USA4</country></user>" 
	   //http://localhost:1070/DW/users/260
	   
	   Dao<User, Integer> userDao;
	   String DATABASE_URL = "jdbc:oracle:thin:@wales:1521:tairtest";
		String userdao = "rftest";
		//String password = "rf0test";
		String password = "rf1tst";
		ConnectionSource connectionSource = null;
		
		try {
			connectionSource = new JdbcConnectionSource(DATABASE_URL, userdao, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		userDao = DaoManager.createDao(connectionSource, User.class);
		
		//TableUtils.createTable(connectionSource, User.class);
		
		
      User user = readUser(is);
      //user.setId(idCounter.incrementAndGet());
      //idCounter.incrementAndGet();
      //user.setId(idCounter.intValue());
      user.setId(id);
      System.out.println("getId ==> " + user.getId());

      //mapin.put(user.getId(), user);
      userDao.update(user);
      
      System.out.println("Updated user " + user.getId());
      //return Response.created(URI.create("/users/" + user.getId())).build();

   }  
   
   @DELETE
   //@Path("{id}")
   //@Consumes("application/xml")
   //public Response createUser(InputStream is) throws SQLException {
   //public void updateUser(@PathParam("id") int id, InputStream is)  throws SQLException {
   public void deleteUser(InputStream is)  throws SQLException {
   
	   //USAGE
	   //curl -X DELETE -HContent-type:application/xml --data "<user><first-name>BillUPDATE</first-name><last-name>BurkeUPDATE</last-name>
	   //<street>256 Clarendon Street4</street><city>Boston4</city><state>MA4</state><zip>021154</zip><country>USA4</country></user>" 
	   //http://localhost:1070/DW/users/260
	   
	   Dao<User, Integer> userDao;
	   String DATABASE_URL = "jdbc:oracle:thin:@wales:1521:tairtest";
		String userdao = "rftest";
		//String password = "rf0test";
		String password = "rf1tst";
		ConnectionSource connectionSource = null;
		
		try {
			connectionSource = new JdbcConnectionSource(DATABASE_URL, userdao, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		userDao = DaoManager.createDao(connectionSource, User.class);
		
		//TableUtils.createTable(connectionSource, User.class);
		
/*		
      User user = readUser(is);
      //user.setId(idCounter.incrementAndGet());
      //idCounter.incrementAndGet();
      //user.setId(idCounter.intValue());
      user.setId(id);
      //System.out.println("getId ==> " + user.getId());

      //mapin.put(user.getId(), user);
      userDao.delete(user);
      
      System.out.println("Deleted user " + user.getId());
      //return Response.created(URI.create("/users/" + user.getId())).build();
*/     
      
      userDao.deleteById(this.id);
		
		
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
