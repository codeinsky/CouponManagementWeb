package couponSystem.rest;





import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import beans.Company;
import beans.Customer;
import facades.AdminFacadeF;

/**
 * The Class AdminRest.
 * ADMIN FACADE METHODS : 

1. CreateCompany : POST {company} ; /company - done , works 
2. Remove Company: DELETE {company} /company - done , works but need to change to be removed by ID 
3. Company Details Update : PUT {company} /company/id - done and works (need changed to get his page and then change)
4. GetAll Companies : GET /company - done , works 
5. Get Company by ID : GET {id}  /company/id - done and works 

6. Add Customer : POST {customer} /customer/ - done and works
7. Remove Customer : DELETE /customer - done and works 
8. UpdateCustomer : PUT {customer}  - done and works
9. GetCustomerList : GET - done and works 
10. GetCustomer by ID : GET{id }

 * 
 */

// example for JSON company : {"id":8,"compName":"Amdocs","password":"3333","email":"amdocs@amdocs.com"}

@RestController 
public class AdminRest {
	AdminFacadeF admin = new AdminFacadeF();
	
	//Company REST 
	
	@RequestMapping (value="/company" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCustomer(@RequestBody Company company) {
		admin.createCompany(company);
	}
	
	@RequestMapping (value="/company" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Company> getAllCopmanies(){
		return admin.getAllCompanies();
	}
	
	@RequestMapping (value="/company" , method = RequestMethod.DELETE)
	public void removeCompany (@RequestBody Company company) {
		admin.removeCompany(company);
		
	}
	@RequestMapping(value = "/company" , method = RequestMethod.PUT)
	public void updateCompany(@RequestBody Company company) {
		admin.companyDetailsUpdate(company);
	}
	
	@RequestMapping(value="/company/{id}" , method = RequestMethod.GET)
	public Company getComapnyById (@PathVariable("id") long id) {
		return admin.getCompany(id);
		
		
		//Customer REST 
	}
	@RequestMapping(value="/customer" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCustomer(@RequestBody Customer customer) {
		admin.addCustomer(customer);
	}
	@RequestMapping(value="/customer/{id}" , method = RequestMethod.GET)
	public Customer getCustomerById(@PathVariable("id") long id) {
		return admin.getCustomer(id);
	}
	@RequestMapping(value="/customer" ,  method = RequestMethod.DELETE , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void removeCustomer(@RequestBody Customer customer) {
		admin.removeCustomer(customer);
	}
	
	@RequestMapping (value="/customer" , method = RequestMethod.PUT )
	public void updateCustomer (@RequestBody Customer customer) {
		admin.updateCustomerDetails(customer);
	}
	@RequestMapping (value="/customer" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Customer> getCustomers(){
		return admin.getCustomerList();
	}

}
