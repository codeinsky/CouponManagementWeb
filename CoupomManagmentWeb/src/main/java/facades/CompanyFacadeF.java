package facades;

import java.util.Collection;
import java.util.HashSet;

import beans.Coupon;
import couponSystemException.CouponSystemException;
import dao.CuoponDBDAO;
import dao.HelperMethodsDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyFacadeF.
 */
public class CompanyFacadeF extends ClientCouponFacade {
	
	/** The company id logged. */
	private long companyIdLogged; // ID of the company that is logged in the system

	/**
	 * Instantiates a new company facade F.
	 *
	 * @param companyIdLogged the company id logged
	 */
	// constructor , gets ID number of company reference
	public CompanyFacadeF(long companyIdLogged) {
		this.companyIdLogged = companyIdLogged;
	}

	/** The helper DAO. */
	HelperMethodsDAO helperDAO = new HelperMethodsDAO();
	
	/** The coupon DAO. */
	CuoponDBDAO couponDAO = new CuoponDBDAO();

	/**
	 * Creates the coupon.
	 * Methods receives coupon. Checks if Coupons already exist by Title, if yes denies creating 
	 * the coupon. If no creates new Coupon row  in Data Base Coupon table. 
	 * @param coupon the coupon
	 */
	
	public void createCoupon(Coupon coupon) {
		try {
			if (helperDAO.ifExist("COUPON", "TITLE", coupon.getTitle())) { // need test
				System.out.println("Coupon with name = " + coupon.getTitle() + "  already exsists");
			} else {
				coupon.setId(helperDAO.getId("COUPON_ID")); // gets last coupon ID per order
				couponDAO.createCoupon(coupon);
				helperDAO.createCopuon(String.valueOf(companyIdLogged), String.valueOf(coupon.getId()));
			}
		} catch (CouponSystemException e) {
			e.getMessage();
		}

	}

	/**
	 * Removes the coupon.
	 * Before removing (Deleting) method check if that coupon belongs
	 * to the company, if yes deletes the Coupon from all tables.
	 * @param coupon the coupon
	 */

	public void removeCoupon(Coupon coupon) {
		Collection<Long> couponsIds;
		try {
			// check if coupon belongs to the company before remove
			couponsIds = helperDAO.getCouponsBelongTo("COUPON_ID", "COMPANY_COUPON", "COMPANY_ID", companyIdLogged);

			if (couponsIds.contains(coupon.getId())) { // the coupon exists , removal will be execute
				// removes all purchased coupons from CUSTOMER_COUPON table
				helperDAO.removeWhere("CUSTOMER_COUPON", "COUPON_ID", coupon.getId()); // need debug
				helperDAO.removeWhere("COMPANY_COUPON", "COUPON_ID", coupon.getId()); // need debug
				// removes the coupon from the COUPON table
				couponDAO.removeCoupon(coupon);
			} else {
				System.out.println("Coupon you want to remove does not belong to your company");
			}

		} catch (CouponSystemException e) {
			e.printStackTrace();
			e.getMessage();
		}

	}

	/**
	 * UpDate coupon.
	 * Retrieves all coupons ID belong to the logged company. 
	 * Checks if the coupon ID contain in the Collection. 
	 * @see dao.HelperMethods#ifExist(String, String, String)
	 * If yes Updates all attributes. 
	 * @param coupon the coupon
	 */
	
	public void updateCoupon(Coupon coupon) {
		// need check if that coupon belongs to the company
		Collection<Long> couponsIds;
		try {
			couponsIds = helperDAO.getCouponsBelongTo("COUPON_ID", "COMPANY_COUPON", "COMPANY_ID", companyIdLogged);
			System.out.println(couponsIds);
			// checks if the coupon belongs to the company
			if (couponsIds.contains(coupon.getId())) {
				couponDAO.updateCoupon(coupon);
				System.out.println("Proccesing the coupon update");
			} else {
				System.out.println("This coupon doesn't belong to your Company");
			}
		} catch (CouponSystemException e) {
			e.getMessage();
		}

	}

	/**
	 * Gets the coupon by id.
	 * First checks if coupon belongs to the logged and if yes 
	 * returns Company bean by ID.  
	 * @param id the id
	 * @return the coupon by id
	 * 
	 */

	public Coupon getCouponById(long id) {
		Coupon coupon = null;
		Collection<Long> couponsIds;
		try {
			couponsIds = helperDAO.getCouponsBelongTo("COUPON_ID", "COMPANY_COUPON", "COMPANY_ID", companyIdLogged);
			System.out.println(couponsIds);
			// checks if the coupon belongs to the company
			if (couponsIds.contains(id)) {
				coupon = couponDAO.getCoupon(id);
			} else {
				System.out.println("This coupon doesn't belong to your Company");
			}
		} catch (CouponSystemException e) {
			e.getMessage();
		}
		return coupon;

	}

	/**
	 * Gets the all coupons.
	 * Returns all coupons list belong to the Company.
	 * @return the all coupons
	 */

	public Collection<Coupon> getAllCoupons() {
		Collection<Coupon> allCoupons = new HashSet<Coupon>();
		Collection<Long> couponsIds;
		try {
			couponsIds = helperDAO.getCouponsBelongTo("COUPON_ID", "COMPANY_COUPON", "COMPANY_ID", companyIdLogged);
			System.out.println("Coupns belong to your Company are: " + couponsIds);
			if (couponsIds.size() == 0) {
				System.out.println("There are no any coupons associated to your Compnay");
			} else {
				for (long id : couponsIds) {
					allCoupons.add(couponDAO.getCoupon(id));
				}
			}

		} catch (CouponSystemException e) {
			e.getMessage();
		}
		return allCoupons;
	}

	// Get list of coupons sorted by : TYPE or PRICE LESS THEN or DATE BEFORE
	/**
	 * Sort coupon by.
	 * Returns collection filter by (TYPE , DATE , PRICE).
	 * @see dao.HelperMethods#getCouponSelected(long, String, String) 
	 * @param select the select
	 * @param refernece the reference
	 * @return the collection
	 */
	// 'yyyy-mm-dd'
	public Collection<Coupon> sortCouponBy(String select, String refernece) {
		Collection<Coupon> selectedCouponsBy = null;
		try {
			selectedCouponsBy = helperDAO.getCouponSelected(companyIdLogged, select, refernece);
		} catch (CouponSystemException e) {
			e.getMessage();
		}
		return selectedCouponsBy;
	}

}
