package org.ldp4j.examples.contactlist.app;

import org.ldp4j.application.data.DataSet;
import org.ldp4j.application.ext.ContentProcessingException;
import org.ldp4j.application.ext.Deletable;
import org.ldp4j.application.ext.Modifiable;
import org.ldp4j.application.ext.ResourceHandler;
import org.ldp4j.application.ext.annotations.Resource;
import org.ldp4j.application.session.ResourceSnapshot;
import org.ldp4j.application.session.WriteSession;

@Resource(
		id=ContactResourceHandler.ID
	)
public class ContactResourceHandler implements ResourceHandler, Modifiable, Deletable {

	public static final String ID="ContactResourceHandler";

	
	public DataSet get(ResourceSnapshot resource) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void delete(ResourceSnapshot resource, WriteSession session) {
		
/*		Name<String> name = (Name<String>) resource.name();
		
		// Get the existing person data
		Person person = DummyDataStore.getPerson(name.id());
		// We must have an existing data, if not something is wrong
		Preconditions.checkNotNull(person, "Person '%s' does not have an existing record.",name.id());
		
		try {
			DummyDataStore.deletePerson(name.id());
			session.delete(resource);
			session.saveChanges();
		} catch (WriteSessionException e) {
			// Restore data if update fails
			DummyDataStore.addPerson(person);
			throw new IllegalStateException("Deletion failed",e);
		}*/
		
	}

	public void update(ResourceSnapshot resource, DataSet content,
			WriteSession session) throws ContentProcessingException {

		
	}


}
