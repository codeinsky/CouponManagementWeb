package couponSystem.rest;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import beans.Coupon;
import facades.CompanyFacadeF;



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
@Controller
public class CompanyRest {
	// need to pass ID from logIn to company 
	CompanyFacadeF company = new CompanyFacadeF(0); 
	@RequestMapping (value="/coupon" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCoupon(Coupon coupon) {
		company.createCoupon(coupon);
	}
	
	@RequestMapping (value="/coupon" , method = RequestMethod.DELETE , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCoupon(Coupon coupon) {
		company.removeCoupon(coupon);
	}
	
	@RequestMapping (value = "/coupon" , method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCoupon(Coupon coupon) {
		company.updateCoupon(coupon);
	}
	@RequestMapping (value = "/coupon/{id}" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public Coupon getCouponByID (@PathVariable ("id") long id) {
		return company.getCouponById(id);
	}
	@RequestMapping (value="/coupon" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getAllCopoupons(){
		return company.getAllCoupons();
	}
	@RequestMapping (value="/coupons/{filter}/{refernce}" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> getFiltertedCoupons(@PathVariable ("filter" , "refernce") String filter  ,  String refernce) {
		return company.sortCouponBy(filter, refernece);
	}
	
	
}
