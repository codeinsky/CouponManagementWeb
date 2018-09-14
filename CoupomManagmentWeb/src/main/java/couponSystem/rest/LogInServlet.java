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
	
	@RequestMapping(value="/LogIn" , method = RequestMethod.POST)
	public @ResponseBody String logIn(@RequestParam String userType, @RequestParam String name, @RequestParam String pwd) {
		try {
			facade = helper.logIn(userType, name, pwd);
		} catch (CouponSystemException e) {
			
		}
			if (facade!=null) {
				
				switch(userType) {
				
				case "admin"    : AdminFacadeF admin = (AdminFacadeF)facade;
				System.out.println("admin logged");
				break;
				
				case "customer" : CustomerFacadeF customer = (CustomerFacadeF)facade;
				System.out.println("Customer logged");
				break;
				
				case "company"	: CompanyFacadeF copmany = (CompanyFacadeF)facade;
				System.out.println("Company logged logged");
				}
				return "you got it";
			}
			else {
				return "no facade";
			}
			
		
	}
}
