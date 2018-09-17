package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import beans.Customer;
import couponSystemException.CouponSystemException;
import dbConnectionPool.ConnectionPool;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerDBDAO.
 */
public class CustomerDBDAO implements CustomerDAO {
	
	/** 
	 * @see dao.CustomerDAO#createCustomer(beans.Customer)
	 */
	@Override 
	public void createCustomer(Customer customer) throws CouponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		String sql = "INSERT INTO Customer (ID , CUST_NAME, PASSWORD) VALUES (? , ? , ?)";
		try {
		PreparedStatement st = con.prepareStatement(sql);
		st.setLong(1, customer.getId());
		st.setString(2, customer.getCustName());
		st.setString(3, customer.getPassword());
		st.executeUpdate();
		} catch (SQLException e) {
		throw new CouponSystemException ("Failed to create new Customer"); 
		}finally {pool.returnConnection(con);}
		
	}

	/**
	 * @see dao.CustomerDAO#removeCustomer(beans.Customer)
	 */
	@Override 
	public void removeCustomer(Customer customer) throws CouponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		String sql = "DELETE  FROM CUSTOMER WHERE ID=" + customer.getId(); 
		Statement st;
		try {
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			throw new CouponSystemException("Failed to delete Customer with id = " + customer.getId() + " ");
		} finally {pool.returnConnection(con);}

		
		
		
	}

	/** 
	 * @see dao.CustomerDAO#updateCustomer(beans.Customer)
	 */
	@Override 
	public void updateCustomer(Customer customer) throws CouponSystemException {
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		String sql = "UPDATE CUSTOMER  SET  ID =  ? , CUST_NAME  = ? , PASSWORD = ?  WHERE ID= "  + customer.getId() ; 
		try {
		PreparedStatement  st = con.prepareStatement(sql);
		st.setLong(1, customer.getId());
		st.setString(2, customer.getCustName());
		st.setString(3, customer.getPassword());
		st.executeUpdate();
		} catch (SQLException e) {
		throw new CouponSystemException("Failed to updated customer with id = " + customer.getId() + " ");
		} finally {pool.returnConnection(con);}
	}

	/**
	 * @see dao.CustomerDAO#getCustomer(long)
	 */
	@Override
	public Customer getCustomer(long id) throws CouponSystemException {
		Customer customer = new Customer(id, null, null);
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		String query = "SELECT * FROM CUSTOMER WHERE id = " + id ; 
		Connection con = pool.getConnection();
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
			if (rs.next()) {
				customer.setCustName(rs.getString("CUST_NAME"));
				customer.setPassword(rs.getString("PASSWORD"));
				customer.setId(id);
			}
			else {
				System.out.println("Customer with id = " +  id + " does not exsist");
			}
		} catch (SQLException e) {
			throw new CouponSystemException ("Failed to get Customer with id=" + id  ) ; 
		}finally {pool.returnConnection(con);}
		
		return customer;
	}

	/**
	 * @see dao.CustomerDAO#getAllCustomers()
	 */
	@Override
	public Collection<Customer> getAllCustomers() throws CouponSystemException {
		Collection<Customer> customerList = new HashSet<Customer>();		
		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con = pool.getConnection();
		String query = "SELECT * FROM CUSTOMER";
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
			customerList.add(new Customer(rs.getLong("ID"), rs.getString("CUST_NAME"), rs.getString("PASSWORD")));
			}
			} catch (SQLException e) {
			throw new CouponSystemException ("Failed to get all Customers from data base");
		} finally {pool.returnConnection(con);}
		
		return customerList;
	}

	/**
	 * @see dao.CustomerDAO#login(java.lang.String, java.lang.String)
	 * Not used see {@link dao.HelperMethods#logIn(String, String, String)}
	 */
	@Override
	public boolean login(String custName, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
