package org.ldp4j.examples.app.contactlist.model;

import org.ldp4j.examples.util.persistence.Entity;

public class Contact implements Entity {
	
	private String id;
	
	private String givenName;
	
	private String familyName;
	
	private String email;
	
	private String url;
	
	public Contact (String id) {
		this.id = id;
	}

	public void setID(String id) {
		this.id = id;
	}
	
	public String getID() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
