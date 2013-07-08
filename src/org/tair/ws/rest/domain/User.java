package org.tair.ws.rest.domain;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@DatabaseTable(tableName = "users")
//@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlRootElement(name = "Customer")
@XmlRootElement

public class User {
   
   /*
   private int id;
   private String firstName;
   private String lastName;
   private String street;
   private String city;
   private String state;
   private String zip;
   private String country;
   */
	public static final String ID_FIELD_NAME = "id";
	public static final String FIRSTNAME_FIELD_NAME = "firstname";
	public static final String LASTNAME_FIELD_NAME = "lastname";
	public static final String STREET_FIELD_NAME = "street";
	public static final String CITY_FIELD_NAME = "city";
	public static final String STATE_FIELD_NAME = "state";
	public static final String ZIP_FIELD_NAME = "zip";
	public static final String COUNTRY_FIELD_NAME = "country";
	
	//@DatabaseField(generatedId = true)
	@DatabaseField(id = true)
	//private int id;
	public int id;
	
	@DatabaseField(columnName = FIRSTNAME_FIELD_NAME, canBeNull = true)
	public String firstname;
	
	@DatabaseField(columnName = LASTNAME_FIELD_NAME, canBeNull = true)
	public String lastname;
	
	@DatabaseField(columnName = STREET_FIELD_NAME, canBeNull = true)
	public String street;
	
	@DatabaseField(columnName = CITY_FIELD_NAME, canBeNull = true)
	public String city;
	
	@DatabaseField(columnName = STATE_FIELD_NAME, canBeNull = true)
	public String state;
	
	@DatabaseField(columnName = ZIP_FIELD_NAME, canBeNull = true)
	public String zip;
	
	@DatabaseField(columnName = COUNTRY_FIELD_NAME, canBeNull = true)
	public String country;
	
	public User() {
		// all persisted classes must define a no-arg constructor with at least package visibility
	}

	public User(String firstname) {
		this.firstname = firstname;
	}
/*
	public Account(String name, String password) {
		this.name = name;
		this.password = password;
	}  
*/
	
	public User(String firstname, String lastname, String street, String city, String state,  String zip, String country) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country =  country;
	}  
	
   public void setId(int id) {
      this.id = id;
   }
   
   public int getId() {
		return id;
	}

   public String getFirstName() {
      return firstname;
   }

   public void setFirstName(String firstname) {
      this.firstname = firstname;
   }

   public String getLastName() {
      return lastname;
   }

   public void setLastName(String lastname) {
      this.lastname = lastname;
   }

   public String getStreet() {
      return street;
   }

   public void setStreet(String street) {
      this.street = street;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getState() {
      return state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getZip() {
      return zip;
   }

   public void setZip(String zip) {
      this.zip = zip;
   }

   public String getCountry() {
      return country;
   }

   public void setCountry(String country) {
      this.country = country;
   }
   
	@Override
	public int hashCode() {
		return firstname.hashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || other.getClass() != getClass()) {
			return false;
		}
		return firstname.equals(((User) other).firstname);
	}
}
