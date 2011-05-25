/**
 * TODO
 */
package bundle.generator.rsa.handlers;

import static bundle.generator.rsa.generator.BundleGenerator.SOURCE_FILE_EXTENSION;
import static bundle.generator.rsa.generator.BundleGenerator.generateBundleFiles;
import static org.eclipse.jface.dialogs.MessageDialog.openError;
import static org.eclipse.jface.dialogs.MessageDialog.openInformation;
import static org.eclipse.jface.dialogs.MessageDialog.openQuestion;
import static org.eclipse.ui.handlers.HandlerUtil.getActiveMenuSelection;
import static org.eclipse.ui.handlers.HandlerUtil.getActiveShell;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.internal.resources.File;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.DirectoryDialog;

import bundle.generator.rsa.generator.GeneratorException;

/**
 * @author MBD
 * 
 */
@SuppressWarnings("restriction")
public class GeneratorHandler extends AbstractHandler {

	/**
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection) getActiveMenuSelection(event);
		File firstElement = (File) selection.getFirstElement();
		IPath sourceFilePath = firstElement.getLocation();
		if (sourceFilePath.segmentCount() < 2) {
			openError(getActiveShell(event), "Error", "File is not valid!");
		} else {
			if (firstElement.getFileExtension().equalsIgnoreCase(SOURCE_FILE_EXTENSION)) {
				IPath destinationDirectoryPath = sourceFilePath.uptoSegment(sourceFilePath
						.segmentCount() - 1);
				String destinationDirectory = destinationDirectoryPath.toOSString();
				boolean useCurrentDestination = openQuestion(getActiveShell(event), "Question",
						"Use current output directory? " + destinationDirectory);
				if (!useCurrentDestination) {
					destinationDirectory = new DirectoryDialog(getActiveShell(event)).open();
				}
				if (destinationDirectory != null && destinationDirectory.length() > 0) {
					try {
						generateBundleFiles(destinationDirectory, sourceFilePath.toOSString());
						openInformation(getActiveShell(event), "Information",
								"All Done! Please refresh destination directory to view generated files!");
					} catch (GeneratorException e) {
						openError(getActiveShell(event), "Runtime Exception", e.getMessage());
					}
				}
			} else {
				openInformation(getActiveShell(event), "Information",
						"Please select a \"txt\" source file!");
			}
		}
		return null;
	}
}
