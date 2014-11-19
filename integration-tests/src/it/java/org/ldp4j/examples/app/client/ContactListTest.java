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
public class ContactListTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactListTest.class);
	
	private static final String WEBAPP_ID = "org.ldp4j.examples:ldp4j-contact-list:war:"; 
	
	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		
		String version = System.getProperty("contactListVersion");
		String mavenCoordinate = WEBAPP_ID + version;
		
		LOGGER.debug("Creating the deployment of '{}'", mavenCoordinate);
		
		File warFile = Maven.configureResolver().workOffline().
				resolve(mavenCoordinate).withoutTransitivity().asSingleFile();
	
		LOGGER.debug("Using the war file in '{}'", warFile.toString());
		
		WebArchive webapp = ShrinkWrap.create(ZipImporter.class, "contact-list.war").importFrom(warFile)
        .as(WebArchive.class);
		
		return webapp;
	}
	
	@Test
	public void testBasicContainerGet(@ArquillianResource URL contextURL) throws Exception {
		
		String basiContainerURI = contextURL + "ldp4j/api/contact_list_basic/";
		
		LOGGER.debug("Context URL : {}", contextURL.toString());
		LOGGER.debug("Basic Container URL : {}", basiContainerURI);
		
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(basiContainerURI);
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
		LOGGER.debug("Basic Container Content : {} \n", content);
		
		client.getConnectionManager().shutdown();
		
	}
	
	@Test
	public void testDirectContainerGet(@ArquillianResource URL contextURL) throws Exception {
		
		String directContainerURI = contextURL + "ldp4j/api/contact_list_direct/";
		
		LOGGER.debug("Context URL : {}", contextURL.toString());
		LOGGER.debug("Direct Container URL : {}", directContainerURI);
		
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(directContainerURI);
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
		LOGGER.debug("Direct Content : {} \n", content);
		
		client.getConnectionManager().shutdown();
		
	}
	
	
	@Test
	public void testIndirectContainerGet(@ArquillianResource URL contextURL) throws Exception {
		
		String indirectContainerURI = contextURL + "ldp4j/api/contact_list_indirect/";
		
		LOGGER.debug("Context URL : {}", contextURL.toString());
		LOGGER.debug("Indirect Container URL : {}", indirectContainerURI);
		
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(indirectContainerURI);
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
		LOGGER.debug("Indirect Container Content : {} \n", content);
		
		client.getConnectionManager().shutdown();
		
	}
	
	
	

}
