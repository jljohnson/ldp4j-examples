package org.ldp4j.examples.foaf.app;

import static org.ldp4j.application.data.IndividualReferenceBuilder.newReference;
import static org.ldp4j.examples.foaf.app.FOAFProfileApplication.getDataStore;
import static org.ldp4j.examples.util.Utils.uri;

import java.net.URI;

import org.ldp4j.application.data.DataDSL;
import org.ldp4j.application.data.DataSet;
import org.ldp4j.application.data.DataSetUtils;
import org.ldp4j.application.data.Individual;
import org.ldp4j.application.data.Literal;
import org.ldp4j.application.data.ManagedIndividual;
import org.ldp4j.application.data.ManagedIndividualId;
import org.ldp4j.application.data.Name;
import org.ldp4j.application.data.NamingScheme;
import org.ldp4j.application.data.Property;
import org.ldp4j.application.data.RelativeIndividualId;
import org.ldp4j.application.data.Value;
import org.ldp4j.application.data.ValueVisitor;
import org.ldp4j.application.domain.RDF;
import org.ldp4j.application.ext.ContentProcessingException;
import org.ldp4j.application.ext.Modifiable;
import org.ldp4j.application.ext.ResourceHandler;
import org.ldp4j.application.ext.annotations.Resource;
import org.ldp4j.application.session.ResourceSnapshot;
import org.ldp4j.application.session.WriteSession;
import org.ldp4j.application.session.WriteSessionException;
import org.ldp4j.examples.foaf.model.DataValidationException;
import org.ldp4j.examples.foaf.model.Person;
import org.ldp4j.examples.rdf.vocab.DCTerms;
import org.ldp4j.examples.rdf.vocab.FOAF;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;

@Resource(id = FOAFProfileHandler.ID)
public class FOAFProfileHandler implements ResourceHandler, Modifiable {

	public static final String ID = "FOAFProfileHandler";

	public DataSet get(ResourceSnapshot resource) {

		Name<String> name = (Name<String>) resource.name();
		Person person = getDataStore().getEntity(name.id());

		if (person == null) {
			new IllegalStateException(String.format("Person '%s' is not in the database.", name.id()));
		}

		return covertToFOAFProfile(person);
	}

	public void update(ResourceSnapshot resource, DataSet content, WriteSession session)
			throws ContentProcessingException {

		Name<String> name = (Name<String>) resource.name();

		// Get the existing person data
		Person person = getDataStore().getEntity(name.id());
		// We must have an existing data, if not something is wrong
		Preconditions.checkNotNull(person, "Person '%s' does not have an existing record.", name.id());

		// Convert to the new dataset to a person object
		Person personNew = convertToPersonObject(content, name);

		try {
			getDataStore().addEntity(personNew);
			session.modify(resource);
			session.saveChanges();
		} catch (WriteSessionException e) {
			// Restore data if update fails
			getDataStore().addEntity(person);
			throw new IllegalStateException("Update failed", e);
		}

	}

	/***
	 * This method converts a Person business object into a DataSet that is
	 * managed by the LDP4j framework
	 * 
	 * @param person
	 *            the person object to be converted
	 * @return the dataset to be used with the LDP4j framework
	 */
	private DataSet covertToFOAFProfile(Person person) {

		// Using the DSL
		DataSet dataSet = DataDSL.dataSet().individual(newReference().toManagedIndividual(ID).named(person.getID()))
				.hasLink(RDF.TYPE.qualifiedEntityName())
				.referringTo(newReference().toExternalIndividual().atLocation(uri(FOAF.PersonalProfileDocument)))
				.hasProperty(DCTerms.title).withValue(String.format("%1$s's  FOAF file", person.getFirstName()))
				.build();

		// Using the DataSet API

		Name<String> personName = NamingScheme.getDefault().name(person.getID());
		ManagedIndividualId personId = ManagedIndividualId.createId(personName, ID);

		// Create the individual to real world object - person#me
		ManagedIndividualId relativeId = ManagedIndividualId.createId(URI.create("#me"), personId);
		ManagedIndividual relativeIndividual = dataSet.individual(relativeId, ManagedIndividual.class);
		relativeIndividual.addValue(uri(FOAF.firstName), DataSetUtils.newLiteral(person.getFirstName()));
		relativeIndividual.addValue(uri(FOAF.surname), DataSetUtils.newLiteral(person.getLastName()));
		for (String email : person.getEmails()) {
			relativeIndividual.addValue(uri(FOAF.mbox), DataSetUtils.newLiteral(email));
		}

		// Add person#me as the primary topic of the profile
		Individual<ManagedIndividualId, ?> personIndividual = dataSet.individualOfId(personId);
		personIndividual.addValue(uri(FOAF.primaryTopic), relativeIndividual);

		return dataSet;

	}

	/***
	 * This method converts the Dataset from the LDP4j framework into a Person
	 * business object
	 * 
	 * @param dataSet
	 *            the dataset from the the LDP4j framework
	 * @param personName
	 *            name of the resource
	 * @return a Person business object
	 */
	private Person convertToPersonObject(DataSet dataSet, Name<String> personName) {

		ManagedIndividualId personId = ManagedIndividualId.createId(personName, ID);
		Individual<ManagedIndividualId, ?> personIndividual = dataSet.individualOfId(personId);
		Preconditions.checkNotNull(personIndividual, "person individual");
		
		RelativeIndividualId relativeId = RelativeIndividualId.createId(personId, URI.create("#me"));
		Individual<RelativeIndividualId, ?> relativeIndividual = dataSet.individualOfId(relativeId);
		Preconditions.checkNotNull(relativeIndividual, "relative individual");

		final Person person = new Person(personName.id());

		Property firstName = relativeIndividual.property(uri(FOAF.firstName));
		if (firstName.numberOfValues() == 1) {
			Value firstValue = Iterables.getFirst(firstName,null);
			firstValue.accept(
					new ValueVisitor() {
					public void visitLiteral(Literal<?> value) {
						person.setFirstName(value.get().toString());
					}
					public void visitIndividual(Individual<?, ?> value) {
					}
					}
				);
		} else {
			String msg = String.format("Exactly one foaf:firstName is expected. The input had '%d' values.",
					firstName.numberOfValues());
			throw new DataValidationException(msg);
		}
		
		Property surname = relativeIndividual.property(uri(FOAF.surname));
		if (surname.numberOfValues() == 1) {
			Value firstValue = Iterables.getFirst(surname,null);
			firstValue.accept(
					new ValueVisitor() {
					public void visitLiteral(Literal<?> value) {
						person.setLastName(value.get().toString());
					}
					public void visitIndividual(Individual<?, ?> value) {
					}
					}
				);
		} else {
			String msg = String.format("Exactly one foaf:surname is expected. The input had '%d' values.",
					surname.numberOfValues());
			throw new DataValidationException(msg);
		}
		
		Property emails  = relativeIndividual.property(uri(FOAF.mbox));
		for (Value email : emails.values()) {
			email.accept(
					new ValueVisitor() {
					public void visitLiteral(Literal<?> value) {
						person.addEmail(value.get().toString());
					}
					public void visitIndividual(Individual<?, ?> value) {
					}
					}
				);
		}
		
		return person;

	}
}
