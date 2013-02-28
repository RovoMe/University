package wfm.ws.couponValidation;

import java.util.HashMap;
import java.util.Map;

public class CouponValidationServiceImpl implements CouponValidationService 
{
	private Map<String, Double> coupons;
	
	public CouponValidationServiceImpl()
	{
		this.coupons = new HashMap<String, Double>();
		this.coupons.put("abcd", 20.0);
		this.coupons.put("qwert", 10.0);
		this.coupons.put("yxcv", 5.0);
	}
	
	@Override
	public Double validateCoupon(String hash)
	{
		Double ret = this.coupons.get(hash);
		if (ret != null)
			return ret;
		return 0.;
	}
}
