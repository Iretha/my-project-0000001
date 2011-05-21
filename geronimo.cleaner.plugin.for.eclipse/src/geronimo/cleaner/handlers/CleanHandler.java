package geronimo.cleaner.handlers;

import geronimo.cleaner.CleanerException;
import geronimo.cleaner.GeronimoProjectCleaner;

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
		try{
			runCleaner();
		}catch (CleanerException e) {
			msg = e.getMessage();
		}
		MessageDialog.openInformation(window.getShell(), "Geronimo Cleaner", msg != null ? msg : "Well done!");
		return null;
	}

	private void runCleaner() throws CleanerException {
		String geronimoLocation = getEnvironmentPropGERONIMO_HOME();
		if (geronimoLocation != null) {
			GeronimoProjectCleaner.clearConfigFile(geronimoLocation);
			GeronimoProjectCleaner.clearRepositoryDir(geronimoLocation);
		} else {
			throw new ClassCastException("Не е открита променлива за "
					+ GeronimoProjectCleaner.GERONIMO_HOME + ".");
		}
	}

	/**
	 * Връща environment пропертитата
	 * 
	 * @return Properties
	 */
	public String getEnvironmentPropGERONIMO_HOME() {
		if (System.getenv().containsKey(GeronimoProjectCleaner.GERONIMO_HOME)) {
			return System.getenv(GeronimoProjectCleaner.GERONIMO_HOME);
		}
		return null;
	}
}
