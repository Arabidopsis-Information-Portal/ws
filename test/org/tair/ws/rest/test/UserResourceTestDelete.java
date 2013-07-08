package org.tair.ws.rest.test;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class UserResourceTestDelete
{
 
    public static void main(String[] args) throws Exception
   {

/*
        System.out.println("*** Create a new User ***");
        // Create a new user
        String newUser = "<user>"
                + "<first-name>Vicent110</first-name>"
                + "<last-name>Borge110</last-name>"
                + "<street>345 Caldon Street</street>"
                + "<city>Houston</city>"
                + "<state>TX</state>"
                + "<zip>21345</zip>"
                + "<country>USA</country>"
                + "</user>";

        //URL postUrl = new URL("http://localhost:9095/customers");
        //URL postUrl = new URL("http://localhost:9090/customers");
        //URL postUrl = new URL("http://localhost:7070/ex03_1/customers");
        //URL postUrl = new URL("http://localhost:1070/DW/users");
        URL postUrl = new URL("http://localhost:1070/DW/users/110");
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/xml");
        OutputStream os = connection.getOutputStream();
        os.write(newUser.getBytes());
        os.flush();
        //Assert.assertEquals(HttpURLConnection.HTTP_CREATED, connection.getResponseCode());
        System.out.println("HttpURLConnection.HTTP_CREATED ==> " + HttpURLConnection.HTTP_CREATED);
        System.out.println("connection.getResponseCode() ==> " + connection.getResponseCode());
        System.out.println("Location: " + connection.getHeaderField("Location"));
        System.out.println("ResponseMessage: " + connection.getResponseMessage());
        connection.disconnect();     

      // Get the new user
      System.out.println("*** GET Created User**");
 
      URL getUrl = new URL("http://localhost:1070/DW/users/110");
      HttpURLConnection connection2 = (HttpURLConnection) getUrl.openConnection();
     
      connection2.setRequestMethod("GET");
 
      //connection2.setRequestProperty("accept", "application/xml");
      //connection2.setRequestProperty("accept", "text/html");
      //connection2.setRequestProperty("accept", "application/xhtml+xml");
      //connection2.setRequestProperty("accept", "application/json");
      
      System.out.println("Content-Type: " + connection2.getContentType());
      
      BufferedReader reader = new BufferedReader(new
              InputStreamReader(connection2.getInputStream()));

      String line = reader.readLine();
      while (line != null)
      {
         System.out.println(line);
         line = reader.readLine();
      }
 
      System.out.println("HttpURLConnection.HTTP_OK: " + HttpURLConnection.HTTP_OK);
      System.out.println("getResponseCode: " + connection2.getResponseCode());
      connection2.disconnect();
 
 
   // Update the new customer.  Change Bill's name to William
      String updateCustomer = "<customer>"
              + "<first-name>William110</first-name>"
              + "<last-name>Burke110</last-name>"
              + "<street>256 Clarendon Street</street>"
              + "<city>Boston</city>"
              + "<state>MA</state>"
              + "<zip>02115</zip>"
              + "<country>USA</country>"
              + "</customer>";
      connection = (HttpURLConnection) getUrl.openConnection();
      connection.setDoOutput(true);
      connection.setRequestMethod("PUT");
      connection.setRequestProperty("Content-Type", "application/xml");
      os = connection.getOutputStream();
      os.write(updateCustomer.getBytes());
      os.flush();
      //Assert.assertEquals(HttpURLConnection.HTTP_NO_CONTENT, connection.getResponseCode());
      connection.disconnect();

      
      // Show the update
      System.out.println("**** After Update ***");
      connection = (HttpURLConnection) getUrl.openConnection();
      connection.setRequestMethod("GET");

      System.out.println("Content-Type: " + connection.getContentType());
      reader = new BufferedReader(new
              InputStreamReader(connection.getInputStream()));

      line = reader.readLine();
      while (line != null)
      {
         System.out.println(line);
         line = reader.readLine();
      }
      //Assert.assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
      System.out.println("HttpURLConnection.HTTP_OK: " + HttpURLConnection.HTTP_OK);
      System.out.println("getResponseCode: " + connection.getResponseCode());      
      
      connection.disconnect();
*/
   // Delete the new customer.  
      String deleteCustomer = "<user>"
              + "<first-name>Vicent110</first-name>"
              + "<last-name>Borge110</last-name>"
              + "<street>345 Caldon Street</street>"
              + "<city>Houston</city>"
              + "<state>TX</state>"
              + "<zip>21345</zip>"
              + "<country>USA</country>"
              + "</user>";
      URL deleteUrl = new URL("http://localhost:1070/DW/users/110");
      HttpURLConnection connection = (HttpURLConnection) deleteUrl.openConnection();
      //connection = (HttpURLConnection) getUrl.openConnection();
      connection.setDoOutput(true);
      connection.setRequestMethod("DELETE");
      connection.setRequestProperty("Content-Type", "application/xml");
      //connection.setRequestProperty("Content-Type",  "application/x-www-form-urlencoded");
     
      connection.connect();
      OutputStream os = connection.getOutputStream();
      os.write(deleteCustomer.getBytes());
      os.flush();
      //Assert.assertEquals(HttpURLConnection.HTTP_NO_CONTENT, connection.getResponseCode());
      connection.disconnect();
    
 
   }
   
   
}
