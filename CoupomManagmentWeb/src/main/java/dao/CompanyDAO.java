package dao;

import java.util.Collection;

import beans.Company;
import beans.Coupon;
import couponSystemException.CouponSystemException;
// TODO: Auto-generated Javadoc

/**
 * The Interface CompanyDAO.
 *
 * CompnayDAO class is an interface with methods used by CouponSystem Business
 * logic. Company Interface includes basic operation for work with dataBase.
 */
public interface CompanyDAO {

	/**
	 * Abstract method receives Company bean and saves(create) it to dataBase in
	 * Company table .
	 *
	 * @param company - company bean
	 * @throws the coupon system exception
	 */
	public void createCompany(Company company) throws CouponSystemException; // tested , works

	/**
	 * Abstract Method receives company bean. Search for it in the DataBase and
	 * removes it.
	 *
	 * @param company - Method receives Bean Company Per rest needs could be changed
	 *                to perform search by ID .
	 * @throws CouponSystemException the coupon system exception
	 */
	public void removeCompany(Company company) throws CouponSystemException; // tested , works

	/**
	 * Abstract method receives company. Looks for the company in DataBase and
	 * replace all attributes
	 *
	 * @param company - receives bean Company
	 * @throws CouponSystemException the coupon system exception
	 */

	public void updateCompany(Company company) throws CouponSystemException; // tested , works

	/**
	 * Abstract method receives and returns Company Bean per ID .
	 *
	 * @param id - receives company ID
	 * @return - returns bean "Company" that was requested with ID
	 * @throws CouponSystemException -
	 */

	public Company getCompany(long id) throws CouponSystemException; // tested , works

	/**
	 * Abstract method returns collection with all Companies.
	 *
	 * @return - Collection of Company beans , all companies from Company table
	 * @throws CouponSystemException the coupon system exception
	 */
	public Collection<Company> getAllCompanies() throws CouponSystemException; // tested, works

	/**
	 * Not used. See HelperMethods class , LogIn - method
	 *
	 * @param the comp name
	 * @param the password
	 * @return the boolean
	 * @throws the coupon system exception
	 */

	public Boolean logIn(String compName, String password) throws CouponSystemException; // not used yet

	/**
	 * Abstract method , receives bean "Company" and returns collection of Coupons
	 * beans .
	 *
	 * @param compnay - receives bean "Company"
	 * @return - returns Collection of coupons
	 * @throws CouponSystemException the coupon system exception
	 */
	Collection<Coupon> getCoupons(Company compnay) throws CouponSystemException; // tested and works

}
