package ws.impl;

import java.io.FileInputStream;
import java.util.UUID;
import javax.xml.soap.MessageFactory;
//import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
//import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.NodeList;

public class ShippingCallbackHelper extends Thread {
	
	public static void main(String arg[]) {
			new ShippingCallbackHelper(
					UUID.randomUUID().toString()
			);
	}
	
// ------------------------------------------------------------------------
	
	String shippingId;
	
	ShippingCallbackHelper(String shippingId) {
        super(shippingId);
        this.shippingId = shippingId;
        start();
    }
	
    public void run() {

    	try {
            sleep(10000);
        } catch(InterruptedException e) {
            // throw new RuntimeException(e);
        }
        
		String uri = "http://localhost:9080/OrderProcessService/OrderProcessPort";
		String fileName = "order.xml";
		
	     try {
	         
	         //First create the connection
	         SOAPConnectionFactory soapConnFactory = 
	                            SOAPConnectionFactory.newInstance();
	         SOAPConnection connection = 
	                             soapConnFactory.createConnection();
	         
	         //Next, create the actual message
	         MessageFactory messageFactory = MessageFactory.newInstance();
	         SOAPMessage message = messageFactory.createMessage();
	         
	         //Create objects for the message parts            
	         SOAPPart soapPart =     message.getSOAPPart();
	         //SOAPEnvelope envelope = soapPart.getEnvelope();
	         //SOAPBody body =         envelope.getBody();

	         //Populate the Message
	        StreamSource preppedMsgSrc = new StreamSource( 
	                 new FileInputStream(fileName));
	        soapPart.setContent(preppedMsgSrc);
	        
	        NodeList nodeList = soapPart.getElementsByTagName("ord:shipping_id");
	        int length = nodeList.getLength();
	        for(int i=0; i<length; i++) {
	        	nodeList.item(i).setTextContent(shippingId);	        	
	        }
	        
	         //Save the message
	         message.saveChanges();

	       //Check the input
	         System.out.println("\nREQUEST:\n");
	         message.writeTo(System.out);
	         System.out.println();

	        //Send the message and get a reply   
	            
	        //Send the message
	        SOAPMessage reply = connection.call(message, uri);

	        //Check the output
	        System.out.println("\nRESPONSE:\n");
	        //Create the transformer
	        TransformerFactory transformerFactory = 
	                           TransformerFactory.newInstance();
	        Transformer transformer = 
	                        transformerFactory.newTransformer();
	        //Extract the content of the reply
	        Source sourceContent = reply.getSOAPPart().getContent();
	        //Set the output for the transformation
	        StreamResult result = new StreamResult(System.out);
	        transformer.transform(sourceContent, result);
	        System.out.println();


	        //Close the connection            
	         connection.close();
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
