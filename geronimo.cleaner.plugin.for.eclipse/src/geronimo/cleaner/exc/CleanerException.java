/**
 * 
 */
package geronimo.cleaner.exc;
/**
 * Изключения възникнали по време на изчистване на кешираните приложения.
 * @author MBD
 */
public class CleanerException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2339874158401837727L;

	/**
	 * @param arg0
	 */
	public CleanerException(Throwable arg0) {
		super(arg0);
	}
	
	/**
	 * @param arg0
	 */
	public CleanerException(String arg0) {
		super(arg0);
	}
}
