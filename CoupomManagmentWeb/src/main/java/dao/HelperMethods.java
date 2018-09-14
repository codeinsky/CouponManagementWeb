package dao;

import java.util.Collection;

import beans.Coupon;
import couponSystemException.CouponSystemException;
import facades.ClientCouponFacade;

// TODO: Auto-generated Javadoc
/**
 * The Interface HelperMethods.
 */
public interface HelperMethods {
	
	/**
	 * If exist.
	 * Abstract generic helper method. Checks if any bean exist in any table. Used for 
	 * verification in "Business-logic" part. 
	 * Receives "Table name" , "column" in the table , and "item". 
	 * 
	 * Example :  sqlTable: "Coupon Table" ; column: "Coupon_Name" ; item:"TEVA"; 
	 * 			   Returns TRUE if TEVA exist or false if not;  
	 * 
	 * @param sqlTable the SQL table
	 * @param column the column
	 * @param item the item
	 * @return true, if successful
	 * @throws CouponSystemException the coupon system exception
	 */
	public boolean ifExist(String sqlTable, String column, String item) throws CouponSystemException;

	/**
	 * Removes the where.
	 * Abstract void method. Removes row in any table per searched item. 
	 * Method used in "Business-logic" part. 
	 * Receives: "sqlTable" - name of the table where user wants to remove bean 
	 * 			 "column" - column where searched item located. 
	 * 			 "id" - id of Bean we want to delete(remove).
	 * Example : 
	 * 			 "sqlTable" - Coupon 
	 * 			 "column" - ID
	 * 			 "id" - 3
	 * 
	 * @param sqlTable the SQL table.
	 * @param column the column
	 * @param id the id
	 * @throws CouponSystemException the coupon system exception
	 */
	public void removeWhere(String sqlTable, String column, long id) throws CouponSystemException;

	/**
	 * Gets the coupons belong to.
	 * Abstract generic method. Returns any column items collection from any table if searched item 
	 * found in the row. 
	 * Used in "business part" logic to return collection of ID's of coupons belongs to 
	 * one company.   
	 * Receives : 
	 * 			"getColumn"   : Items which we want to to collect and  return
	 * 			"SQL table"   : Table Name from where we collect data 
	 * 			"whereColumn" : Column where arguments ID located
	 * 			"ID"		  : Id of the company that looks for Coupons. 
	 * 							Preventing returning coupons that belongs to other company.  
	 * 
	 * Example: 
	 * 			"getColumn"   : COUPON_ID
	 * 			"SQL table"   : COUPON
	 * 			"whereColumn" : ID 
	 * 			"ID"		  : 1
	 * 
	 * @param getColumn the get column
	 * @param sqlTable the SQL table
	 * @param whereColumn the where column
	 * @param id the id
	 * @return the coupons belong to
	 * @throws CouponSystemException the coupon system exception
	 */
	public Collection<Long> getCouponsBelongTo(String getColumn, String sqlTable, String whereColumn, long id)
			throws CouponSystemException;

	/**
	 * Gets the coupon selected.
	 * Abstract generic method. Returns collection of Coupons belong to Company filtered by "Type" , "Price" , "EndDate.
	 * Used in "Business Logic DAO" by COMPANY AND CUSTOMER to pull their coupons filtered.
	 * Receives: 
	 * 			id    	   : id of the company that pulls its coupons
	 * 			select 	   : select argument ("Type" , "Price", "EndDate");
	 * 			reference  : argument reference ("type" = {Restrains , Electricity , Food , Health, Sports , Camping, Traveling} ; 
	 * 											 "price" = 36.98(long number) will return all coupons cost less then that price
	 * 											 "date"	 = 2018-01-24 will return all coupons that EdnDate is 
	 * 												before 2018/01/24. 
	 * @param id the id
	 * @param select the select
	 * @param reference the reference
	 * @return the coupon selected
	 * @throws CouponSystemException the coupon system exception
	 */
	public Collection<Coupon> getCouponSelected(long id, String select, String reference) throws CouponSystemException;

	/**
	 * Buy coupon.
	 * Abstract void method. Updates CUSTOME_COUPON SQl table when Customer purchase coupon.
	 * Receives Customer id and Coupon Id and updated them in NEW row in CUSTOME COUPON table.   
	 * @param customerId the customer id
	 * @param couponId the coupon id
	 * @throws CouponSystemException the coupon system exception
	 */
	public void buyCoupon(String customerId, String couponId) throws CouponSystemException;

	/**
	 * Creates the coupon.
	 * Abstract void method. Used in Business Logic part to link new coupons with company owning it.
	 * Updates COMPANY_COUPON table with new row with ID's 
	 * @param companyId the company id
	 * @param couponId the coupon id
	 * @throws CouponSystemException the coupon system exception
	 */
	public void createCopuon(String companyId, String couponId) throws CouponSystemException;

	/**
	 * Log in.
	 * Log In method used for LoggingIn the CouponSystem. 
	 * Receives : 
	 * 			userType : (Customer, Admin , Company)
	 * 			userName : Name of the Customer or Company in DataBase Table that tries to log in 
	 * 			password : password . String saved in DataBase in Password Column. 
	 * Returns : 
	 * 			 - If userName exist and password from DataBase is equal, method returns facade 
	 * 			that need to be cast to (Company, Admin or Customer) Facade
	 * 			 - If user name not found in DataBase returns failing message 
	 * 			 - If password  not found in DataBase returns wrong password message 
	 * @param userType the user type
	 * @param userName the user name
	 * @param password the password
	 * @return the facade
	 * @throws CouponSystemException the coupon system exception
	 */
	public ClientCouponFacade logIn(String userType, String userName, String password) throws CouponSystemException;

	/**
	 * Gets the id.
	 * Abstract method used to create automatically new ID for new (CUSTOMER, COUPON , COMPANY). 
	 * Methods pull ID from SQL tables used for  ID. And increment it for the next one. 
	 * Method keeps the system creates single (key value) ID and prevent from repeating by mistake. 
	 * Method receives type of Id we need for creating new (COMPANY_ID , CUSTOMER_ID , COUPON_ID);        
	 * @param typeId the type id
	 * @return the id
	 * @throws CouponSystemException the coupon system exception
	 */
	public long getId(String typeId) throws CouponSystemException;
}
