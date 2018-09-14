package facades;

import java.util.Collection;

import beans.Company;
import beans.Coupon;
import beans.Customer;
import couponSystemException.CouponSystemException;
import dao.CompanyDBDAO;
import dao.CuoponDBDAO;
import dao.CustomerDBDAO;
import dao.HelperMethodsDAO;


// TODO: Auto-generated Javadoc
/**
 * The Class AdminFacadeF.
 * Includes all Admins business logic methods 
 */
public class AdminFacadeF extends ClientCouponFacade{
	
	/** The company DAO. */
	CompanyDBDAO companyDAO = new CompanyDBDAO();
	
	/** The customer DAO. */
	CustomerDBDAO customerDAO = new CustomerDBDAO();
	
	/** The coupon DAO. */
	CuoponDBDAO couponDAO = new CuoponDBDAO();
	
	/** The helper DAO. */
	HelperMethodsDAO helperDAO = new HelperMethodsDAO();

	/**
	 * Creates the company.
	 * Method receives Company bean. Checks if already exist company with 
	 * same name, If not performs createCopmany method. 
	 * @param company the company
	 */

	public void createCompany(Company company) {
		// check if the Company name already exists
		try {
			if (helperDAO.ifExist("COMPANY", "COMP_NAME", company.getCompName())) { 
				System.out.println("Company with name = " + company.getCompName() + " already exsists");
			} else {
				company.setId(helperDAO.getId("COMPANY_ID"));
				companyDAO.createCompany(company);
			}
		} catch (CouponSystemException e) {
			e.getMessage();
		}

	}

	
	/**
	 * Removes the company.
	 * Method receives company bean.
	 * Gets all coupons belongs to the company.
	 * Uses helper method helperDAO.removeWhere 
	 * by Coupon Id removes all coupons from tables(Coupon,Customer_Coupon, Copmany_Coupon)
	 * removes the company from Company table as well
	 * @param company the company
	 */
	
	public void removeCompany(Company company) {

		try {
			Collection<Coupon> couponsList = companyDAO.getCoupons(company);
			for (Coupon coupon : couponsList) {
				// removing all coupons belonging to the company from
				helperDAO.removeWhere("COUPON", "ID", coupon.getId());
				helperDAO.removeWhere("Customer_Coupon", "Coupon_ID", coupon.getId());
				helperDAO.removeWhere("Company_Coupon", "Cuopon_ID", coupon.getId());
				// removing the Company from Company DB table
			}
			companyDAO.removeCompany(company);
		} catch (CouponSystemException e) {
			e.getMessage();
		}
	}

	/**
	 * Company details update.
	 * Receives Company bean,  the company from DataBase by Id.
	 * Checks if User tries to update name (different name):
	 * 		-if yes, denies the action 
	 * 		-if no updates(overwrites all attributes)  
	 * @param company the company
	 */
	
	public void companyDetailsUpdate(Company company) {
		// COMPANY NAME CAN NOT BE CHANGED
		try {
			// checking if Admin tries to change the name by mistake
			Company companyPriorUpdate = companyDAO.getCompany(company.getId());
			if (company.getCompName().equals(companyPriorUpdate.getCompName())) { // names are equal --> update approved
				companyDAO.updateCompany(company);
			} // update

			else {
				throw new CouponSystemException("Company NAME can not be changed");
			}

		} catch (CouponSystemException e) {
			e.getMessage();
		}
	}

	/**
	 * Gets the all companies.
	 * 
	 * @return the all companies existing in Data Base
	 */
	
	public Collection<Company> getAllCompanies() {
		Collection<Company> companies = null;
		try {
			companies = companyDAO.getAllCompanies();
		} catch (CouponSystemException e) {
			e.getMessage();
		}
		return companies;
	}

	/**
	 * Gets the company by ID 
	 *
	 * @param id the id
	 * @return the company
	 */

	public Company getCompany(long id) {
		Company company = null;
		try {
			company = companyDAO.getCompany(id);
		} catch (CouponSystemException e) {
			e.getMessage();
		}
		return company;
	}

	/**
	 * Adds the customer.
	 * Receives Customer bean. Methods checks if Customer with that name 
	 * all ready exists and if yes denies the action. 
	 * If no Customer with the name creates new Id for the new customer 
	 * and adds it to the Data Base in Customer table new row. 
	 * @param customer the customer
	 */
	public void addCustomer(Customer customer) {
		// need check if there is already customer with same name
		try {
			if (helperDAO.ifExist("CUSTOMER", "CUST_NAME", customer.getCustName())) {
				throw new CouponSystemException("Customer with Name=" + customer.getCustName() + " already exists");
			} else {
				customer.setId(helperDAO.getId("CUSTOMER_ID"));
				customerDAO.createCustomer(customer); // creating new customer
			}
		} catch (CouponSystemException e) {
			e.getMessage();
		}

	}

	/**
	 * Removes the customer.
	 * Removes Customers Coupon from data base table Customer_Coupon. 
	 * Removes the customer from Customer table as well.
	 * @param customer the customer
	 */

	public void removeCustomer(Customer customer) {
		// all coupons purchased by the Customer need to be "released" from the table
		// Customer-Coupon
		try {
			helperDAO.removeWhere("Customer_Coupon", "Customer_ID", customer.getId()); // delete Customer Coupon
																							// History
			customerDAO.removeCustomer(customer); // remove Customer from the System
		} catch (CouponSystemException e) {
			e.getMessage();
		}

	}

	/**
	 * Update customer details.
	 * Retries Customer from Data Base by ID. 
	 * Checks if Admin tries to update Copamany name , denies the action.
	 * If Customer Name is the same updates all attributes.   
	 * @param customer the customer
	 */
	
	public void updateCustomerDetails(Customer customer) {
		try {
			// checks if customer already exists and if the name is the same
			Customer CustomerpriorUpdate = customerDAO.getCustomer(customer.getId());
			// check if Admin doesn't tries to change the name
			if (customer.getCustName().equals(CustomerpriorUpdate.getCustName())) {
				customerDAO.updateCustomer(customer);
			} else {
				throw new CouponSystemException("Customer name can't be changed");
			}
		} catch (CouponSystemException e) {
			e.getMessage();
		}
	}

	/**
	 * Gets the customer list.
	 *
	 * @return the customer list
	 */

	public Collection<Customer> getCustomerList() {
		Collection<Customer> customerList = null;
		try {
			customerList = customerDAO.getAllCustomers();
		} catch (CouponSystemException e) {
			e.getMessage();
		}
		return customerList;
	}

	/**
	 * Gets the customer by ID. 
	 *
	 * @param id the id
	 * @return the customer
	 */
	
	public Customer getCustomer(long id) {
		Customer customer = null;
		try {
			customer = customerDAO.getCustomer(id);
		} catch (CouponSystemException e) {
			e.getMessage();
		}
		return customer;
	}
}
