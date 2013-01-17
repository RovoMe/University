
package at.ac.tuwien.infosys.aic10.registration;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.2.3
 * Wed Oct 13 08:09:03 CEST 2010
 * Generated source version: 2.2.3
 * 
 */

@WebFault(name = "RegistrationFault", targetNamespace = "http://infosys.tuwien.ac.at/aic10/registration")
public class RegistrationException extends Exception {
    public static final long serialVersionUID = 20101013080903L;
    
    private at.ac.tuwien.infosys.aic10.registration.RegistrationFault registrationFault;

    public RegistrationException() {
        super();
    }
    
    public RegistrationException(String message) {
        super(message);
    }
    
    public RegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrationException(String message, at.ac.tuwien.infosys.aic10.registration.RegistrationFault registrationFault) {
        super(message);
        this.registrationFault = registrationFault;
    }

    public RegistrationException(String message, at.ac.tuwien.infosys.aic10.registration.RegistrationFault registrationFault, Throwable cause) {
        super(message, cause);
        this.registrationFault = registrationFault;
    }

    public at.ac.tuwien.infosys.aic10.registration.RegistrationFault getFaultInfo() {
        return this.registrationFault;
    }
}