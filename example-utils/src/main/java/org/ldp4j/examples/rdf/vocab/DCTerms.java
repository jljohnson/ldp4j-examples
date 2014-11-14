package org.ldp4j.examples.rdf.vocab;

/**
 * String constants for the vocabulary defined in http://dublincore.org/usage/terms/
 */
public class DCTerms {
	
	private DCTerms () {
		// a utility class to hold the constants
	}
	
	
    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://purl.org/dc/terms/";
	
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>A summary of the resource.</p> */
    public static final String abstract_ = new String( "http://purl.org/dc/terms/abstract" );
    
    /** <p>Information about who can access the resource or an indication of its security 
     *  status.</p>
     */
    public static final String accessRights = new String( "http://purl.org/dc/terms/accessRights" );
    
    /** <p>The method by which items are added to a collection.</p> */
    public static final String accrualMethod = new String( "http://purl.org/dc/terms/accrualMethod" );
    
    /** <p>The frequency with which items are added to a collection.</p> */
    public static final String accrualPeriodicity = new String( "http://purl.org/dc/terms/accrualPeriodicity" );
    
    /** <p>The policy governing the addition of items to a collection.</p> */
    public static final String accrualPolicy = new String( "http://purl.org/dc/terms/accrualPolicy" );
    
    /** <p>An alternative name for the resource.</p> */
    public static final String alternative = new String( "http://purl.org/dc/terms/alternative" );
    
    /** <p>A class of entity for whom the resource is intended or useful.</p> */
    public static final String audience = new String( "http://purl.org/dc/terms/audience" );
    
    /** <p>Date (often a range) that the resource became or will become available.</p> */
    public static final String available = new String( "http://purl.org/dc/terms/available" );
    
    /** <p>A bibliographic reference for the resource.</p> */
    public static final String bibliographicCitation = new String( "http://purl.org/dc/terms/bibliographicCitation" );
    
    /** <p>An established standard to which the described resource conforms.</p> */
    public static final String conformsTo = new String( "http://purl.org/dc/terms/conformsTo" );
    
    /** <p>An entity responsible for making contributions to the resource.</p> */
    public static final String contributor = new String( "http://purl.org/dc/terms/contributor" );
    
    /** <p>The spatial or temporal topic of the resource, the spatial applicability of 
     *  the resource, or the jurisdiction under which the resource is relevant.</p>
     */
    public static final String coverage = new String( "http://purl.org/dc/terms/coverage" );
    
    /** <p>Date of creation of the resource.</p> */
    public static final String created = new String( "http://purl.org/dc/terms/created" );
    
    /** <p>An entity primarily responsible for making the resource.</p> */
    public static final String creator = new String( "http://purl.org/dc/terms/creator" );
    
    /** <p>A point or period of time associated with an event in the lifecycle of the 
     *  resource.</p>
     */
    public static final String date = new String( "http://purl.org/dc/terms/date" );
    
    /** <p>Date of acceptance of the resource.</p> */
    public static final String dateAccepted = new String( "http://purl.org/dc/terms/dateAccepted" );
    
    /** <p>Date of copyright.</p> */
    public static final String dateCopyrighted = new String( "http://purl.org/dc/terms/dateCopyrighted" );
    
    /** <p>Date of submission of the resource.</p> */
    public static final String dateSubmitted = new String( "http://purl.org/dc/terms/dateSubmitted" );
    
    /** <p>An account of the resource.</p> */
    public static final String description = new String( "http://purl.org/dc/terms/description" );
    
    /** <p>A class of entity, defined in terms of progression through an educational 
     *  or training context, for which the described resource is intended.</p>
     */
    public static final String educationLevel = new String( "http://purl.org/dc/terms/educationLevel" );
    
    /** <p>The size or duration of the resource.</p> */
    public static final String extent = new String( "http://purl.org/dc/terms/extent" );
    
    /** <p>The file format, physical medium, or dimensions of the resource.</p> */
    public static final String format = new String( "http://purl.org/dc/terms/format" );
    
    /** <p>A related resource that is substantially the same as the pre-existing described 
     *  resource, but in another format.</p>
     */
    public static final String hasFormat = new String( "http://purl.org/dc/terms/hasFormat" );
    
    /** <p>A related resource that is included either physically or logically in the 
     *  described resource.</p>
     */
    public static final String hasPart = new String( "http://purl.org/dc/terms/hasPart" );
    
    /** <p>A related resource that is a version, edition, or adaptation of the described 
     *  resource.</p>
     */
    public static final String hasVersion = new String( "http://purl.org/dc/terms/hasVersion" );
    
    /** <p>An unambiguous reference to the resource within a given context.</p> */
    public static final String identifier = new String( "http://purl.org/dc/terms/identifier" );
    
    /** <p>A process, used to engender knowledge, attitudes and skills, that the described 
     *  resource is designed to support.</p>
     */
    public static final String instructionalMethod = new String( "http://purl.org/dc/terms/instructionalMethod" );
    
    /** <p>A related resource that is substantially the same as the described resource, 
     *  but in another format.</p>
     */
    public static final String isFormatOf = new String( "http://purl.org/dc/terms/isFormatOf" );
    
    /** <p>A related resource in which the described resource is physically or logically 
     *  included.</p>
     */
    public static final String isPartOf = new String( "http://purl.org/dc/terms/isPartOf" );
    
    /** <p>A related resource that references, cites, or otherwise points to the described 
     *  resource.</p>
     */
    public static final String isReferencedBy = new String( "http://purl.org/dc/terms/isReferencedBy" );
    
    /** <p>A related resource that supplants, displaces, or supersedes the described 
     *  resource.</p>
     */
    public static final String isReplacedBy = new String( "http://purl.org/dc/terms/isReplacedBy" );
    
    /** <p>A related resource that requires the described resource to support its function, 
     *  delivery, or coherence.</p>
     */
    public static final String isRequiredBy = new String( "http://purl.org/dc/terms/isRequiredBy" );
    
    /** <p>A related resource of which the described resource is a version, edition, 
     *  or adaptation.</p>
     */
    public static final String isVersionOf = new String( "http://purl.org/dc/terms/isVersionOf" );
    
    /** <p>Date of formal issuance (e.g., publication) of the resource.</p> */
    public static final String issued = new String( "http://purl.org/dc/terms/issued" );
    
    /** <p>A language of the resource.</p> */
    public static final String language = new String( "http://purl.org/dc/terms/language" );
    
    /** <p>A legal document giving official permission to do something with the resource.</p> */
    public static final String license = new String( "http://purl.org/dc/terms/license" );
    
    /** <p>An entity that mediates access to the resource and for whom the resource is 
     *  intended or useful.</p>
     */
    public static final String mediator = new String( "http://purl.org/dc/terms/mediator" );
    
    /** <p>The material or physical carrier of the resource.</p> */
    public static final String medium = new String( "http://purl.org/dc/terms/medium" );
    
    /** <p>Date on which the resource was changed.</p> */
    public static final String modified = new String( "http://purl.org/dc/terms/modified" );
    
    /** <p>A statement of any changes in ownership and custody of the resource since 
     *  its creation that are significant for its authenticity, integrity, and interpretation.</p>
     */
    public static final String provenance = new String( "http://purl.org/dc/terms/provenance" );
    
    /** <p>An entity responsible for making the resource available.</p> */
    public static final String publisher = new String( "http://purl.org/dc/terms/publisher" );
    
    /** <p>A related resource that is referenced, cited, or otherwise pointed to by the 
     *  described resource.</p>
     */
    public static final String references = new String( "http://purl.org/dc/terms/references" );
    
    /** <p>A related resource.</p> */
    public static final String relation = new String( "http://purl.org/dc/terms/relation" );
    
    /** <p>A related resource that is supplanted, displaced, or superseded by the described 
     *  resource.</p>
     */
    public static final String replaces = new String( "http://purl.org/dc/terms/replaces" );
    
    /** <p>A related resource that is required by the described resource to support its 
     *  function, delivery, or coherence.</p>
     */
    public static final String requires = new String( "http://purl.org/dc/terms/requires" );
    
    /** <p>Information about rights held in and over the resource.</p> */
    public static final String rights = new String( "http://purl.org/dc/terms/rights" );
    
    /** <p>A person or organization owning or managing rights over the resource.</p> */
    public static final String rightsHolder = new String( "http://purl.org/dc/terms/rightsHolder" );
    
    /** <p>A related resource from which the described resource is derived.</p> */
    public static final String source = new String( "http://purl.org/dc/terms/source" );
    
    /** <p>Spatial characteristics of the resource.</p> */
    public static final String spatial = new String( "http://purl.org/dc/terms/spatial" );
    
    /** <p>The topic of the resource.</p> */
    public static final String subject = new String( "http://purl.org/dc/terms/subject" );
    
    /** <p>A list of subunits of the resource.</p> */
    public static final String tableOfContents = new String( "http://purl.org/dc/terms/tableOfContents" );
    
    /** <p>Temporal characteristics of the resource.</p> */
    public static final String temporal = new String( "http://purl.org/dc/terms/temporal" );
    
    public static final String title = new String( "http://purl.org/dc/terms/title" );
    
    /** <p>The nature or genre of the resource.</p> */
    public static final String type = new String( "http://purl.org/dc/terms/type" );
    
    /** <p>Date (often a range) of validity of a resource.</p> */
    public static final String valid = new String( "http://purl.org/dc/terms/valid" );
    
    /** <p>A resource that acts or has the power to act.</p> */
    public static final String Agent = new String( "http://purl.org/dc/terms/Agent" );
    
    /** <p>A group of agents.</p> */
    public static final String AgentClass = new String( "http://purl.org/dc/terms/AgentClass" );
    
    /** <p>A book, article, or other documentary resource.</p> */
    public static final String BibliographicResource = new String( "http://purl.org/dc/terms/BibliographicResource" );
    
    /** <p>A digital resource format.</p> */
    public static final String FileFormat = new String( "http://purl.org/dc/terms/FileFormat" );
    
    /** <p>A rate at which something recurs.</p> */
    public static final String Frequency = new String( "http://purl.org/dc/terms/Frequency" );
    
    /** <p>The extent or range of judicial, law enforcement, or other authority.</p> */
    public static final String Jurisdiction = new String( "http://purl.org/dc/terms/Jurisdiction" );
    
    /** <p>A legal document giving official permission to do something with a Resource.</p> */
    public static final String LicenseDocument = new String( "http://purl.org/dc/terms/LicenseDocument" );
    
    /** <p>A system of signs, symbols, sounds, gestures, or rules used in communication.</p> */
    public static final String LinguisticSystem = new String( "http://purl.org/dc/terms/LinguisticSystem" );
    
    /** <p>A spatial region or named place.</p> */
    public static final String Location = new String( "http://purl.org/dc/terms/Location" );
    
    /** <p>A location, period of time, or jurisdiction.</p> */
    public static final String LocationPeriodOrJurisdiction = new String( "http://purl.org/dc/terms/LocationPeriodOrJurisdiction" );
    
    /** <p>A file format or physical medium.</p> */
    public static final String MediaType = new String( "http://purl.org/dc/terms/MediaType" );
    
    /** <p>A media type or extent.</p> */
    public static final String MediaTypeOrExtent = new String( "http://purl.org/dc/terms/MediaTypeOrExtent" );
    
    /** <p>A method by which resources are added to a collection.</p> */
    public static final String MethodOfAccrual = new String( "http://purl.org/dc/terms/MethodOfAccrual" );
    
    /** <p>A process that is used to engender knowledge, attitudes, and skills.</p> */
    public static final String MethodOfInstruction = new String( "http://purl.org/dc/terms/MethodOfInstruction" );
    
    /** <p>An interval of time that is named or defined by its start and end dates.</p> */
    public static final String PeriodOfTime = new String( "http://purl.org/dc/terms/PeriodOfTime" );
    
    /** <p>A physical material or carrier.</p> */
    public static final String PhysicalMedium = new String( "http://purl.org/dc/terms/PhysicalMedium" );
    
    /** <p>A material thing.</p> */
    public static final String PhysicalResource = new String( "http://purl.org/dc/terms/PhysicalResource" );
    
    /** <p>A plan or course of action by an authority, intended to influence and determine 
     *  decisions, actions, and other matters.</p>
     */
    public static final String Policy = new String( "http://purl.org/dc/terms/Policy" );
    
    /** <p>A statement of any changes in ownership and custody of a resource since its 
     *  creation that are significant for its authenticity, integrity, and interpretation.</p>
     */
    public static final String ProvenanceStatement = new String( "http://purl.org/dc/terms/ProvenanceStatement" );
    
    /** <p>A statement about the intellectual property rights (IPR) held in or over a 
     *  Resource, a legal document giving official permission to do something with 
     *  a resource, or a statement about access rights.</p>
     */
    public static final String RightsStatement = new String( "http://purl.org/dc/terms/RightsStatement" );
    
    /** <p>A dimension or extent, or a time taken to play or execute.</p> */
    public static final String SizeOrDuration = new String( "http://purl.org/dc/terms/SizeOrDuration" );
    
    /** <p>A basis for comparison; a reference point against which other things can be 
     *  evaluated.</p>
     */
    public static final String Standard = new String( "http://purl.org/dc/terms/Standard" );
    
    

}
