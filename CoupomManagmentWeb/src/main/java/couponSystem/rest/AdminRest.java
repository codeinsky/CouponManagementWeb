package couponSystem.rest;





import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import beans.Company;
import facades.AdminFacadeF;

/**
 * The Class AdminRest.
 * ADMIN FACADE METHODS : 

1. CreateCompany : POST {company} ; /company - in progress
2. Remove Company: DELETE {company} /company/id 
3. Company Details Update : PUT {company} /company/id 
4. GetAll Companies : GET /company - done , works 
5. Get Company by ID : GET {id}  /company/id
6. Add Customer : POST {customer} /customer/
7. Remove Customer : DELETE /customer
8. UpdateCustomer : PUT {customer}
9. GetCustomerList : GET 
10. GetCustomer by ID : GET{id }

 * 
 */

// example for JSON company : {"id":8,"compName":"Amdocs","password":"3333","email":"amdocs@amdocs.com"}

@RestController 
public class AdminRest {
	AdminFacadeF admin = new AdminFacadeF();
	
	@RequestMapping (value="/company" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCustomer(@RequestBody Company company) {
		// need to complete 
		admin.createCompany(company);
	}
	
	@RequestMapping (value="/company" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Company> getAllCopmanies(){
		return admin.getAllCompanies();
	}
	

}
