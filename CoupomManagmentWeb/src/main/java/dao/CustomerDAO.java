package dao;

import java.util.Collection;

import beans.Customer;
import couponSystemException.CouponSystemException;

// TODO: Auto-generated Javadoc
/**
 * The Interface CustomerDAO.
 */
public interface CustomerDAO {

	/**
	 * Creates the customer. Abstract method receives Customer bean and creates new
	 * row in DataBase Customer table
	 * 
	 * @param customer the customer
	 * @throws CouponSystemException the coupon system exception
	 */
	public void createCustomer(Customer customer) throws CouponSystemException; // test done , works

	/**
	 * Removes the customer. Abstract Method receives Customer bean. Search for
	 * existing Customer and removes it from DataBase Customer table.
	 * 
	 * @param customer the customer
	 * @throws CouponSystemException the coupon system exception
	 */
	public void removeCustomer(Customer customer) throws CouponSystemException; // test done . works

	/**
	 * Update customer. Abstract void method receives customer bean. Search for
	 * existing Customer in DataBase in Customer Table and updates all attributes.
	 * 
	 * @param customer the customer
	 * @throws CouponSystemException the coupon system exception
	 */
	public void updateCustomer(Customer customer) throws CouponSystemException; // test done , works

	/**
	 * Gets the customer. Abstract method, receives "long ID". Search for Customer
	 * with received ID and returns Customer bean.
	 * 
	 * @param id the id
	 * @return the customer
	 * @throws CouponSystemException the coupon system exception
	 */
	public Customer getCustomer(long id) throws CouponSystemException; // test done , works

	/**
	 * Gets the all customers. Abstract method, returns all Collection of All
	 * customers
	 * 
	 * @return the all customers
	 * @throws CouponSystemException the coupon system exception
	 */
	public Collection<Customer> getAllCustomers() throws CouponSystemException; // test done , works

	/**
	 * Login. Abstract method , receives String Customer Name and String Password.
	 * Not used. See dao.helperMethodsDAO.java LogIn;
	 * 
	 * @param custName the cust name
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean login(String custName, String password); // not used yet
}
