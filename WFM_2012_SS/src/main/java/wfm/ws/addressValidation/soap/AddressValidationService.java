package wfm.ws.addressValidation.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import wfm.bean.Customer;

@WebService(targetNamespace="http://www.tuwien.ac.at/wfm2012/addressValidation")
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL, parameterStyle=ParameterStyle.WRAPPED)
public interface AddressValidationService
{
	/**
	 * <p>Checks the validity of an address. If it is valid a customer object based on
	 * the inputs collected is returned. If the address is unknown an 
	 * {@link AddressNotFoundFault} is returned</p>
	 * 
	 * @param firstName First name of the customer
	 * @param lastName Last name of the customer
	 * @param street Street the customer lives at
	 * @param city City the customer lives in
	 * @param zip Zip-code of the city-part the customer lives in
	 * @param country Country the customer lives in
	 * @param telNo Telephone number of the customer
	 * @return Returns the {@link Customer} object if the address of the customer is
	 * 		   valid
	 * @throws AddressNotFoundFault Is thrown if the customer's address is invalid
	 */
	@WebMethod(operationName="validateAddress", action="urn:ValidateAddress")
	public @WebResult(name="customer") Customer validateAddress(
										@WebParam(name="firstName") String firstName, 
										@WebParam(name="lastName") String lastName, 
										@WebParam(name="street") String street, 
										@WebParam(name="city") String city, 
										@WebParam(name="zip") String zip, 
										@WebParam(name="country") String country, 
										@WebParam(name="telNo") String telNo,
										@WebParam(name="mailAddress") String mailAddress) 
												throws AddressNotFoundFault;
	
	/**
	 * <p>Validates the address of a customer. If the address is valid the customer-object
	 * is returned, if not an {@link AddressNotFoundFault} is thrown</p>
	 * 
	 * @param customer {@link Customer} object which represents the customer
	 * @return Returns the {@link Customer} object if the address of the customer is
	 * 		   valid
	 * @throws AddressNotFoundFault Is thrown if the customer's address is invalid
	 */
	@WebMethod(operationName="validateAddressBean", action="urn:ValidateAddressBean")
	public @WebResult(name="customer") Customer 
		validateAddressBean(@WebParam(name="customer") Customer customer) 
				throws AddressNotFoundFault;
}
