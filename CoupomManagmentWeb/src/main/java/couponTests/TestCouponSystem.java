package couponTests;

import java.sql.Date;

import beans.Company;
import beans.Coupon;
import beans.CouponType;
import beans.Customer;
import couponSystem.CouponSystem;
import facades.AdminFacadeF;
import facades.CompanyFacadeF;
import facades.CustomerFacadeF;


public class TestCouponSystem {
public static void main(String[] args) {
	/**
	 * 
	 * 											Admin Methods Test 
	 *	1.createCompany(Company company) - done 
	 *	2.removeCompany(Company company) - done and works. removes company per ID - if need change to Company name
	 * 	3.companyDetailsUpdate(Company company) - done and tested, Company can not be changed. finds company per ID and changes all details 
	 * 	4.getAllCompanies() - done , works . Returns all companies with ID , Name , Password , Email. Empty Coupon ID list 
	 * 	5.addCustomer(Customer customer) - done , works 
	 * 	6.removeCustomer(Customer customer) - done , removes per Id . 
     *	7.updateCustomerDetails(Customer customer) - done , name can not be changed. Password changed successfully 
	 * 	8.getCustomerList() - done , companies list retrieved
	 * 
	 * 	
	 * 									   	Company Methods Test
	 * 
	 * 	1. CreateCoupon - done , works . Date example Date.valueOf("2018-01-01")", type example  CouponType.valueOf("Food")
	 *	2. RemoveCoupon - done , works . Checks if coupons belongs to LoggedIn Company 
	 *	3. UpdateCoupon - done , works . Update per Coupon Id and checks if coupons belongs to the company wants to update 
	 *	4. GetCouponByID - done works . Returns single coupon per ID belongs to the company 
	 *	5. GetAllCoupons - done , works. Checks if needed coupon belongs to the Logged Company before returning it 
	 * 	6. SortCoupons - 
	 * 		- Get all coupons belongs to Logged-In company , sorted by  (example : "Type" , "Food") - done and works 
	 * 		- Gel all coupons belongs to Logged-In company , sorted by (example : "Price" , "30" , all coupons cheaper then 30) - done and works 
	 * 		- Gel all coupons belongs to Logged-In company , sorted by (example : "Date" , "2018-11-25" , all coupons end date before "2018-11-25") - done and works 
     *	7. LogIN  - done , works 
     *
     *									
     *
     *										Customer Tests
     *	1. LogIn - done and works 
     *  2. PurchaseCoupon  - done and works. If all 3 terms below TRUE , updates CUSTOMER_COUPON - BUY   
     *  	- Checks if coupon was already purchased by the same Customer - tested and works 
     *  	- Checks if coupon is expired compared to the Current date - tested and works  
     *  	- Checks if coupons amount is more then 0 - tested and works 
     *  
 	 *  3. GetAllMyCoupons - done and works 
 	 *	4. GetMyCouponSorted - 
 	 *		- to get coupons by PRICE   ("customerCouponsByPrice" , "30") - returns all coupons belong 
 	 *		  to the Customer cheaper then 30  
	 * 		- to get coupons by DATE    ("customerCouponsByDate" , "2017-12-31") - returns all coupons 
	 * 		  belongs to the Customer with endDate before 31.12.2017  
	 *      - to get coupons by TYPE 	("customerCouponsByType" , "food" ) - 
     *
     *
     *
     *								DailyCouponExpirationTask
     *1. Receives (True or False): false - makes thread active ; true -makes thread stop "cleaning"  
     *2. Removes All  expired coupons from tables : COUPON , COMPANY_COUPON, CUSTOMER_COUPON - done and works 
     *
     * 	
	 **/ 
	
	
	
	Coupon coupon = new Coupon(1, "BestSport", Date.valueOf("2018-01-01"), Date.valueOf("2018-01-29") , 10 , CouponType.valueOf("Sports"), "Best deal" , 34.87 , "www.image.jpge");
	Coupon coupon1 = new Coupon(2, "PanCakeWorld", Date.valueOf("2017-06-01"), Date.valueOf("2019-01-31") , 10 , CouponType.valueOf("Food"), "Best deal" , 20.87 , "www.image.jpge");
	Coupon coupon2 = new Coupon(3, "FishAndMe", Date.valueOf("2016-01-01"), Date.valueOf("2020-01-29") , 10 , CouponType.valueOf("Travelling"), "Best deal" , 60.87 , "www.image.jpge");
	Coupon coupon3 = new Coupon(7, "TripOut", Date.valueOf("2012-01-01"), Date.valueOf("2019-01-29") , 10 , CouponType.valueOf("Campning"), "Best deal" , 200.87 , "www.image.jpge");
	Coupon coupon4 = new Coupon(5, "BestHotelDeal", Date.valueOf("2012-01-01"), Date.valueOf("2014-01-29") , 10 , CouponType.valueOf("Health"), "FthatDeal" , 10.87 , "www.image.jpge");
	
    Company testCompany = new Company(3, "Intel" , "abcd" , "intel@intel.com");
    Company testCompany1 = new Company(6, "Teva" , "12345" , "newTeva@teva.co.il");
    Company testCompany2 = new Company(5, "Microsft" , "1111" , "microsoft@micro.com");
    Company testCompany3 = new Company(4, "Apple" , "abcd" , "Apple@pple.com");
    Company testCompany4 = new Company(5, "Amdocs" , "3333" , "amdocs@amdocs.com");
    
    
    Customer customer = new Customer(11, "Ivan", "1234");
    Customer customer1 = new Customer(12, "Pet", "1111");
    Customer customer2 = new Customer(13, "Jon", "2222");
    Customer customer3 = new Customer(14, "Smon", "3333");
    Customer customer4 = new Customer(25, "Jeny111", "changed");
    
    
	CouponSystem system = CouponSystem.getInstance();
	
	// 								Thread Test 
//	
	Thread thread = new Thread(new DailyCouponExpirationTask(false));
	thread.run();
//	
	//								Customer Test 
	
//	CustomerFacadeF cust = (CustomerFacadeF)system.logIn("customer", "Pet", "1111");
//	CustomerFacadeF cust1 = (CustomerFacadeF)system.logIn("customer", "Jon", "2222");
//	CustomerFacadeF cust2 = (CustomerFacadeF)system.logIn("customer", "Smon", "3333");
//	
//	cust2.getMyCouponsSortedByType("customerCouponsByPrice", "80").forEach(System.out::println);
//	cust2.getMyCouponsSortedByType("customerCouponsByPrice", "35").forEach(System.out::println);
//	cust2.getMyCouponsSortedByType("customerCouponsByPrice", "80").forEach(System.out::println);
//	
//	cust2.getMyCouponsSortedByType("customerCouponsByType", "Food").forEach(System.out::println);
//	cust2.getMyCouponsSortedByType("customerCouponsByType", "Travelling").forEach(System.out::println);
//	
//	
//	cust2.getMyCouponsSortedByType("customerCouponsByDate", "2021-01-01").forEach(System.out::println);
//	cust2.getMyCouponsSortedByType("customerCouponsByDate", "2014-01-01").forEach(System.out::println);
//	cust2.getMyCouponsSortedByType("customerCouponsByDate", "2020-01-28").forEach(System.out::println);
	
//	cust1.purchaseCoupon(coupon); // expired coupon , denied to be purchased 
//	cust1.purchaseCoupon(coupon1); // good for buy
//	cust1.purchaseCoupon(coupon2); // good buy 
//	cust1.purchaseCoupon(coupon3); // good for but
//	cust.purchaseCoupon(coupon4); // Expired 
	
//	cust1.purchaseCoupon(coupon); // expired coupon , denied to be purchased 
//	cust1.purchaseCoupon(coupon1); // good for buy
//	cust1.purchaseCoupon(coupon2); // good buy 
//	cust1.purchaseCoupon(coupon3); // good for but
//	cust1.purchaseCoupon(coupon4); // Expired 
	
//	cust2.purchaseCoupon(coupon); // expired coupon , denied to be purchased 
//	cust2.purchaseCoupon(coupon1); // good for buy
//	cust2.purchaseCoupon(coupon2); // good buy 
//	cust2.purchaseCoupon(coupon3); // good for but
//	cust2.purchaseCoupon(coupon4); // Expired 
	
	
//									Company Tests
     CompanyFacadeF company = (CompanyFacadeF)system.logIn("company", "Amdocs", "3333");
//	System.out.println(company.sortCouponBy("Type", "Sports"));
//  company.sortCouponBy("Type", "Food").forEach(System.out::println);
//  company.sortCouponBy("Type", "Sports").forEach(System.out::println);
//  company.sortCouponBy("Type", "Health").forEach(System.out::println);
//	System.out.println(company.sortCouponBy("Price" , "15"));
//	System.out.println(company.sortCouponBy("Price" , "260"));
//  company.sortCouponBy("Date" , "2019-02-01").forEach(System.out::println);
//  company.sortCouponBy("Date" , "2016-02-01").forEach(System.out::println);
//	company.updqateCoupon(coupon);
//	company.updqateCoupon(coupon1);
//	company.updqateCoupon(coupon2);
//	company.updqateCoupon(coupon3);
//	company.updqateCoupon(coupon4);
//	
	
//	
//	company.createCoupon(coupon);
//	company.createCoupon(coupon1);
//	company.createCoupon(coupon2);
//	company.createCoupon(coupon3);
//	company.createCoupon(coupon4);
//	
//	System.out.println(company.getCouponById(1));
//	System.out.println(company.getAllCoupons());
	//company.createCoupon(coupon);
	
	
//											Admin Tests 
	AdminFacadeF admin = (AdminFacadeF) system.logIn("admin", "admin", "1234");
//	admin.createCompany(testCompany);
//	admin.createCompany(testCompany1);
//	admin.createCompany(testCompany2);
//	admin.createCompany(testCompany3);
//	admin.createCompany(testCompany4);
//	
//	admin.addCustomer(customer);
//	admin.addCustomer(customer1);
//	admin.addCustomer(customer2);
//	admin.addCustomer(customer3);
//	admin.addCustomer(customer4);
	
//	admin.removeCompany(testCompany);
//	admin.removeCompany(testCompany1);
//	admin.removeCompany(testCompany2);
//	admin.removeCompany(testCompany3);
//	admin.removeCompany(testCompany4);
//	admin.removeCompany(testCompany);
	
//	admin.removeCustomer(customer);
//	admin.removeCustomer(customer1);
//	admin.removeCustomer(customer2);
//	admin.removeCustomer(customer3);
//	admin.removeCustomer(customer4);
	
	//get all companies - works 
//	admin.companyDetailsUpdate(testCompany3);
//	System.out.println(admin.getAllCompanies());
	
//	System.out.println(admin.getCustomerList());
}
}
