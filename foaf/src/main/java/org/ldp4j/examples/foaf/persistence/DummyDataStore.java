package org.ldp4j.examples.foaf.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ldp4j.examples.foaf.model.Person;

import com.google.common.collect.ImmutableList;

public class DummyDataStore {
	
	private static Map<String, Person> data = new HashMap<String, Person>(); 
	
	static {
		data.put("nandana", new Person("nandana", "Nandana", "Mihindukulasooriya", "nmihindu@fi.upm.es"));
		data.put("miguel", new Person("miguel", "Miguel", "Esteban Gutiérrez", "mesteban@fi.upm.es"));
		data.put("raul", new Person("raul", "Raúl", "García Castro", "rgarcia@fi.upm.es"));
		
	}

	public static void addPerson(Person person){
		data.put(person.getId(), person);
	}
	
	public static Person getPerson(String id){
		return data.get(id);
	}
	
	public static Person deletePerson(String id) {
		return data.remove(id);
	}
	
	public static List<Person> getPersonlist() {
		return ImmutableList.copyOf(data.values());
	}
	

}
