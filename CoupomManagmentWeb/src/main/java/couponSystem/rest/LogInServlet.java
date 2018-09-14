package couponSystem.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import couponSystemException.CouponSystemException;
import dao.HelperMethodsDAO;
import facades.AdminFacadeF;
import facades.ClientCouponFacade;
import facades.CompanyFacadeF;
import facades.CustomerFacadeF;

@Controller 
public class LogInServlet {
	HelperMethodsDAO helper = new HelperMethodsDAO();
	ClientCouponFacade facade ; 
	String directURL=null;
	
	@RequestMapping(value="/LogIn" , method = RequestMethod.POST)
	public  String logIn(@RequestParam String userType, @RequestParam String name, @RequestParam String pwd) {
		try {
			facade = helper.logIn(userType, name, pwd);
		} catch (CouponSystemException e) {
			
		}
			if (facade!=null) {
				
				switch(userType) {
				
				case "admin"    : AdminFacadeF admin = (AdminFacadeF)facade;
				System.out.println("admin logged");
				directURL = "http://localhost:8082/admin.html";
				break;
				
				case "customer" : CustomerFacadeF customer = (CustomerFacadeF)facade;
				System.out.println("Customer logged");
				directURL = "http://localhost:8082/customer.html";
				break;
				
				case "company"	: CompanyFacadeF copmany = (CompanyFacadeF)facade;
				System.out.println("Company logged logged");
				directURL = "http://localhost:8082/company.html" ; 
				}
				return "redirect:" + directURL;
			}
			else {
				return "redirect:http://localhost:8082/failed.html";
			}
			
		
	}
}
