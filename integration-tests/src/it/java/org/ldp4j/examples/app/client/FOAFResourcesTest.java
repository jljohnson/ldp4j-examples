package org.ldp4j.examples.app.client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

@RunWith(Arquillian.class)
public class FOAFResourcesTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FOAFResourcesTest.class);

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		
		File file = Maven.configureResolver().workOffline().
				resolve("org.ldp4j.examples:ldp4j-foaf:war:0.0.1-SNAPSHOT")
				.withoutTransitivity().asSingleFile();
		
		System.out.println("File:" + file.toString());
		
		WebArchive webapp = ShrinkWrap.create(ZipImporter.class, "foaf.war").importFrom(file)
        .as(WebArchive.class);
		
		return webapp;
	}

	@Test
	public void testFOAFProfileGet(@ArquillianResource URL contextURL) throws Exception {
		
		String resourceURI = contextURL + "ldp4j/api/nandana/";
		
		LOGGER.debug("Context URL : {}", contextURL.toString());
		LOGGER.debug("Resource URL : {}", resourceURI);
		
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(resourceURI);
		get.setHeader("Accept", "text/turtle");
		
		HttpResponse response = client.execute(get);
		
		assertThat("successful retrieval",response.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
		
		HttpEntity entity = response.getEntity();
		
		assertThat("body shouldn't be empty", entity, is(notNullValue()));
		
		Model model = ModelFactory.createDefaultModel();
		model.read(entity.getContent(), null, "TURTLE");
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		model.write(baos, "TURTLE");
		String content = baos.toString();
		LOGGER.debug("Resource Content : {} \n", content);
		
		client.getConnectionManager().shutdown();
		
	}

	@Test
	public void testFOAFProfilePut(@ArquillianResource URL contextURL) throws Exception {

		String resourceURI = contextURL + "ldp4j/api/nandana/";

		LOGGER.debug("Context URL : {}", contextURL.toString());
		LOGGER.debug("Resource URL : {}", resourceURI);

		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(resourceURI);
		get.setHeader("Accept", "text/turtle");

		HttpResponse response = client.execute(get);

		assertThat("successful retrieval",response.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));

		HttpEntity entity = response.getEntity();

		assertThat("body shouldn't be empty", entity, is(notNullValue()));

		Model model = ModelFactory.createDefaultModel();
		model.read(entity.getContent(), null, "TURTLE");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		model.write(baos, "TURTLE");
		String content = baos.toString();
		LOGGER.debug("Resource Content : {} \n", content);

		client.getConnectionManager().shutdown();

		//TODO test the update

	}

}
