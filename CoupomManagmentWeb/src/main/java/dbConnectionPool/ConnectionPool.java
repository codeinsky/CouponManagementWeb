package dbConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import couponSystemException.CouponSystemException;

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectionPool.
 */
public class ConnectionPool {

	/** The connection pool. */
	private Set<Connection> connectionPool = new HashSet<>();

	/** The pool back UP. */
	private Set<Connection> poolBackUP = connectionPool; // new reference for connection pool close

	/** The url. */
	String url = "jdbc:derby://localhost:1527/CuponSystemDB;"; // database URL , driver , port

	/** The connection count. */
	private int connectionCount = 10;

	/** The instance. */
	private static ConnectionPool instance;
	// private singleton constructor

	/**
	 * Instantiates a new connection pool.
	 */
	private ConnectionPool() {

		for (int i = 0; i < connectionCount; i++) {
			try {
				connectionPool.add(createConnection());
			} catch (CouponSystemException e) {
			}
		}
	}

	/**
	 * Creates the connection.
	 *
	 * @return the connection
	 * @throws CouponSystemException the coupon system exception
	 */
	// CREATING NEW CONNECTION
	private Connection createConnection() throws CouponSystemException {
		Connection connection = null;
		try {
			Connection conn = DriverManager.getConnection(url);
			connection = conn;
		} catch (SQLException e) {
			throw new CouponSystemException("DataBase connection initialization failed  ", e);
		}
		return connection;
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 * @throws CouponSystemException the coupon system exception
	 */
	// RETRIVING CONNCTION FROM THE POOL
	public synchronized Connection getConnection() throws CouponSystemException {
		try {
			while (connectionPool.isEmpty()) {
				// System.out.println("No connection left , wait"); - used for Connection pool
				// checks
				wait();
			}
		} catch (InterruptedException e) {
			throw new CouponSystemException("Data Base Connection Manager failed", e);
		}
		Iterator<Connection> it = connectionPool.iterator();
		Connection con = it.next();
		connectionPool.remove(con);
		System.out.println(connectionPool.size() + " Connections left in the Pool");
		return con;
	}

	/**
	 * Return connection.
	 *
	 * @param con the con
	 */
	// RETURNING CONNCTION BACK TO THE POOL
	public synchronized void returnConnection(Connection con) {
		connectionPool.add(con);
		notifyAll();
		System.out.println("There is " + connectionPool.size() + " after returning  ");
	}

	/**
	 * Connection amout check.
	 *
	 * @return the int
	 */
	public int connectionAmoutCheck() {
		return connectionPool.size();
	}

	/**
	 * Close connections.
	 *
	 * @throws CouponSystemException the coupon system exception
	 */
	// CLOSEING ALL CONNECTIONS
	// need to update with closing flag to prevent
	// taking connections during close
	public void closeConnections() throws CouponSystemException {
		if (connectionPool.size() < 10) {
			System.out.println("Please do not shut down, data still transfering with data-base");
			try {
				System.out.println("Ten seconds delay to let all connection finish transfer data ");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				throw new CouponSystemException("Connection pool shut down failed", e);
			}
			for (Connection BuCon : poolBackUP) {
				try {
					BuCon.close();
				} catch (SQLException e) {
					throw new CouponSystemException("Connection close failed", e);
				}
			}
		} else {
			for (Connection con : connectionPool) {
				try {
					System.out.println("Connection closed");
					con.close();
				} catch (SQLException e) {
					throw new CouponSystemException("Connection close failed", e);
				}
			}
			System.out.println("All connections are closed");
		}
	}

	/**
	 * Gets the connection pool. Singleton get method
	 * 
	 * @return the connection pool
	 */
	public static ConnectionPool getConnectionPool() {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}
}
