package CouponSystem;

import java.sql.Date;
import java.util.Collection;
import beans.Coupon;
import couponSystemException.CouponSystemException;
import dao.HelperMethodsDAO;

// TODO: Auto-generated Javadoc
/**
 * Thread used for removing (delete expired coupons). Compares each coupon 
 * END_Date to the current date if coupon is expired will be removed from: - - -
 * Runs every 24h. Could be adjusted with sleep Argument. 
 */
public class DailyCouponExpirationTask implements Runnable {

	/** The quit. */
	boolean quit = false;

	/**
	 * Instantiates a new daily coupon expiration task.
	 *
	 * @param quit the quit - Quit flag is used to stop the thread looping
	 */
	public DailyCouponExpirationTask(boolean quit) {
		super();
		this.quit = quit;

	}

	/**
	 * Checks if is quit. Flag is used to stop the thread work. Quit is true - stops
	 * the thread
	 * 
	 * @return true, if is quit
	 */
	public boolean isQuit() {
		return quit;
	}

	/**
	 * Sets the quit.
	 *
	 * @param quit the new quit
	 */
	public void setQuit(boolean quit) {
		this.quit = quit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		HelperMethodsDAO helper = new HelperMethodsDAO();
		while (quit == false) {
			Collection<Coupon> expiredCoupons = null;
			long time = System.currentTimeMillis();
			Date date = new Date(time);
			System.out.println(date);
			try {
				expiredCoupons = helper.getCouponSelected(0, "Expired", String.valueOf(date));
			} catch (CouponSystemException e2) {

			}
			for (Coupon coupon : expiredCoupons) {
				try {
					helper.removeWhere("COUPON", "ID", coupon.getId());
					helper.removeWhere("COMPANY_COUPON", "COUPON_ID", coupon.getId());
					helper.removeWhere("CUSTOMER_COUPON", "COUPON_ID", coupon.getId());
				} catch (CouponSystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				Thread.sleep(10000); // delay between each "Clean" 24h
			} catch (InterruptedException e) {
				try {
					throw new CouponSystemException("Expiration Coupons cleaner failed", e);
				} catch (CouponSystemException e1) {

				}
			}
		}
	}

}
