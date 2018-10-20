package couponSystem.rest;





import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import beans.Company;
import beans.Customer;
import facades.AdminFacadeF;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminRest.
 * ADMIN FACADE METHODS : 
 * 
 * 1. createCompany(Company)
 * 2. removeCompany(Company)
 * 3. companyDetailsUpdate(Company)
 * 4. getAllCompanies()
 * 5. getCompany(long)
 * 6. addCustomer(Customer)
 * 7. removeCustomer(Customer)
 * 8. updateCustomerDetails(Customer)
 * 9. getCustomerList()
 * 10.getCustomer(long)
 * 
 */

// example for JSON company : {"id":8,"compName":"Amdocs","password":"3333","email":"amdocs@amdocs.com"}
@CrossOrigin("*")
@RestController 
public class AdminRest {
	
	/** The admin. */
	AdminFacadeF admin = new AdminFacadeF();
	
	//Company REST 
	
	/**
	 * Creates the company.
	 *
	 * @param company the company
	 */
	@RequestMapping (value="/admin/createCompany" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCompany(@RequestBody Company company) {
		admin.createCompany(company);
	}
	
	/**
	 * Gets the all companies.
	 *
	 * @return the all companies
	 */
	@RequestMapping (value="/admin/getAllCompanies" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Company> getAllCompanies(){
		return admin.getAllCompanies();
	}
	
	/**
	 * Removes the company.
	 *
	 * @param company the company
	 */
	@RequestMapping (value="/admin/removeCompany" , method = RequestMethod.DELETE)
	public void removeCompany (@RequestBody Company company) {
		admin.removeCompany(company);
		
	}
	
	/**
	 * Update company.
	 *
	 * @param company the company
	 */
	@RequestMapping(value = "/admin/companyDetailsUpdate" , method = RequestMethod.PUT)
	public void updateCompany(@RequestBody Company company) {
		admin.companyDetailsUpdate(company);
	}
	
	/**
	 * Gets the company by id.
	 *
	 * @param id the id
	 * @return the company by id
	 */
	@RequestMapping(value="/admin/getCompany/{id}" , method = RequestMethod.GET)
	public Company getCompanyById (@PathVariable("id") long id) {
		return admin.getCompany(id);
		
		
		//Customer REST 
	}
	
	/**
	 * Creates the customer.
	 *
	 * @param customer the customer
	 */
	@RequestMapping(value="/admin/addCustomer" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCustomer(@RequestBody Customer customer) {
		admin.addCustomer(customer);
	}
	
	/**
	 * Gets the customer by id.
	 *
	 * @param id the id
	 * @return the customer by id
	 */
	@RequestMapping(value="/admin/getCustomer/{id}" , method = RequestMethod.GET)
	public Customer getCustomerById(@PathVariable("id") long id) {
		return admin.getCustomer(id);
	}
	
	/**
	 * Removes the customer.
	 *
	 * @param customer the customer
	 * Removes just by ID 
	 */
	@RequestMapping(value="/admin/removeCustomer" ,  method = RequestMethod.DELETE , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void removeCustomer(@RequestBody Customer customer) {
		admin.removeCustomer(customer);
	}
	
	/**
	 * Update customer.
	 *
	 * @param customer the customer
	 */
	@RequestMapping (value="/admin/updateCustomerDetails" , method = RequestMethod.PUT )
	public void updateCustomer (@RequestBody Customer customer) {
		admin.updateCustomerDetails(customer);
	}
	
	/**
	 * Gets the customers.
	 *
	 * @return the customers
	 */
	@RequestMapping (value="/admin/getCustomerList" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Customer> getCustomers(){
		return admin.getCustomerList();
	}

}
