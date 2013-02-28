package wfm.ws.addressValidation.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import wfm.bean.Address;
import wfm.bean.Customer;

public class AddressValidationRESTServiceImpl implements AddressValidationRESTService
{
	private List<Address> knownAddresses = new ArrayList<Address>();
	
	public AddressValidationRESTServiceImpl()
	{
		this.knownAddresses.add(new Address("Teststraße 1", "Wien", "1010", "Austria"));
		this.knownAddresses.add(new Address("Mustergasse 2", "Wien", "1090", "Austria"));
		this.knownAddresses.add(new Address("Karlsplatz", "Wien", "1040", "Austria"));
		this.knownAddresses.add(new Address("Getreidegasse 34", "Salzburg", "5020", "Austria"));
		this.knownAddresses.add(new Address("Bürgergasse 14", "Graz", "8010", "Austria"));
		this.knownAddresses.add(new Address("Behrenstraße 37", "Berlin", "10117", "Germany"));
	}
	
	@Override
	public Customer validateAddress(String firstName, String lastName,
			String street, String city, String zip, String country,
			String telNo, String mailAddress)
	 throws WebApplicationException
	{				
		for (Address address : this.knownAddresses)
		{
			if (address.getStreet().equals(street) &&
				address.getCity().equals(city) &&
				address.getZip().equals(zip) &&
				address.getCountry().equals(country))
			{
				return new Customer(firstName, lastName, street, city, zip, country, telNo, mailAddress);
			}
		}
		ResponseBuilder builder = Response.status(Status.NOT_FOUND);
		builder.type("text/html");
		builder.entity("<h3>Invalid address! Address could not be found</h3>");

		throw new WebApplicationException(builder.build());
	}
}
