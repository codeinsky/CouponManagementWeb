package facades;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;

import org.apache.derby.tools.sysinfo;

import beans.Coupon;
import couponSystemException.CouponSystemException;
import dao.CuoponDBDAO;
import dao.HelperMethodsDAO;


// TODO: Auto-generated Javadoc
/**
 * The Class CustomerFacadeF.
 */
public class CustomerFacadeF extends ClientCouponFacade{
	
	/** The customer logged. */
	long customerLogged;

	/**
	 * Instantiates a new customer facade F.
	 *
	 * @param customerLogged the customer logged
	 */
	// has a constructor for ID reference for customer activities
	public CustomerFacadeF(long customerLogged) {
		this.customerLogged = customerLogged;
	}

	/** The coupon DAO. */
	CuoponDBDAO couponDAO = new CuoponDBDAO();
	
	/** The helper DAO. */
	HelperMethodsDAO helperDAO = new HelperMethodsDAO();
	
	/**
	 * Purchase coupon.
	 * Before coupons been purchased Method checks 3 terms : 
	 * 		1. Checks if coupons been already purchased by the Customer;
	 * 		2. Checks if Coupon amount is more the 1; 
	 * 		3. Checks if coupons that customer wants to buy is not expired 
	 *  IF all terms are good: 
	 *  	1.performs BUY 
	 *  	@see dao.HelperMethods#buyCoupon(String, String);
	 *  	2. Decrees coupons amount in data base 
	 * @param coupon the coupon
	 */

	public void purchaseCoupon(Coupon coupon) {
		Coupon checkedCoupon = null;
		boolean dateFlag = false;
		boolean amountFlag = false;
		boolean alreadyPurchasedFlag = false;
		try {
			checkedCoupon = couponDAO.getCoupon(coupon.getId());
		} catch (CouponSystemException e) {
		
		}
		// check if coupon was already purchased by the Customer
		Collection<Long> purchasedCouponsIds = null;
		try {
			purchasedCouponsIds = helperDAO.getCouponsBelongTo("COUPON_ID", "CUSTOMER_COUPON", "CUSTOMER_ID",
					customerLogged);
			if (purchasedCouponsIds.contains(coupon.getId())) {
				System.out.println("You have already purchased that Coupon");
				alreadyPurchasedFlag = false;
			} else {
				alreadyPurchasedFlag = true;
			}

		} catch (CouponSystemException e) {
			e.getMessage();
		}

		// check if coupon amount more then > 1 and
		if (checkedCoupon.getAmount() > 1) {
			amountFlag = true;
		}

		else {
			System.out.println("All coupons are soldout");
			amountFlag = false;
		}

		// check if not expired
		Date couponEndDate = checkedCoupon.getEndDate();
		long time = System.currentTimeMillis();
		Date currentDate = new Date(time);
		if ((couponEndDate.compareTo(currentDate)) < 0) {
			System.out.println("Sorry, the coupon you want to buy is expired");
			dateFlag = false;
		} else {
			dateFlag = true;

		}
		// action - decrees amount and upDate Customer Coupon

		if (dateFlag && amountFlag && alreadyPurchasedFlag) {
			int amount = checkedCoupon.getAmount();
			checkedCoupon.setAmount(amount - 1);
			try {
				couponDAO.updateCoupon(checkedCoupon);
				helperDAO.buyCoupon(String.valueOf(customerLogged), String.valueOf(checkedCoupon.getId()));
			} catch (CouponSystemException e) {
				e.getMessage();
			}

		}

	}

	/**
	 * Gets the all my coupons.
	 * Returns all copuns that were purchased by Logged Customer 
	 * @return the all my coupons
	 */

	public Collection<Coupon> getAllMyCoupons() {
		Collection<Coupon> myCoupons = new HashSet<Coupon>();
		Collection<Long> couponsId = null;
		try {
			couponsId = helperDAO.getCouponsBelongTo("COUPON_ID", "CUSTOMER_COUPON", "CUSTOMER_ID", customerLogged);
			for (long id : couponsId) {
				myCoupons.add(couponDAO.getCoupon(id));
			}
		} catch (CouponSystemException e) {
			e.getMessage();
		}
		return myCoupons;
	}

	
	
	
	/**
	 * Gets the my coupons sorted by type.
	 *  getting coupons Collection filtered :
	 * to get coupons by PRICE pass select = " customerCouponsByPrice"
	 * to get coupons by DATE pass select = " customerCouponsByDate"
	 * to get coupons by TYPE pass select = " customerCouponsByType"
	 * reference pass :
	
	 * PRICE example till price reference = "300"
	 * DATE example till date reference = "MM-DD-YYYY"
	 * TYPE example till type reference = " Restrains,
	 * Electricity,
	 * Food,
	 * Health,
	 * Sports,
	 * Camping,
	 * Traveling"
	 * 
	 *
	 * @param select the select
	 * @param reference the reference
	 * @return the my coupons sorted by type
	 */
	public Collection<Coupon> getMyCouponsSortedByType(String select, String reference) {
		Collection<Coupon> mySortedCoupons = null;
		try {
			mySortedCoupons = helperDAO.getCouponSelected(customerLogged, select, reference);
		} catch (CouponSystemException e) {
			e.getMessage();
		}
		return mySortedCoupons;
	}

}
