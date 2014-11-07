package org.ldp4j.examples.foaf.app.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class ExampleClient {

	private static final MediaType TEXT_TURTLE = new MediaType("text", "turtle");

	public static void main(String[] args) {

		String resourceURI = "http://localhost:8080/ldp4j/api/nandana/";
		String personURI = "http://localhost:8080/ldp4j/api/nandana/#me";

		String etag;
		String content;

		System.out.printf("GET '%s' representation of '%s' %n", TEXT_TURTLE, resourceURI);

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(resourceURI);

		Response response = target.request(TEXT_TURTLE).get();
		printResposnse(response);

		etag = response.getEntityTag().getValue();
		content = response.readEntity(String.class);

		System.out.println("Modifying the last name, 'Mihindukulasooriya' -> 'Doe'");

		content = modifySurname(content, personURI, "Doe");
		System.out.println(content);

		System.out.printf("PUT a modified presentation of representation of '%s' %n", resourceURI);

		response = target.request().header("If-Match", etag).put(Entity.entity(content, TEXT_TURTLE));
		printResposnse(response);

	}

	/**
	 * Modify the foaf:surname property in the representation
	 */
	private static String modifySurname(String content, String personURI, String newValue) {

		Model model = getModel(content, "TURTLE");

		Property surname = model.createProperty("http://xmlns.com/foaf/0.1/surname");

		Resource person = model.getResource(personURI);
		person.removeAll(surname);
		person.addProperty(surname, newValue);

		return asString(model, "TURTLE");

	}

	private static Model getModel(String content, String format) {

		Model m = ModelFactory.createDefaultModel();
		m.setNsPrefix("foaf", "http://xmlns.com/foaf/0.1/");
		m.setNsPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		m.setNsPrefix("ldp", "http://www.w3.org/ns/ldp#");
		m.setNsPrefix("dcterms", "http://purl.org/dc/terms/");

		m.read(new ByteArrayInputStream(content.getBytes()), null, format);

		return m;

	}

	private static String asString(Model m, String format) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		m.write(baos, format);

		return baos.toString();

	}

	private static void printResposnse(Response response) {

		System.out.println("-----------------------Response---------------------------");
		System.out.printf("%d %s %n", response.getStatus(), response.getStatusInfo().getReasonPhrase());
		System.out.println("Headers: \n");
		MultivaluedMap<String, Object> headerMap = response.getHeaders();
		for (String header : headerMap.keySet()) {
			for (Object ob : headerMap.get(header)) {
				System.out.printf("%s : %s %n", header, ob);
			}
		}

		if (response.hasEntity()) {
			String content = response.readEntity(String.class);
			System.out.println("Content: \n");
			if (TEXT_TURTLE.equals(response.getMediaType())) {
				getModel(content, "TURTLE").write(System.out, "TURTLE");
			} else {
				System.out.println(content);
			}
		}
		System.out.println("--------------------------------------------------------------");

	}

}
