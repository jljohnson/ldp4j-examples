package org.ldp4j.examples.foaf.model;

import java.util.HashSet;
import java.util.Set;

public class Person {
	
	public Person (String id) {
		this.id = id;
	}
	
	public Person(String id, String fn, String ln, Set<String> emails) {
		this.id = id;
		this.firstName = fn;
		this.lastName = ln;
		this.emails = emails;
	}
	
	public Person(String id, String fn, String ln, String email) {
		this.id = id;
		this.firstName = fn;
		this.lastName = ln;
		addEmail(email);
	}

	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private Set<String> emails;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<String> getEmails() {
		return emails;
	}

	public void setEmails(Set<String> emails) {
		this.emails = emails;
	}
	
	public void addEmail(String email){
		if (emails == null) {
			emails = new HashSet<String>();
		} 
		emails.add(email);
	}
	
}
