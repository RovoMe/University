package at.ac.tuwien.infosys.aic10.registration;

//import java.io.ByteArrayOutputStream;
//import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Source;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.w3c.dom.Document;
//import org.w3c.dom.Node;


public class Main
{
	public static void main(String args[])
	{
		new Main();
	}
	
	public Main()
	{
		this.registerShortVersion();
		this.registerMidVersion();
		this.registerLongVersion();
		this.registerWithJAXB();
		this.registerWithXCF();
	}
	
	private void registerWithJAXB()
	{
		try
		{		
			// JAXBContext needs the path to the files generated with jxc, 
			// which translated the schema into java-files
			JAXBContext context = JAXBContext.newInstance("at.ac.tuwien.infosys.aic10.registration");

			// The URL defining the description of the webservice
			URL url = new URL("http://stockholm.vitalab.tuwien.ac.at:8000/registration");

			// Create the registration data we want to provide to the service
			ObjectFactory factory = new ObjectFactory();
			RegisterStudent regStudent = factory.createRegisterStudent();
			regStudent.setLastName("Vottner");
			regStudent.setPassword("egadnewa");
			regStudent.setProgramNumber("066 926");
			regStudent.setStudentNumber("0105011");
			
			// A marshaller marshalls the object into xml
			Marshaller marshaller = context.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, new Boolean(false));
 			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));
			
//			StringWriter stringWriter = new StringWriter();
//			marshaller.marshal(regStudent, stringWriter);
//			String soapBody = stringWriter.toString();
//			System.out.println(soapBody);
//			System.out.println();
//			stringWriter.close();
			   
			//Create instance of DocumentBuilderFactory
		    DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		    //Get the DocumentBuilder
		    DocumentBuilder parser = domFactory.newDocumentBuilder();
		    //Create blank DOM Document
		    Document doc = parser.newDocument();
		    // and fill it with the data our registration
			marshaller.marshal(regStudent, doc);
			
			// Create the SOAP-Message
			SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
			MimeHeaders headers = soapMessage.getMimeHeaders();
			headers.addHeader("SOAPAction", "urn:requiredSOAPAction");
			SOAPBody body = soapMessage.getSOAPBody();
			body.addDocument(doc);

			// Print the SOAPMessage to the console
//			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//			soapMessage.writeTo( outputStream );
//			byte[] byteArray = outputStream.toByteArray();
//			String soapMsg = new String( byteArray, "UTF-8");
//			System.out.println(soapMsg);
			
			SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection conn = soapConnFactory.createConnection();
			SOAPMessage reply = conn.call(soapMessage, url);
			conn.close();
//	        System.out.println("\nRESPONSE:\n");
	        
			//Create the transformer
//	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//	        Transformer transformer = transformerFactory.newTransformer();
//	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//	        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
//	        //Extract the content of the reply
//	        Source sourceContent = reply.getSOAPPart().getContent();
//	        //Set the output for the transformation
//	        transformer.transform(sourceContent, new StreamResult(System.out));
	        System.out.println();
	        Unmarshaller unmarshaller = context.createUnmarshaller();

	        // Unmarshal the reply and extract userfriendly info
	        JAXBElement<RegisterStudentResponse> type = unmarshaller.unmarshal(
	        		reply.getSOAPBody().getFirstChild(), RegisterStudentResponse.class);
	        RegisterStudentResponse response = type.getValue();
	        RegisterStudentResult result = response.getSuccess();
			System.out.println("Success: "+result.isSuccess());
			System.out.println("Message: "+result.getMessage());

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Registers for the assignments on the course "Advanced Internet Application" 2010
	 * short version
	 */
	private void registerShortVersion()
	{
		RegistrationService_Service service = new RegistrationService_Service();
		try
		{
			RegisterStudentResult result = service.getRegistrationServicePT().
					registerStudent("0105011", "Vottner", "066 926", "egadnewa");
			System.out.println("Success: "+result.isSuccess());
			System.out.println("Message: "+result.getMessage());
		}
		catch (RegistrationException e)
		{
			System.out.println("Registration failed due to an exception: "+
					e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Registers for the assignments on the course "Advanced Internet Application" 2010
	 * mid version
	 */
	private void registerMidVersion()
	{
		RegistrationService_Service service = new RegistrationService_Service();
		RegistrationService regService = service.getPort(
				RegistrationService_Service.RegistrationServicePT, RegistrationService.class);
		
		try
		{
			RegisterStudentResult result = regService.
					registerStudent("0105011", "Vottner", "066 926", "egadnewa");
			System.out.println("Success: "+result.isSuccess());
			System.out.println("Message: "+result.getMessage());
		}
		catch (RegistrationException e)
		{
			System.out.println("Registration failed due to an exception: "+e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Registers for the assignments on the course "Advanced Internet Application" 2010
	 * long version
	 */
	private void registerLongVersion()
	{
		try
		{
			QName regName = new QName("http://infosys.tuwien.ac.at/aic10/registration", 
					"RegistrationService");
			QName regPort = new QName("http://infosys.tuwien.ac.at/aic10/registration", 
					"RegistrationServicePT");

			URL url = new URL("http://stockholm.vitalab.tuwien.ac.at:8000/registration?wsdl");
			Service service = RegistrationService_Service.create(url, regName);
			service.addPort(regPort, SOAPBinding.SOAP11HTTP_BINDING, 
					"http://stockholm.vitalab.tuwien.ac.at:8000/registration");
			RegistrationService regService = (RegistrationService)
					service.getPort(regPort, RegistrationService.class);
			try
			{
				RegisterStudentResult result = regService.
						registerStudent("0105011", "Vottner", "066 926", "egadnewa");
				System.out.println("Success: "+result.isSuccess());
				System.out.println("Message: "+result.getMessage());
			}
			catch (RegistrationException e)
			{
				System.out.println("Registration failed due to an exception: "+
						e.getLocalizedMessage());
				e.printStackTrace();
			}
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Registers for the assignments on the course "Advanced Internet Application" 2010
	 * with Apache XCF
	 */
	private void registerWithXCF()
	{	
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setAddress("http://stockholm.vitalab.tuwien.ac.at:8000/registration");
		factory.setServiceClass(RegistrationService.class);
		RegistrationService regService = (RegistrationService)factory.create();

		try
		{
			RegisterStudentResult result = regService.
					registerStudent("0105011", "Vottner", "066 926", "egadnewa");
			System.out.println("Success: "+result.isSuccess());
			System.out.println("Message: "+result.getMessage());
		}
		catch (RegistrationException e)
		{
			System.out.println("Registration failed due to an exception: "+
					e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}
