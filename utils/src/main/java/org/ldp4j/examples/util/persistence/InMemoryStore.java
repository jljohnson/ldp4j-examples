package org.ldp4j.examples.util.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;

public class InMemoryStore<T extends Entity> {
	
	private  Map<String, T> data = new HashMap<String, T>(); 
	
	public void addEntity(T entity){
		data.put(entity.getID(), entity);
	}
	
	public T getEntity(String id){
		return data.get(id);
	}
	
	public T deleteEntity(String id) {
		return data.remove(id);
	}
	
	public List<T> getEntityList() {
		return ImmutableList.copyOf(data.values());
	}

}
