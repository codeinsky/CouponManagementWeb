package couponSystemException;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.SQLException;
// TODO: Auto-generated Javadoc

/**
 * The Class CuponSystemException.
 *
 * CuponSystemException class, extends Exception class 
 */
public class CouponSystemException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * CouponSystme Exception class constructor with string message ; .
	 *
	 * @param string - gets message
	 */
	public CouponSystemException(String string) {
		System.out.println(string);
	}

	/**
	 * CuponSystemException class constructor with exception e .
	 *
	 * @param string - exception message
	 * @param e   - Interrupted exception
	 * Used for check , receives Interrupt Exception details
	 */
	public CouponSystemException(String string, InterruptedException e) {
		System.out.println(string + e);
	}

	/**
	 * CouponSystemException class constructor with SQL exception e .
	 *
	 * @param string - Exception Message
	 * @param e - Exception
	 * Used for checks , receives Exception Details
	 */
	public CouponSystemException(String string, SQLException e) {
		System.out.println(string + e);
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#fillInStackTrace()
	 */
	@Override
	public synchronized Throwable fillInStackTrace() {
		// TODO Auto-generated method stub
		return super.fillInStackTrace();
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getCause()
	 */
	@Override
	public synchronized Throwable getCause() {
		// TODO Auto-generated method stub
		return super.getCause();
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getLocalizedMessage()
	 */
	@Override
	public String getLocalizedMessage() {
		// TODO Auto-generated method stub
		return super.getLocalizedMessage();
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getStackTrace()
	 */
	@Override
	public StackTraceElement[] getStackTrace() {
		// TODO Auto-generated method stub
		return super.getStackTrace();
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#initCause(java.lang.Throwable)
	 */
	@Override
	public synchronized Throwable initCause(Throwable arg0) {
		// TODO Auto-generated method stub
		return super.initCause(arg0);
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace()
	 */
	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		super.printStackTrace();
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace(java.io.PrintStream)
	 */
	@Override
	public void printStackTrace(PrintStream arg0) {
		// TODO Auto-generated method stub
		super.printStackTrace(arg0);
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace(java.io.PrintWriter)
	 */
	@Override
	public void printStackTrace(PrintWriter arg0) {
		// TODO Auto-generated method stub
		super.printStackTrace(arg0);
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#setStackTrace(java.lang.StackTraceElement[])
	 */
	@Override
	public void setStackTrace(StackTraceElement[] arg0) {
		// TODO Auto-generated method stub
		super.setStackTrace(arg0);
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	// test
	/*
	 * public void deviation() { System.out.println("Pls do not 0 for deviation"); }
	 */

	/**
	 * Data base.
	 */
	public void dataBase() {
		System.out.println("Issue with Data Base connection ");
	}
}
