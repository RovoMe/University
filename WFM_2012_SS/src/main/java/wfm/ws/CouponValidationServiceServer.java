package wfm.ws;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import wfm.ws.couponValidation.CouponValidationServiceImpl;

public class CouponValidationServiceServer 
{
	private Server soapServer = null;
	
	public void start()
	{
		// SOAP-Service
		JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
		svrFactory.setServiceClass(CouponValidationServiceImpl.class);
        svrFactory.setAddress("http://localhost:63081/CouponValidationService");
        svrFactory.getInInterceptors().add(new LoggingInInterceptor());
        svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
        
        this.soapServer = svrFactory.create();        
        this.soapServer.start();
	}
	
	public void stop()
	{
		if (this.soapServer != null)
			this.soapServer.stop();
	}
	
	public static void main(String[] args)
	{
		new CouponValidationServiceServer().start();
	}
}
