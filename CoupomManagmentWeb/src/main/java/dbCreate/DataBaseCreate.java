		// table creation 
	//String sql = "Create table Company(ID BIGINT NOT NULL , COMP_NAME VARCHAR(30), PASSWORD VARCHAR(10), EMAIL VARCHAR(20) , PRIMARY KEY(ID))";
	// String sql = "Create table Customer(ID BIGINT NOT NULL , CUST_NAME VARCHAR(30) , PASSWORD VARCHAR(10),PRIMARY KEY(ID))";
	// String sql = "Create table Coupon(ID BIGINT NOT NULL , TITLE VARCHAR(20) , START_DATE DATE, END_DATE DATE , AMOUNT INT , TYPE VARCHAR (20), MESSAGE VARCHAR(20) , PRICE DOUBLE , IMAGE VARCHAR (50) ,PRIMARY KEY(ID))";
	// String sql = "Create table Customer_Coupon(Customer_ID BIGINT , Coupon_ID BIGINT , PRIMARY KEY(Customer_ID , Coupon_ID  ))";
	// String sql = "Create table Company_Coupon(Company_ID BIGINT , Coupon_ID BIGINT,  PRIMARY KEY(Company_ID , Coupon_ID))";
	// String sql = "Create table Company_ID (Company_ID BIGINT)";
	// String sql="Insert into Company_ID  values(1)"; // initialization first ID  	
	//	String sql = "Create table CUSTOMER_ID (CUSTOMER_ID BIGINT)";
	//	String sql=" "; // initialization first ID
	//	String sql = "Create table COUPON_ID (COUPON_ID BIGINT)";
	//	String sql="Insert into COUPON_ID values(1)"; // initialization first ID



package dbCreate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import couponSystemException.CouponSystemException;
import dbConnectionPool.ConnectionPool;

// TODO: Auto-generated Javadoc
/**
 * The Class DataBaseCreate.
 * Creates all SQL tables needed for running the CouponSystem
 */
public class DataBaseCreate {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		String[] queryes = new String[11];
		queryes[0]="Create table Company(ID BIGINT NOT NULL , COMP_NAME VARCHAR(30), PASSWORD VARCHAR(10), EMAIL VARCHAR(20) , PRIMARY KEY(ID))";
		queryes[1]="Create table Customer(ID BIGINT NOT NULL , CUST_NAME VARCHAR(30) , PASSWORD VARCHAR(10),PRIMARY KEY(ID))";
		queryes[2]="Create table Coupon(ID BIGINT NOT NULL , TITLE VARCHAR(20) , START_DATE DATE, END_DATE DATE , AMOUNT INT , TYPE VARCHAR (20), MESSAGE VARCHAR(20) , PRICE DOUBLE , IMAGE VARCHAR (50) ,PRIMARY KEY(ID))";
		queryes[3]="Create table Customer_Coupon(Customer_ID BIGINT , Coupon_ID BIGINT , PRIMARY KEY(Customer_ID , Coupon_ID  ))";
		queryes[4]="Create table Company_Coupon(Company_ID BIGINT , Coupon_ID BIGINT,  PRIMARY KEY(Company_ID , Coupon_ID))";
		queryes[5]="Create table Company_ID (Company_ID BIGINT)";
		queryes[6]="Insert into Company_ID  values(1)";
		queryes[7]="Create table CUSTOMER_ID (CUSTOMER_ID BIGINT)";
		queryes[8]="Insert into CUSTOMER_ID values(1)";
		queryes[9]="Create table COUPON_ID (COUPON_ID BIGINT)";
		queryes[10]="Insert into COUPON_ID values(1)";
		
		for (int i = 0; i < queryes.length; i++) {
		String sql	= queryes[i];
		
		
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con=null;
		try {
			con = pool.getConnection();
		} catch (CouponSystemException e1) {
			
		}
		
		java.sql.Statement st;
		try {
			st = con.createStatement();
			st.executeUpdate(sql);
			System.out.println(i + " Table created");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.returnConnection(con);
		System.out.println(pool.connectionAmoutCheck());
		}

}
}
