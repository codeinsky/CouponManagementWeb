package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;
// TODO: Auto-generated Javadoc

/**
 * The Class Customer.
 *
 * 		   Company Class - Attributes : id , custName , password ; 
 *         Constructor with all attributes - Getter and Setters for each
 *         attribute
 */
@XmlRootElement
public class Customer implements Serializable {

	/** The id. */
	private long id;

	/** The cust name. */
	private String custName;

	/** The password. */
	private String password;

	/** The coupon. */
	private Collection<Coupon> coupon = new ArrayList<Coupon>();

	/**
	 * Instantiates a new customer.
	 *
	 * @param id       the id
	 * @param custName the cust name
	 * @param password the password
	 */
	
	public Customer() {
		super();
	}
	public Customer(long id, String custName, String password) {
		super();
		this.id = id;
		this.custName = custName;
		this.password = password;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the cust name.
	 *
	 * @return the cust name
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * Sets the cust name.
	 *
	 * @param custName the new cust name
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [id=" + id + ", custName=" + custName + ", password=" + password + "]";
	}

}
