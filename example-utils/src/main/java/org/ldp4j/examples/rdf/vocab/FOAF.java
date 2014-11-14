package org.ldp4j.examples.rdf.vocab;


/**
 * String constants for the vocabulary defined in http://xmlns.com/foaf/spec/
 */
public class FOAF {
	
    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://xmlns.com/foaf/0.1/";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>A jabber ID for something.</p> */
    public static final String jabberID = new String( "http://xmlns.com/foaf/0.1/jabberID" );
    
    /** <p>A short informal nickname characterising an agent (includes login identifiers, 
     *  IRC and other chat nicknames).</p>
     */
    public static final String nick = new String( "http://xmlns.com/foaf/0.1/nick" );
    
    /** <p>A checksum for the DNA of some thing. Joke.</p> */
    public static final String dnaChecksum = new String( "http://xmlns.com/foaf/0.1/dnaChecksum" );
    
    /** <p>A topic of some page or document.</p> */
    public static final String topic = new String( "http://xmlns.com/foaf/0.1/topic" );
    
    /** <p>A theme.</p> */
    public static final String theme = new String( "http://xmlns.com/foaf/0.1/theme" );
    
    /** <p>An MSN chat ID</p> */
    public static final String msnChatID = new String( "http://xmlns.com/foaf/0.1/msnChatID" );
    
    /** <p>An OpenID for an Agent.</p> */
    public static final String openid = new String( "http://xmlns.com/foaf/0.1/openid" );
    
    /** <p>The family_name of some person.</p> */
    public static final String family_name = new String( "http://xmlns.com/foaf/0.1/family_name" );
    
    /** <p>A homepage of a school attended by the person.</p> */
    public static final String schoolHomepage = new String( "http://xmlns.com/foaf/0.1/schoolHomepage" );
    
    /** <p>A project this person has previously worked on.</p> */
    public static final String pastProject = new String( "http://xmlns.com/foaf/0.1/pastProject" );
    
    /** <p>A .plan comment, in the tradition of finger and '.plan' files.</p> */
    public static final String plan = new String( "http://xmlns.com/foaf/0.1/plan" );
    
    /** <p>A Myers Briggs (MBTI) personality classification.</p> */
    public static final String myersBriggs = new String( "http://xmlns.com/foaf/0.1/myersBriggs" );
    
    /** <p>A personal mailbox, ie. an Internet mailbox associated with exactly one owner, 
     *  the first owner of this mailbox. This is a 'static inverse functional property', 
     *  in that there is (across time and change) at most one individual that ever 
     *  has any particular value for foaf:mbox.</p>
     */
    public static final String mbox = new String( "http://xmlns.com/foaf/0.1/mbox" );
    
    /** <p>The gender of this Agent (typically but not necessarily 'male' or 'female').</p> */
    public static final String gender = new String( "http://xmlns.com/foaf/0.1/gender" );
    
    /** <p>A sha1sum hash, in hex.</p> */
    public static final String sha1 = new String( "http://xmlns.com/foaf/0.1/sha1" );
    
    /** <p>A link to the publications of this person.</p> */
    public static final String publications = new String( "http://xmlns.com/foaf/0.1/publications" );
    
    /** <p>Indicates an account held by this agent.</p> */
    public static final String holdsAccount = new String( "http://xmlns.com/foaf/0.1/holdsAccount" );
    
    /** <p>A current project this person works on.</p> */
    public static final String currentProject = new String( "http://xmlns.com/foaf/0.1/currentProject" );
    
    /** <p>A work info homepage of some person; a page about their work for some organization.</p> */
    public static final String workInfoHomepage = new String( "http://xmlns.com/foaf/0.1/workInfoHomepage" );
    
    /** <p>Something that was made by this agent.</p> */
    public static final String made = new String( "http://xmlns.com/foaf/0.1/made" );
    
    /** <p>An AIM chat ID</p> */
    public static final String aimChatID = new String( "http://xmlns.com/foaf/0.1/aimChatID" );
    
    /** <p>The primary topic of some page or document.</p> */
    public static final String primaryTopic = new String( "http://xmlns.com/foaf/0.1/primaryTopic" );
    
    /** <p>A derived thumbnail image.</p> */
    public static final String thumbnail = new String( "http://xmlns.com/foaf/0.1/thumbnail" );
    
    /** <p>A location that something is based near, for some broadly human notion of 
     *  near.</p>
     */
    public static final String based_near = new String( "http://xmlns.com/foaf/0.1/based_near" );
    
    /** <p>A workplace homepage of some person; the homepage of an organization they 
     *  work for.</p>
     */
    public static final String workplaceHomepage = new String( "http://xmlns.com/foaf/0.1/workplaceHomepage" );
    
    /** <p>Title (Mr, Mrs, Ms, Dr. etc)</p> */
    public static final String title = new String( "http://xmlns.com/foaf/0.1/title" );
    
    /** <p>A weblog of some thing (whether person, group, company etc.).</p> */
    public static final String weblog = new String( "http://xmlns.com/foaf/0.1/weblog" );
    
    /** <p>A logo representing some thing.</p> */
    public static final String logo = new String( "http://xmlns.com/foaf/0.1/logo" );
    
    /** <p>An organization funding a project or person.</p> */
    public static final String fundedBy = new String( "http://xmlns.com/foaf/0.1/fundedBy" );
    
    /** <p>A depiction of some thing.</p> */
    public static final String depiction = new String( "http://xmlns.com/foaf/0.1/depiction" );
    
    /** <p>Indicates a homepage of the service provide for this online account.</p> */
    public static final String accountServiceHomepage = new String( "http://xmlns.com/foaf/0.1/accountServiceHomepage" );
    
    /** <p>A page or document about this thing.</p> */
    public static final String page = new String( "http://xmlns.com/foaf/0.1/page" );
    
    /** <p>A document that this thing is the primary topic of.</p> */
    public static final String isPrimaryTopicOf = new String( "http://xmlns.com/foaf/0.1/isPrimaryTopicOf" );
    
    /** <p>The surname of some person.</p> */
    public static final String surname = new String( "http://xmlns.com/foaf/0.1/surname" );
    
    /** <p>The first name of a person.</p> */
    public static final String firstName = new String( "http://xmlns.com/foaf/0.1/firstName" );
    
    /** <p>A homepage for some thing.</p> */
    public static final String homepage = new String( "http://xmlns.com/foaf/0.1/homepage" );
    
    /** <p>A person known by this person (indicating some level of reciprocated interaction 
     *  between the parties).</p>
     */
    public static final String knows = new String( "http://xmlns.com/foaf/0.1/knows" );
    
    /** <p>A thing depicted in this representation.</p> */
    public static final String depicts = new String( "http://xmlns.com/foaf/0.1/depicts" );
    
    /** <p>A page about a topic of interest to this person.</p> */
    public static final String interest = new String( "http://xmlns.com/foaf/0.1/interest" );
    
    /** <p>A textual geekcode for this person, see http://www.geekcode.com/geek.html</p> */
    public static final String geekcode = new String( "http://xmlns.com/foaf/0.1/geekcode" );
    
    /** <p>The sha1sum of the URI of an Internet mailbox associated with exactly one 
     *  owner, the first owner of the mailbox.</p>
     */
    public static final String mbox_sha1sum = new String( "http://xmlns.com/foaf/0.1/mbox_sha1sum" );
    
    /** <p>Indicates the name (identifier) associated with this online account.</p> */
    public static final String accountName = new String( "http://xmlns.com/foaf/0.1/accountName" );
    
    /** <p>Indicates the class of individuals that are a member of a Group</p> */
    public static final String membershipClass = new String( "http://xmlns.com/foaf/0.1/membershipClass" );
    
    /** <p>A tipjar document for this agent, describing means for payment and reward.</p> */
    public static final String tipjar = new String( "http://xmlns.com/foaf/0.1/tipjar" );
    
    /** <p>An agent that made this thing.</p> */
    public static final String maker = new String( "http://xmlns.com/foaf/0.1/maker" );
    
    /** <p>A name for some thing.</p> */
    public static final String name = new String( "http://xmlns.com/foaf/0.1/name" );
    
    /** <p>An image that can be used to represent some thing (ie. those depictions which 
     *  are particularly representative of something, eg. one's photo on a homepage).</p>
     */
    public static final String img = new String( "http://xmlns.com/foaf/0.1/img" );
    
    /** <p>The birthday of this Agent, represented in mm-dd string form, eg. '12-31'.</p> */
    public static final String birthday = new String( "http://xmlns.com/foaf/0.1/birthday" );
    
    /** <p>The given name of some person.</p> */
    public static final String givenname = new String( "http://xmlns.com/foaf/0.1/givenname" );
    
    /** <p>A Yahoo chat ID</p> */
    public static final String yahooChatID = new String( "http://xmlns.com/foaf/0.1/yahooChatID" );
    
    /** <p>Indicates a member of a Group</p> */
    public static final String member = new String( "http://xmlns.com/foaf/0.1/member" );
    
    /** <p>An ICQ chat ID</p> */
    public static final String icqChatID = new String( "http://xmlns.com/foaf/0.1/icqChatID" );
    
    /** <p>A phone, specified using fully qualified tel: URI scheme (refs: http://www.w3.org/Addressing/schemes.html#tel).</p> */
    public static final String phone = new String( "http://xmlns.com/foaf/0.1/phone" );
    
    /** <p>A thing of interest to this person.</p> */
    public static final String topic_interest = new String( "http://xmlns.com/foaf/0.1/topic_interest" );
    
    /** <p>A personal profile RDF document.</p> */
    public static final String PersonalProfileDocument = new String( "http://xmlns.com/foaf/0.1/PersonalProfileDocument" );
    
    /** <p>An online gaming account.</p> */
    public static final String OnlineGamingAccount = new String( "http://xmlns.com/foaf/0.1/OnlineGamingAccount" );
    
    /** <p>An agent (eg. person, group, software or physical artifact).</p> */
    public static final String Agent = new String( "http://xmlns.com/foaf/0.1/Agent" );
    
    /** <p>An image.</p> */
    public static final String Image = new String( "http://xmlns.com/foaf/0.1/Image" );
    
    /** <p>A project (a collective endeavour of some kind).</p> */
    public static final String Project = new String( "http://xmlns.com/foaf/0.1/Project" );
    
    /** <p>An online chat account.</p> */
    public static final String OnlineChatAccount = new String( "http://xmlns.com/foaf/0.1/OnlineChatAccount" );
    
    /** <p>An organization.</p> */
    public static final String Organization = new String( "http://xmlns.com/foaf/0.1/Organization" );
    
    /** <p>A class of Agents.</p> */
    public static final String Group = new String( "http://xmlns.com/foaf/0.1/Group" );
    
    /** <p>A person.</p> */
    public static final String Person = new String( "http://xmlns.com/foaf/0.1/Person" );
    
    /** <p>An online account.</p> */
    public static final String OnlineAccount = new String( "http://xmlns.com/foaf/0.1/OnlineAccount" );
    
    /** <p>An online e-commerce account.</p> */
    public static final String OnlineEcommerceAccount = new String( "http://xmlns.com/foaf/0.1/OnlineEcommerceAccount" );
    
    /** <p>A document.</p> */
    public static final String Document = new String( "http://xmlns.com/foaf/0.1/Document" );
    
	
	

}
