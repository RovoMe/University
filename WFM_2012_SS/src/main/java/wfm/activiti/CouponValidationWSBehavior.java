package wfm.activiti;

import java.util.StringTokenizer;
import javax.ws.rs.WebApplicationException;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.el.Expression;
import org.activiti.engine.impl.el.FixedValue;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;
import wfm.ws.couponValidation.CouponValidationService;

public class CouponValidationWSBehavior implements JavaDelegate
{
	// These fields correspond to the name of the <activiti:field /> elements
	// in the bpmn
	private FixedValue service;
	private Expression hash;
	private static Logger logger = Logger.getLogger(CouponValidationWSBehavior.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception
	{
		String hashString = "";
		
		// connect to the SOAP AddressValidationService to check if the entered address is valid
		String serviceString = (String)service.getValue(execution);		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInFaultInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(CouponValidationService.class);
		factory.setAddress(serviceString);
		CouponValidationService couponValidationService = (CouponValidationService)factory.create();
		
		// retrieve input-values from the fields
		if (this.hash != null) 
		{
			StringTokenizer st = new StringTokenizer ((String)this.hash.getValue(execution), ",");
			while (st.hasMoreTokens()) 
			{
				String token = st.nextToken().trim();
				hashString = token;
			}
		}
		
		try
		{
			double couponValue = couponValidationService.validateCoupon(hashString);
			logger.info("Coupon-Value: " + couponValue);
			execution.setVariable("couponValue", couponValue);
		}
		catch (WebApplicationException waEx)
		{
			logger.error(waEx.getClass().getName()+": "+waEx.getResponse().getEntity());
			execution.setVariable("couponValue", 0.0);
		}
	}
}
