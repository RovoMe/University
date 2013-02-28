package wfm.ws.addressValidation.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import wfm.bean.Customer;

/**
 * <p>
 * REST (Representational State Transfer) Web-Service for validating addresses.
 * Basic infos may be found at:
 * http://java.sun.com/developer/technicalArticles/WebServices/jax-rs/index.html
 * http://java.dzone.com/articles/putting-java-rest
 * </p>
 * <p>
 * Example-URI:<br />
 * http://localhost:63082/addressValidation/validateAddress?firstName=Max&
 * lastName=Muster&....
 * </p>
 * 
 * <pre>
 * HTTP-Methods (last ones rarely used):
 * GET         safe       No defined body semantics
 * POST                   body supported
 * PUT                    body supported
 * DELETE                 No defined body semantics
 * HEAD        safe       NO BODY!
 * PATCH                  NO BODY!
 * OPTIONS                No defined body semantics
 * </pre>
 * 
 * http://stackoverflow.com/questions/299628/is-an-entity-body-allowed-for-an-
 * http-delete-request
 * 
 * @author Roman Vottner
 */

@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_HTML, MediaType.APPLICATION_XHTML_XML, MediaType.TEXT_PLAIN, MediaType.WILDCARD})
@Path("/addressValidation")
public interface AddressValidationRESTService
{
	@GET
	@Path("validateAddress")
	public Customer validateAddress(@QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName,
			@QueryParam("street") String street,
			@QueryParam("city") String city,
			@QueryParam("zip") String zip,
			@QueryParam("country") String country,
			@QueryParam("telNo") @DefaultValue("") String telNo,
			@QueryParam("mailAddress") String mailAddress) throws WebApplicationException;
}
