package org.ldp4j.examples.app.contactlist.handlers;

import static org.ldp4j.application.data.IndividualReferenceBuilder.newReference;
import static org.ldp4j.examples.app.contactlist.ContactListApplication.getDataStore;
import static org.ldp4j.examples.util.Utils.uri;

import org.ldp4j.application.data.*;
import org.ldp4j.application.domain.RDF;
import org.ldp4j.application.ext.Deletable;
import org.ldp4j.application.ext.Modifiable;
import org.ldp4j.application.ext.annotations.Resource;
import org.ldp4j.application.session.ResourceSnapshot;
import org.ldp4j.application.session.WriteSession;
import org.ldp4j.application.session.WriteSessionException;
import org.ldp4j.examples.app.contactlist.model.Contact;
import org.ldp4j.examples.app.contactlist.model.DataValidationException;
import org.ldp4j.examples.rdf.vocab.VCARD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;

import java.net.URI;

@Resource(
		id=ResourceHandler.ID
	)
public class ResourceHandler implements org.ldp4j.application.ext.ResourceHandler, Modifiable, Deletable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceHandler.class);

	public static final String ID="ContactResourceHandler";

	public DataSet get(ResourceSnapshot resource) {
		
		LOGGER.trace("Retriving the resource {}",resource.name().id());
	
		Name<String> name = (Name<String>) resource.name();
		Contact contact = getDataStore().getEntity(name.id());
		DataSet dataSet = transform(contact);
		
		LOGGER.trace("Contact representation in RDF \n{}", dataSet);
		
		return dataSet;
	}
	
	public void delete(ResourceSnapshot resource, WriteSession session) {
		
		LOGGER.trace("Deleting the resource {}",resource.name().id());
		
		Name<String> name = (Name<String>) resource.name();
		Contact contact = getDataStore().getEntity(name.id());
		
		try {
			getDataStore().deleteEntity(contact.getID());
			session.delete(resource);
			session.saveChanges();
		} catch (WriteSessionException e) {
			// Recover if failed
			getDataStore().addEntity(contact);
			throw new IllegalStateException("Deletion failed",e);
		}
		
	}

	public void update(ResourceSnapshot resource, DataSet content, WriteSession session) {

		LOGGER.trace("Updating the resource {}",resource.name().id());

		Contact oldContact = getDataStore().getEntity(((Name<String>) resource.name()).id());

		ManagedIndividualId id = ManagedIndividualId.createId(resource.name(), ResourceHandler.ID);
		ManagedIndividual contactIndividual = content.individual(id, ManagedIndividual.class);

		Contact contact = transform(contactIndividual, resource.name().id().toString());

		try {
			getDataStore().addEntity(contact);
			session.modify(resource);
			session.saveChanges();
		} catch (WriteSessionException e) {
			// Recover if failed
			getDataStore().addEntity(oldContact);
			throw new IllegalStateException("Deletion failed",e);
		}

	}
	
	/***
	 * Transform a Dataset representation of a Contact to a <code>org.ldp4j.examples.contactlist.model.Contact<code>
	 * object. The RDF representation of the Contact looks like the following. 
	 * <pre>
	 * @prefix vcard: <http://www.w3.org/2006/vcard/ns#> .
	 *	
	 * <> a vcard:Individual;
	 *   vcard:hasURL <http://example.org/fernando#me> ;
	 *   vcard:hasEmail <mailto:fernado@example.com>;
	 *   vcard:fn "Fernando Serena";
	 *   vcard:hasTelephone [ a vcard:Home, vcard:Voice;
	 *   vcard:hasValue <tel:+34655555555> ] . 
	 * </pre> 
	 * @param individual
	 * @param id
	 * @return
	 */
	public static Contact transform (ManagedIndividual individual, String id) {
		
		final Contact contact = new Contact(id);
		
		Property url = individual.property(uri(VCARD.hasURL));
		Preconditions.checkNotNull(url, "No vcard:hasURL property found.");
		if (url.numberOfValues() == 1) {
			Value firstValue = Iterables.getFirst(url,null);
			firstValue.accept(
					new ValueVisitor() {
					public void visitLiteral(Literal<?> value) {
						
					}
					public void visitIndividual(Individual<?, ?> value) {
						contact.setUrl(value.id().toString());
					}
					}
				);
		} else {
			String msg = String.format("Exactly one vcard:hasURL is expected. The input had '%d' values.",
					url.numberOfValues());
			throw new DataValidationException(msg);
		}
		
		Property email = individual.property(uri(VCARD.hasEmail));
		Preconditions.checkNotNull(email, "No vcard:hasEmail property found.");
		if (email.numberOfValues() == 1) {
			Value firstValue = Iterables.getFirst(email,null);
			firstValue.accept(
					new ValueVisitor() {
					public void visitLiteral(Literal<?> value) {
						contact.setEmail(value.get().toString());
					}
					public void visitIndividual(Individual<?, ?> value) {
						contact.setEmail(value.id().toString());
					}
					}
				);
		} else {
			String msg = String.format("Exactly one vcard:hasEmail is expected. The input had '%d' values.",
					url.numberOfValues());
			throw new DataValidationException(msg);
		}
		
		Property givenName = individual.property(uri(VCARD.hasGivenName));
		Preconditions.checkNotNull(givenName, "No vcard:hasGivenName property found.");
		if (givenName.numberOfValues() == 1) {
			Value firstValue = Iterables.getFirst(givenName,null);
			firstValue.accept(
					new ValueVisitor() {
					public void visitLiteral(Literal<?> value) {
						contact.setGivenName(value.get().toString());
					}
					public void visitIndividual(Individual<?, ?> value) {

					}
					}
				);
		} else {
			String msg = String.format("Exactly one vcard:hasGivenName is expected. The input had '%d' values.",
					givenName.numberOfValues());
			throw new DataValidationException(msg);
		}
		
		Property familyName = individual.property(uri(VCARD.hasFamilyName));
		Preconditions.checkNotNull(familyName, "No vcard:hasFamilyName property found.");
		if (familyName.numberOfValues() == 1) {
			Value firstValue = Iterables.getFirst(familyName,null);
			firstValue.accept(
					new ValueVisitor() {
					public void visitLiteral(Literal<?> value) {
						contact.setFamilyName(value.get().toString());
					}
					public void visitIndividual(Individual<?, ?> value) {

					}
					}
				);
		} else {
			String msg = String.format("Exactly one vcard:hasFamilyName is expected. The input had '%d' values.",
					familyName.numberOfValues());
			throw new DataValidationException(msg);
		}
		
		return contact;
	}

	/***
	 * Transform the contact object into a LDP4j dataset
	 * @param contact
	 * @return
	 */
	public static DataSet transform (Contact contact) {
		
		// Using the DSL
		DataSet dataSet = DataDSL.dataSet().individual(newReference().toManagedIndividual(ID).named(contact.getID())).
				hasLink(RDF.TYPE.qualifiedEntityName()).
					referringTo(newReference().toExternalIndividual().atLocation(uri(VCARD.Individual))).
				//hasLink(VCARD.hasURL).
				//	referringTo(newReference().toRelativeIndividual().atLocation("#me")).	
				hasProperty(VCARD.hasEmail).
					withValue(contact.getEmail()).
				hasProperty(VCARD.hasGivenName).
					withValue(contact.getGivenName()).
				hasProperty(VCARD.hasFamilyName).
					withValue(contact.getFamilyName()).
				build();
		
		return dataSet;
	}


}
