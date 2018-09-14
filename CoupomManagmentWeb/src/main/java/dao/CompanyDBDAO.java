package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import beans.Company;
import beans.Coupon;
import couponSystemException.CouponSystemException;
import dbConnectionPool.ConnectionPool;
// TODO: Auto-generated Javadoc

/**
 * The Class CompanyDBDAO.
 *
 * CompanyDBDAO class includes methods based on CompanyDAO interface. Operations
 * with DataBase Company table
 */
public class CompanyDBDAO implements CompanyDAO {

	@Override
	/**
	 * 
	 * CreateCompanyis a void method receives Company bean. Creates new row in
	 * Company table and inputs all Company attributes.
	 * 
	 * @see dao.CompanyDAO#createCompany(beans.Company)
	 * @param - Company bean.
	 * @throws - CuponSystemException
	 */
	public void createCompany(Company company) throws CouponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		String createCompanySt = "INSERT INTO COMPANY(ID, COMP_NAME , PASSWORD , EMAIL ) VALUES (?,?,?,?) ";
		Connection con = null;
		con = pool.getConnection();
		PreparedStatement state;

		try {
			state = con.prepareStatement(createCompanySt);
			state.setLong(1, company.getId());
			state.setString(2, company.getCompName());
			state.setString(3, company.getPassword());
			state.setString(4, company.getEmail());
			state.executeUpdate();
		} catch (SQLException e) {
			throw new CouponSystemException("Failed to add ne compnay in DataBase ");
		} finally {
			pool.returnConnection(con);
		}

	}

	@Override

	/**
	 * RemoveCompany is a void method receives Company bean. Looks for existing
	 * company in DataBase Company table per Company ID. Deletes the company from
	 * DataBase Company Table.
	 * 
	 * @see dao.CompanyDAO#removeCompany(beans.Company)
	 * @param - Company bean
	 * @throws - CuponSystemException
	 */
	public void removeCompany(Company company) throws CouponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		String removeCompanySt = "DELETE FROM COMPANY WHERE ID=? ";
		Connection con = null;
		try {
			con = pool.getConnection();
			PreparedStatement state;
			state = con.prepareStatement(removeCompanySt);
			state.setLong(1, company.getId());
			state.executeUpdate();
			System.out.println("company removed");
		} catch (SQLException e) {
			throw new CouponSystemException("Failed to remove the company");
		} finally {
			pool.returnConnection(con);
		}

	}

	@Override
	/**
	 * 
	 * UpdateCompany is a void method receives Company bean. Looks for exiting
	 * Company in DataBase Company table with same ID and all attributes except ID.
	 * 
	 * @see dao.CompanyDAO#updateCompany(beans.Company)
	 * @param - Company bean
	 * @throws - CuponSystemException
	 */
	public void updateCompany(Company company) throws CouponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		String updateCompany = "UPDATE COMPANY  SET COMP_NAME = ? , PASSWORD = ? , EMAIL = ?  WHERE ID= ?";
		PreparedStatement state;
		try {
			state = con.prepareStatement(updateCompany);
			state.setString(1, company.getCompName());
			state.setString(2, company.getPassword());
			state.setString(3, company.getEmail());
			state.setLong(4, company.getId());
			state.executeUpdate();
		} catch (SQLException e) {
			throw new CouponSystemException("Failed to update the Copmany");
		} finally {
			pool.returnConnection(con);
		}
	}

	@Override
	/**
	 * GetCompany is a method receives Company ID. Search for the existing company
	 * in DataBase Company table with same ID. Returns Company bean.
	 * 
	 * @see dao.CompanyDAO#getCompany(long)
	 * @param - ID long
	 * @returns - Company bean
	 * @throws - CuponSystemException
	 */
	public Company getCompany(long id) throws CouponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		Company company = new Company();
		Statement st;

		try {
			st = con.createStatement();
			String query = "SELECT * FROM COMPANY WHERE ID=" + id;
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				company.setId(id);
				company.setCompName(rs.getString("COMP_NAME"));
				company.setPassword(rs.getString("PASSWORD"));
				company.setEmail(rs.getString("EMAIL"));
			} else {
				throw new CouponSystemException("Copmany does not exist in the system");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException("Failed to parse DataBase Result ");
		} finally {
			pool.returnConnection(con);
		}
		return company;
	}

	@Override
	/**
	 * GetAllCopmanies is a Method returns Company Collection - all existing
	 * companies in DataBase company table
	 * 
	 * @see dao.CompanyDAO#getAllCompanies()
	 * @returns - Company Collection
	 * @throws - CuponSystemException
	 */
	public Collection<Company> getAllCompanies() throws CouponSystemException {
		Collection<Company> companyList = new HashSet<Company>();
		String query = "SELECT * FROM  COMPANY";
		ResultSet rs;
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		try {
			Statement state = con.createStatement();
			rs = state.executeQuery(query);
			while (rs.next()) {
				companyList.add(new Company(rs.getLong("ID"), rs.getString("COMP_NAME"), rs.getString("PASSWORD"),
						rs.getString("EMAIL")));
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Failed to get all Companies from DATA BASE");
		} finally {
			pool.returnConnection(con);
		}
		return companyList;
	}

	@Override
	/**
	 * GetCopuns method receives Company bean. Puts all Coupons ID belongs to the
	 * company in ID Collection (couponIdList).Based on ID collection method
	 * collects all coupons beans to new collection and returns it.
	 * 
	 * @see dao.CompanyDAO#getCoupons(beans.Company)
	 * @param - Company bean
	 * @returns - Coupon Collection.
	 * @throws - CuponSystemException
	 */

	public Collection<Coupon> getCoupons(Company company) throws CouponSystemException {
		Collection<Coupon> couponList = new HashSet<Coupon>(); // Collection of coupons returned by the method
		Collection<Long> couponIdList = new HashSet<Long>(); // List with all coupons ID's belong to Company
		String queryJoinTable = "SELECT * FROM COMPANY_COUPON WHERE COUPON_ID = " + company.getId();
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(queryJoinTable);
			while (rs.next()) {
				couponIdList.add(rs.getLong("COUPON_ID"));
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Failed to get Coupons from Data Base");
		} finally {
			pool.returnConnection(con);
		}
		// getting coupon objects from Coupon DB table per Company
		for (long id : couponIdList) {
			CuoponDBDAO getCop = new CuoponDBDAO();
			couponList.add(getCop.getCoupon(id));
		}
		if (couponList.size() == 0) {
			System.out.println("No any Coupons associated with Company :" + company.getCompName());
		}
		return couponList;

	}

	@Override

	/**
	 * LogIn method receives Company Name and Password. Looks for existing company
	 * in DataBase Company table and check if password is equal to received
	 * password. If yes returns TRUE:FALSE.
	 * 
	 * @see dao.CompanyDAO#logIn(java.lang.String, java.lang.String)
	 * @param - String Company Name , String Password
	 * @returns - Boolean (logged , failed)
	 * @throws - CuponSystemException
	 */
	public Boolean logIn(String compName, String passowrd) throws CouponSystemException {
		Boolean result = false;
		String query = "SELECT PASSWORD FROM COMPANY WHERE COMP_NAME =" + compName;
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		Statement state;
		try {
			state = con.createStatement();
		} catch (SQLException e) {
			throw new CouponSystemException("Company name did not found, pls try again");
		}
		ResultSet rs;
		try {
			rs = state.executeQuery(query);
		} catch (SQLException e) {
			throw new CouponSystemException("Failed to get user details from Data Base");
		}

		try {
			if (rs.getString("PASSWORD").equals(passowrd)) {
				result = true;
			} else {
				System.out.println("Wrong password , pls try again");
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Failed to retrive password from DATA BASE");
		} finally {
			pool.returnConnection(con);
		}
		return result;
	}

}
