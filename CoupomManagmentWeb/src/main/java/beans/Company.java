package beans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

// TODO: Auto-generated Javadoc
/**
 * The Class Company.
 * Company Class 
 *  - Attributes : id , compName , password , email, collection for Coupons; 
 *  - Coupon Collection : not used; instead exists Method "get all coupons" from DataBase 
 *  - Constructor with each attribute 
 *  - Getters and setters for each Attribute 
 */
@XmlRootElement 
public class Company implements Serializable  {
	
	/** The id. */
	private long id;
	
	/** The comp name. */
	private String compName;
	
	/** The password. */
	private String password;
	
	/** The email. */
	private String email;
	
	/** The coupon. */
	private Collection<Coupon> coupon = new ArrayList<Coupon>();

	/**
	 * Instantiates a new company.
	 */
	public Company() {
		super();
	}

	/**
	 * Instantiates a new company.
	 *
	 * @param id the id
	 * @param compName the comp name
	 * @param password the password
	 * @param email the email
	 */
	public Company(long id, String compName, String password, String email) { // if need construct to collection ??
		super();
		this.id = id;
		this.compName = compName;
		this.password = password;
		this.email = email;
		// this.coupon = coupon;
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
	 * Gets the comp name.
	 *
	 * @return the comp name
	 */
	public String getCompName() {
		return compName;
	}

	/**
	 * Sets the comp name.
	 *
	 * @param compName the new comp name
	 */
	public void setCompName(String compName) {
		this.compName = compName;
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

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email
				+ ", coupon=" + coupon + "]";
	}

}
