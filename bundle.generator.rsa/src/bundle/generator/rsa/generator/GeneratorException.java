package bundle.generator.rsa.generator;

/**
 * @author MBD
 */
public class GeneratorException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2339874158401837727L;

	/**
	 * @param msg
	 *            - Съобщение
	 */
	public GeneratorException(String msg) {
		super(msg);
	}

	/**
	 * @param arg0
	 *            - грешка
	 */
	public GeneratorException(Throwable arg0) {
		super(arg0);
	}
}
