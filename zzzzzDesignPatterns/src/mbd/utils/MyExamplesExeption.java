/**
 * 
 */
package mbd.utils;

/**
 * Грешки, възникнали при разработването на примерите
 * 
 * @author developer0024
 */
public class MyExamplesExeption extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3169458221993037730L;

	/**
	 * @param message
	 */
	public MyExamplesExeption(String message) {
		super(message);
		ServicesManager.getInstance().getLog().info(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MyExamplesExeption(String message, Throwable cause) {
		super(message, cause);
		ServicesManager.getInstance().getLog().info(message);
	}

	/**
	 * @param cause
	 */
	public MyExamplesExeption(Throwable cause) {
		super(cause);
		ServicesManager.getInstance().getLog().info(cause.getMessage());
	}

}
