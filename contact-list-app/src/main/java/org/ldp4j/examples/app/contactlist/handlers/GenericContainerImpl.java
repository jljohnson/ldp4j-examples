package org.ldp4j.examples.app.contactlist.handlers;

import static org.ldp4j.examples.app.contactlist.ContactListApplication.getDataStore;

import java.net.URI;
import java.util.concurrent.atomic.AtomicInteger;

import org.ldp4j.application.data.DataSet;
import org.ldp4j.application.data.DataSetHelper;
import org.ldp4j.application.data.DataSetModificationException;
import org.ldp4j.application.data.Individual;
import org.ldp4j.application.data.ManagedIndividual;
import org.ldp4j.application.data.ManagedIndividualId;
import org.ldp4j.application.data.Name;
import org.ldp4j.application.data.NamingScheme;
import org.ldp4j.application.data.NewIndividual;
import org.ldp4j.application.ext.ApplicationRuntimeException;
import org.ldp4j.application.ext.ContainerHandler;
import org.ldp4j.application.ext.UnknownResourceException;
import org.ldp4j.application.ext.UnsupportedContentException;
import org.ldp4j.application.session.ContainerSnapshot;
import org.ldp4j.application.session.ResourceSnapshot;
import org.ldp4j.application.session.WriteSession;
import org.ldp4j.application.session.WriteSessionException;
import org.ldp4j.examples.app.contactlist.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

public class GenericContainerImpl  {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GenericContainerImpl.class);
	
	private String containerID;
	
	private DataSet dataSet;
	
	private AtomicInteger counter = new AtomicInteger();
	
	public GenericContainerImpl(String id, DataSet initial) {
		containerID = id;
		dataSet = initial;
	}
	
	public DataSet get(ResourceSnapshot resource) throws UnknownResourceException, ApplicationRuntimeException {
		return dataSet;
	}

	public ResourceSnapshot create(ContainerSnapshot container, DataSet dataSet, WriteSession session) {

		LOGGER.debug("Creating member of container {} using: \n{}", containerID, dataSet);

		String id = Long.toHexString(counter.getAndIncrement());
		Name<String> name = NamingScheme.getDefault().name(id);
		
		NewIndividual newInd=dataSet.individual(DataSetHelper.SELF,NewIndividual.class);
		Individual<URI, ?> self = dataSet.individualOfId(DataSetHelper.SELF);
		
		DataSetHelper helper = DataSetHelper.newInstance(dataSet);

		try {
			ManagedIndividual newManInd = helper.manage(ManagedIndividualId.createId(name, containerID));
		} catch (DataSetModificationException e1) {
			e1.printStackTrace();
		}
		
		ManagedIndividual individual = helper.replace(DataSetHelper.SELF,
				ManagedIndividualId.createId(name, containerID), ManagedIndividual.class);
		Preconditions.checkNotNull(individual, "The contact individual is null");
		
		//TODO how to get the individual without renaming

		Contact contact = ResourceHandler.transform(individual, id);

		try {
			getDataStore().addEntity(contact);
			ResourceSnapshot member = container.addMember(name);
			session.saveChanges();
			return member;
		} catch (WriteSessionException e) {
			getDataStore().deleteEntity(id);
			throw new IllegalStateException("Could not create member", e);
		}

	}
	

	
}
