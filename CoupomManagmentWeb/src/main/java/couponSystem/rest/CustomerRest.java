package couponSystem.rest;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import beans.Coupon;
import facades.CustomerFacadeF;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerRest.
 */
@RestController 
public class CustomerRest {
	
	/** The customer. */
	CustomerFacadeF customer = new CustomerFacadeF(5);
	
	/**
	 * Purchase coupon.
	 *
	 * @param coupon the coupon
	 */
	@RequestMapping(value="/customer/purchaseCoupon" , method= RequestMethod.POST) 
	public void purchaseCoupon(@RequestBody Coupon coupon) {
		customer.purchaseCoupon(coupon);
	}
	
	/**
	 * Gets the my coupons.
	 *
	 * @return the my coupons
	 */
	@RequestMapping(value="/customer/getAllMyCoupons" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getMyCoupons(){
		return customer.getAllMyCoupons();
	}
	
	/**
	 * Gets the coupons filtered.
	 *
	 * @param filter the filter
	 * @param refernce the reference
	 * @return the coupons filtered
	 */
	@RequestMapping(value="/customer/getMyCouponsSortedByType/{filter}/{reference}" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getCouponsFiltered(@PathVariable ("filter") String filter,@PathVariable  ("reference") String reference) {
		return customer.getMyCouponsSortedByType(filter , reference);
	}

}
