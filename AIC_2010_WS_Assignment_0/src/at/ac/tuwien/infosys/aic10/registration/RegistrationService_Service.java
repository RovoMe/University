
/*
 * 
 */

package at.ac.tuwien.infosys.aic10.registration;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.3
 * Wed Oct 13 08:09:03 CEST 2010
 * Generated source version: 2.2.3
 * 
 */


@WebServiceClient(name = "RegistrationService", 
                  wsdlLocation = "file:ressources/registration.wsdl",
                  targetNamespace = "http://infosys.tuwien.ac.at/aic10/registration") 
public class RegistrationService_Service extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://infosys.tuwien.ac.at/aic10/registration", "RegistrationService");
    public final static QName RegistrationServicePT = new QName("http://infosys.tuwien.ac.at/aic10/registration", "RegistrationServicePT");
    static {
        URL url = null;
        try {
            url = new URL("file:ressources/registration.wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:ressources/registration.wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public RegistrationService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public RegistrationService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RegistrationService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns RegistrationService
     */
    @WebEndpoint(name = "RegistrationServicePT")
    public RegistrationService getRegistrationServicePT() {
        return super.getPort(RegistrationServicePT, RegistrationService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns RegistrationService
     */
    @WebEndpoint(name = "RegistrationServicePT")
    public RegistrationService getRegistrationServicePT(WebServiceFeature... features) {
        return super.getPort(RegistrationServicePT, RegistrationService.class, features);
    }

}