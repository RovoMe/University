
package at.ac.tuwien.infosys.aic10.registration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for register_studentResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="register_studentResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="success" type="{http://infosys.tuwien.ac.at/aic10/registration}registerStudentResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "register_studentResponse", propOrder = {
    "success"
})
public class RegisterStudentResponse {

    protected RegisterStudentResult success;

    /**
     * Gets the value of the success property.
     * 
     * @return
     *     possible object is
     *     {@link RegisterStudentResult }
     *     
     */
    public RegisterStudentResult getSuccess() {
        return success;
    }

    /**
     * Sets the value of the success property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegisterStudentResult }
     *     
     */
    public void setSuccess(RegisterStudentResult value) {
        this.success = value;
    }

}
