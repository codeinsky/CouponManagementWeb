package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import beans.Coupon;
import beans.CouponType;
import couponSystemException.CouponSystemException;
import dbConnectionPool.ConnectionPool;
import facades.AdminFacadeF;
import facades.CompanyFacadeF;
import facades.CustomerFacadeF;
import facades.ClientCouponFacade;

// TODO: Auto-generated Javadoc
/**
 * The Class HelperMethodsDAO.
 */
public class HelperMethodsDAO implements HelperMethods {

	/** 
	 * @see dao.HelperMethods#ifExist(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean ifExist(String sqlTable, String column, String item) throws CouponSystemException {
		boolean result = false;
		String sql = "SELECT * FROM " + sqlTable + "  WHERE " + column + " =  ?";
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, item);
			ResultSet rs;
			rs = st.executeQuery();
			if (rs.next()) {
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Failed to calculate the query ");
		} finally {
			pool.returnConnection(con);
		}
		return result;
	}

	/** 
	 * @see dao.HelperMethods#removeWhere(java.lang.String, java.lang.String, long)
	 */
	@Override
	public void removeWhere(String sqlTable, String column, long id) throws CouponSystemException {
		String sql = "DELETE FROM " + sqlTable + " WHERE " + column + " = ?";
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException("Failed to delete needed coupon ");
		} finally {
			pool.returnConnection(con);
		}

	}

	/** 
	 * @see dao.HelperMethods#getCouponsBelongTo(java.lang.String, java.lang.String, java.lang.String, long)
	 */
	@Override
	public Collection<Long> getCouponsBelongTo(String getColumn, String sqlTable, String whereColumn, long id)
			throws CouponSystemException {
		Collection<Long> couponsList = new HashSet<Long>();
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		try {
			String sql = "SELECT " + getColumn + " FROM " + sqlTable + " WHERE " + whereColumn + " = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				couponsList.add(rs.getLong(getColumn));
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Failed to execute the SQL query ");
		} finally {
			pool.returnConnection(con);
		}
		return couponsList;
	}

	/** 
	 * @see dao.HelperMethods#getCouponSelected(long, java.lang.String, java.lang.String)
	 */
	@Override
	public Collection<Coupon> getCouponSelected(long id, String select, String refernce) throws CouponSystemException {
		Collection<Coupon> selectedCoupons = new HashSet<Coupon>();
		String sql = null;
		switch (select) {
		case "Expired":
			sql = "SELECT * FROM COUPON WHERE END_DATE < '" + refernce + "'";
			System.out.println(sql);
			break;
		case "Type":
			sql = "SELECT * FROM COUPON " + "INNER JOIN COMPANY_COUPON " + "ON Coupon.ID=company_coupon.COUPON_ID "
					+ "WHERE TYPE = '" + refernce + "' " + "AND company_id = " + id;
			break;

		case "Price":
			sql = "SELECT * FROM COUPON " + "INNER JOIN COMPANY_COUPON " + "ON Coupon.ID=company_coupon.COUPON_ID "
					+ "WHERE PRICE < " + refernce + "AND company_id = " + id;
			break;

		case "Date":
			sql = "SELECT * FROM COUPON " + "INNER JOIN COMPANY_COUPON " + "ON Coupon.ID=company_coupon.COUPON_ID "
					+ "WHERE END_DATE < '" + refernce + "' AND company_id = " + id;
			break;

		case "customerCouponsByPrice":
			sql = "SELECT * FROM COUPON " + "INNER JOIN CUSTOMER_COUPON " + "ON Coupon.ID=customer_coupon.COUPON_ID "
					+ "WHERE PRICE < " + refernce + "AND customer_id = " + id;
			break;

		case "customerCouponsByDate":
			sql = "SELECT * FROM COUPON " + "INNER JOIN CUSTOMER_COUPON " + "ON Coupon.ID=customer_coupon.COUPON_ID "
					+ "WHERE END_DATE < '" + refernce + "' AND customer_id = " + id;
			break;

		case "customerCouponsByType":
			sql = "SELECT * FROM COUPON " + "INNER JOIN CUSTOMER_COUPON " + "ON Coupon.ID=customer_coupon.COUPON_ID "
					+ "WHERE TYPE = '" + refernce + "' " + "AND customer_id = " + id;
			break;

		}

		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				selectedCoupons.add(new Coupon(rs.getLong("ID"), rs.getString("TITLE"), rs.getDate("START_DATE"),
						rs.getDate("END_DATE"), rs.getInt("AMOUNT"), CouponType.valueOf(rs.getString("TYPE")),
						rs.getString("MESSAGE"), rs.getDouble("PRICE"), rs.getString("IMAGE")));
			}
		} catch (SQLException e) {
			throw new CouponSystemException ("Failed filtering coupons from Data Base"); 
		} finally {
			pool.returnConnection(con);
		}

		return selectedCoupons;
	}

	/** 
	 * @see dao.HelperMethods#buyCoupon(java.lang.String, java.lang.String)
	 */
	@Override
	public void buyCoupon(String customerId, String couponId) throws CouponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		try {
			String sql = "INSERT INTO CUSTOMER_COUPON VALUES(" + customerId + "," + couponId + ")";
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			throw new CouponSystemException("Failed updateing coupon purchase ", e);
		} finally {
			pool.returnConnection(con);
		}

	}

	/** 
	 * @see dao.HelperMethods#createCopuon(java.lang.String, java.lang.String)
	 */
	@Override
	public void createCopuon(String companyId, String couponId) throws CouponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		try {
			String sql = "INSERT INTO COMPANY_COUPON VALUES(" + companyId + "," + couponId + ")";
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			throw new CouponSystemException("Failed updateing coupon purchase ", e);
		} finally {
			pool.returnConnection(con);
		}

	}

	/** 
	 * @see dao.HelperMethods#logIn(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ClientCouponFacade logIn(String userType, String userName, String password) throws CouponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		ClientCouponFacade facade = null;
		try {
			switch (userType) {
			case "admin": {
				if (userName.equals("admin") && password.equals("1234")) {
					facade = new AdminFacadeF();
					System.out.println("Welcome Admin");
				}
				else {
					System.out.println("wrong user name or password , please try again");
				}
				pool.returnConnection(con);
				break;
			}
			case "company": {
				String sql = "SELECT ID , PASSWORD  FROM COMPANY WHERE COMP_NAME='" + userName + "'";
				Statement st;
				st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				if (rs.next()) {
					if (rs.getString("Password").equals(password)) {
						facade = new CompanyFacadeF(rs.getLong("ID"));
						System.out.println("You are LoggedIN");
					} else {
						System.out.println("Wrong Password , please try again");
					}
				} else {
					System.out.println("Wrong User Name doesn't exist, please try again");
				}
				pool.returnConnection(con);
				break;
			}
			case "customer": {
				String sql = "SELECT ID , PASSWORD  FROM CUSTOMER WHERE CUST_NAME='" + userName + "'";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				if (rs.next()) {
					if (rs.getString("Password").equals(password)) {
						facade = new CustomerFacadeF(rs.getLong("ID"));
						System.out.println("You are LoggedIN");
					} else {
						System.out.println("Wrong Password, please try again");
					}
				} else {
					System.out.println("User do not exists, please try again");

				}
				pool.returnConnection(con);
			}
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Failed to LogON, please try again", e);
		}
		return facade;
	}

	/** 
	 * @see dao.HelperMethods#getId(java.lang.String)
	 */
	@Override
	public long getId(String typeId) throws CouponSystemException {
		long id = 0;
		String sql = null;
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		switch (typeId) {
		case "COUPON_ID":
			sql = "SELECT MAX (COUPON_ID) FROM COUPON_ID";
			break;
		case "CUSTOMER_ID":
			sql = "SELECT MAX (CUSTOMER_ID) FROM CUSTOMER_ID";
			break;
		case "COMPANY_ID":
			sql = "SELECT MAX (COMPANY_ID) FROM COMPANY_ID";
			break;
		}
		try {
			// gets last ID exists in the table
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				id = rs.getLong(1);
			}
			// increments new ID
			String sqlUpdate = "INSERT INTO " + typeId + " VALUES(" + (id + 1) + ")";
			st.executeUpdate(sqlUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException("Failed to crete new ID ", e);
		} finally {
			pool.returnConnection(con);
		}
		//

		return id;
	}

}
