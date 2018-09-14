package couponSystem.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import facades.AdminFacadeF;

@SpringBootApplication
public class CoupomManagmentWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoupomManagmentWebApplication.class, args);
//		CouponSystem.CouponSystem system = CouponSystem.CouponSystem.getInstance();
//		AdminFacadeF admin = (AdminFacadeF) system.logIn("admin", "admin", "1234");
		
	}
}
