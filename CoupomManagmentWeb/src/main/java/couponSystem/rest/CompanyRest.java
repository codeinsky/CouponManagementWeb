package couponSystem.rest;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import beans.Coupon;
import facades.CompanyFacadeF;



// TODO: Auto-generated Javadoc
/**
 * The Class CompanyRest.
 * COMPANY FACADE METHODS Rest: 
 * 1. Create Coupon - POST 
 * 2. Remove Coupon - DELETE
 * 3. Update Coupon - PUT 
 * 4. getCouponByID - GET{long id}
 * 5. GetAll Coupons - GET 
 * 6. Sort Coupons  - GET{String filter} 
 * 
 * 
 */
@CrossOrigin("*")
@RestController
public class CompanyRest {
	
	/** The company. */
	// need to pass ID from logIn to company 
	CompanyFacadeF company = new CompanyFacadeF(8); 
	
	/**
	 * Creates the coupon.
	 *
	 * @param coupon the coupon
	 */
	@RequestMapping (value="/company/createCoupon" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCoupon(@RequestBody Coupon coupon) {
		company.createCoupon(coupon);
	}
	
	/**
	 * Delete coupon.
	 *
	 * @param coupon the coupon
	 */
	@RequestMapping (value="/company/removeCoupon" , method = RequestMethod.DELETE , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCoupon(@RequestBody Coupon coupon) {
		company.removeCoupon(coupon);
	}
	
	/**
	 * Update coupon.
	 *
	 * @param coupon the coupon
	 */
	@RequestMapping (value = "/company/updateCoupon" , method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCoupon(@RequestBody Coupon coupon) {
		company.updateCoupon(coupon);
	}
	
	/**
	 * Gets the coupon by ID.
	 *
	 * @param id the id
	 * @return the coupon by ID
	 */
	@RequestMapping (value = "/company/getCouponById/{id}" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public Coupon getCouponByID (@PathVariable ("id") long id) {
		return company.getCouponById(id);
	}
	
	/**
	 * Gets the all coupons.
	 *
	 * @return the all coupons
	 */
	@RequestMapping (value="/company/getAllCoupons" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getAllCoupons(){
		return company.getAllCoupons();
	}
	
	/**
	 * Gets the filtered coupons.
	 *
	 * @param filter the filter
	 * @param refernce the reference
	 * @return the filtered coupons
	 */
	@RequestMapping (value="/company/sortCouponBy/{filter}/{reference}" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getFiltertedCoupons(@PathVariable ("filter" ) String filter  , 
			@PathVariable ("reference" ) String reference) {
		return company.sortCouponBy(filter, reference);
	}
	
	
}
