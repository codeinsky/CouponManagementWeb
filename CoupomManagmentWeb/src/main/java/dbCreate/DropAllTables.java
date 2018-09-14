package dbCreate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import couponSystemException.CouponSystemException;
import dbConnectionPool.ConnectionPool;

// TODO: Auto-generated Javadoc
/**
 * The Class DropAllTables.
 */
public class DropAllTables {

	/**
	 * The main method.
	 * MAIN method DELETE(DROPS) ALL SQL TABLES in case or reset of the system or for tests
	 * @param args the arguments
	 * @throws SQLException the SQL exception
	 * @throws CouponSystemException the coupon system exception
	 */
	public static void main(String[] args) throws SQLException, CouponSystemException {
		// TODO Auto-generated method stub
		String[] statements = new String[8];
		statements[0]="DROP TABLE Company ";
		statements[1]="DROP TABLE Customer";
		statements[2]="DROP TABLE Coupon";
		statements[3]="DROP TABLE Customer_Coupon";
		statements[4]="DROP TABLE Company_Coupon";
		statements[5]="DROP TABLE Company_ID";
		statements[6]="DROP TABLE CUSTOMER_ID";
		statements[7]="DROP TABLE COUPON_ID";
		String sql=null;
		for (int i = 0; i < statements.length; i++) {
		sql = statements[i];	
		
		Connection con ;
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		con = pool.getConnection();
		Statement st = con.createStatement();
		st.execute(sql);
		pool.returnConnection(con);
		
		
		}

	}

}
