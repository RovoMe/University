
package at.ac.tuwien.infosys.aic10.registration;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the at.ac.tuwien.infosys.aic10.registration package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RegisterStudentResponse_QNAME = new QName("http://infosys.tuwien.ac.at/aic10/registration", "register_studentResponse");
    private final static QName _RegisterStudent_QNAME = new QName("http://infosys.tuwien.ac.at/aic10/registration", "register_student");
    private final static QName _RegistrationFault_QNAME = new QName("http://infosys.tuwien.ac.at/aic10/registration", "RegistrationFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: at.ac.tuwien.infosys.aic10.registration
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegisterStudentResult }
     * 
     */
    public RegisterStudentResult createRegisterStudentResult() {
        return new RegisterStudentResult();
    }

    /**
     * Create an instance of {@link RegistrationFault }
     * 
     */
    public RegistrationFault createRegistrationFault() {
        return new RegistrationFault();
    }

    /**
     * Create an instance of {@link RegisterStudentResponse }
     * 
     */
    public RegisterStudentResponse createRegisterStudentResponse() {
        return new RegisterStudentResponse();
    }

    /**
     * Create an instance of {@link RegisterStudent }
     * 
     */
    public RegisterStudent createRegisterStudent() {
        return new RegisterStudent();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterStudentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://infosys.tuwien.ac.at/aic10/registration", name = "register_studentResponse")
    public JAXBElement<RegisterStudentResponse> createRegisterStudentResponse(RegisterStudentResponse value) {
        return new JAXBElement<RegisterStudentResponse>(_RegisterStudentResponse_QNAME, RegisterStudentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterStudent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://infosys.tuwien.ac.at/aic10/registration", name = "register_student")
    public JAXBElement<RegisterStudent> createRegisterStudent(RegisterStudent value) {
        return new JAXBElement<RegisterStudent>(_RegisterStudent_QNAME, RegisterStudent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrationFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://infosys.tuwien.ac.at/aic10/registration", name = "RegistrationFault")
    public JAXBElement<RegistrationFault> createRegistrationFault(RegistrationFault value) {
        return new JAXBElement<RegistrationFault>(_RegistrationFault_QNAME, RegistrationFault.class, null, value);
    }

}
