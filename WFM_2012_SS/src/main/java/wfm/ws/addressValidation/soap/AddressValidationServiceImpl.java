package wfm.ws.addressValidation.soap;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import wfm.bean.Address;
import wfm.bean.Customer;

//http://cxf.apache.org/docs/developing-a-service.html#DevelopingaService-RequiredAnnotations
@WebService(endpointInterface="at.rovo.addressValidation.AddressValidationService",  
		serviceName="AddressValidationService", 
		targetNamespace="http://www.tuwien.ac.at/wfm2012/addressValidation", 
		name="AddressValidationPT") // wird nach portType gemapped
public class AddressValidationServiceImpl implements AddressValidationService
{
	private List<Address> knownAddresses = new ArrayList<Address>();
	
	public AddressValidationServiceImpl()
	{
		this.knownAddresses.add(new Address("Teststraße 1", "Wien", "1010", "Austria"));
		this.knownAddresses.add(new Address("Mustergasse 2", "Wien", "1090", "Austria"));
		this.knownAddresses.add(new Address("Karlsplatz", "Wien", "1040", "Austria"));
		this.knownAddresses.add(new Address("Getreidegasse 34", "Salzburg", "5020", "Austria"));
		this.knownAddresses.add(new Address("Bürgergasse 14", "Graz", "8010", "Austria"));
		this.knownAddresses.add(new Address("Behrenstraße 37", "Berlin", "10117", "Germany"));
	}
	
	@Override
	public Customer validateAddress(String firstName, String lastName, String street, 
			String city, String zip, String country, String telNo, String mailAddress) throws AddressNotFoundFault
	{
		Customer customer = new Customer(firstName, lastName, street, city, zip, country, telNo, mailAddress);
		
		for (Address address : this.knownAddresses)
		{
			if (address.getStreet().equals(street) &&
				address.getCity().equals(city) &&
				address.getZip().equals(zip) &&
				address.getCountry().equals(country))
			{
				return customer;
			}
		}
		
		// no address could be found - throw an exception
		throw new AddressNotFoundFault("Address seems not to be valid!");
	}
	
	@Override
	public Customer validateAddressBean(Customer customer) throws AddressNotFoundFault
	{
		Address address = customer.getAddress();
		
		for (Address addr : this.knownAddresses)
		{
			if (addr.getStreet().equals(address.getStreet()) &&
				addr.getCity().equals(address.getCity()) && 
				addr.getZip().equals(address.getZip()) &&
				addr.getCountry().equals(address.getCountry()))
			{
				return customer;
			}
		}
		
		// no address could be found - throw an exception
		throw new AddressNotFoundFault("Address seems not to be valid!");
	}
}