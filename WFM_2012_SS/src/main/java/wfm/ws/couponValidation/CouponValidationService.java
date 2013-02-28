package wfm.ws.couponValidation;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL, parameterStyle=ParameterStyle.WRAPPED)
public interface CouponValidationService 
{
	/**
	 * <p>Validates a coupon-hash and returns the price reduction the coupon is valid for.
	 * If no coupon can be found for a certain coupon hash 0. will be returned</p>
	 * @param hash The string which represents a coupon
	 * @return The price amount the coupon is valid for, 0 if the coupon is invalid
	 */
	@WebMethod(operationName="validateCoupon", action="urn:ValidateCoupon")
	public @WebResult(name="priceReduction") Double validateCoupon(@WebParam(name="couponHash") String hash);
}
