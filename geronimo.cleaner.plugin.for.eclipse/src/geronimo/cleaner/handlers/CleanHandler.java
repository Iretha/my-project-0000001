package geronimo.cleaner.handlers;

import geronimo.cleaner.exc.CleanerException;
import static geronimo.cleaner.GeronimoProjectCleaner.*;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 * @author MBD
 */
public class CleanHandler extends AbstractHandler {

	/**
	 * The constructor.
	 */
	public CleanHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		String msg = null;
		boolean rowsDeleted = false;
		boolean cacheFolderDeleted = false;
		try {
			String geronimoLocation = getEnvironmentProp(GERONIMO_HOME);
			if (geronimoLocation != null) {
				rowsDeleted = clearConfigFile(geronimoLocation);
				cacheFolderDeleted = clearRepositoryDir(geronimoLocation);
			} else {
				throw new CleanerException("Environment variable not found: " + GERONIMO_HOME
						+ ".");
			}
		} catch (CleanerException e) {
			msg = e.getMessage();
		}
		if (msg != null) {
			MessageDialog.openError(window.getShell(), "Geronimo Cleaner", msg);
		} else {
			MessageDialog.openInformation(window.getShell(), "Geronimo Cleaner", "Done! "
					+ "Config file cleaned: " + rowsDeleted + " Cache folder cleaned: "
					+ cacheFolderDeleted);
		}
		return null;
	}

	/**
	 * Връща environment пропертитата
	 * 
	 * @return Properties
	 */
	public String getEnvironmentProp(String propKey) {
		if (System.getenv().containsKey(propKey)) {
			return System.getenv(propKey);
		}
		return null;
	}
}
