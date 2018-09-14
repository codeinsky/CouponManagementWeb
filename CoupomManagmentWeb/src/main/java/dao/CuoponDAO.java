package dao;

import java.util.Collection;

import beans.Coupon;
import beans.CouponType;
import couponSystemException.CouponSystemException;
// TODO: Auto-generated Javadoc

/**
 * The Interface CuoponDAO.
 *
 * CuoponDAO is an interface with methods that will be used for Business logic
 */
public interface CuoponDAO {

	/**
	 * Abstract method receives bean "Coupon" and saves it to DataBase-Coupon Table
	 * .
	 *
	 * @param coupon - Coupon bean
	 * @throws the coupon system exception
	 */
	public void createCoupon(Coupon coupon) throws CouponSystemException; // test done - works

	/**
	 * Abstract method receives bean "Coupon". Search for the Coupon in
	 * DataBase-Coupon Table and removes it from the DataBase
	 *
	 * @param coupon bean Coupon
	 * @throws the coupon system exception
	 */

	public void removeCoupon(Coupon coupon) throws CouponSystemException; // test done - works

	/**
	 * Abstract method receives bean "Coupon". Search for the Coupon in DataBase in
	 * Coupon Table and updates all attributes.
	 *
	 * @param coupon
	 * @throws the coupon system exception
	 */

	public void updateCoupon(Coupon coupon) throws CouponSystemException; // test done - works

	/**
	 * Abstract methods - receives coupon ID and returns Coupon Bean from DataBase .
	 *
	 * @param id Coupon ID
	 * @return returns bean
	 * @throws the coupon system exception
	 */

	public Coupon getCoupon(long id) throws CouponSystemException; // test done - works

	/**
	 * Abstract method returns collection of CouponsBeans, all coupons in Coupon
	 * Table .
	 *
	 * @return Coupon Collection
	 * @throws the coupon system exception
	 */
	public Collection<Coupon> getAllCoupons() throws CouponSystemException; // test done - works ;

	/**
	 * Abstract method , receives Coupon Type and returns Collection of coupons with
	 * the requested type
	 * 
	 * @param type the type
	 * @return Coupon Collection
	 * @throws the coupon system exception
	 */

	public Collection<Coupon> getCouponByType(CouponType type) throws CouponSystemException; // test done - works

}
