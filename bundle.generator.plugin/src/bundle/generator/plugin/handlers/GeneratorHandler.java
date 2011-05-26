/**
 * TODO
 */
package bundle.generator.plugin.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.internal.resources.File;
import org.eclipse.core.runtime.IPath;
import static org.eclipse.jface.dialogs.MessageDialog.*;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.DirectoryDialog;
import static org.eclipse.ui.handlers.HandlerUtil.*;

import static bundle.generator.plugin.generator.BundleGenerator.*;
import bundle.generator.plugin.generator.BundleGenerator;
import bundle.generator.plugin.generator.GeneratorException;

/**
 * @author MBD
 * 
 */
@SuppressWarnings("restriction")
public class GeneratorHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection) getActiveMenuSelection(event);
		File firstElement = (File) selection.getFirstElement();
		IPath sourceFilePath = firstElement.getLocation();
		if (sourceFilePath.segmentCount() < 2) {
			openError(getActiveShell(event), "Error", "File is not valid!");
		} else {
			if (firstElement.getFileExtension().equalsIgnoreCase(
					BundleGenerator.SOURCE_FILE_EXTENSION)) {
				IPath destinationDirectoryPath = sourceFilePath.uptoSegment(sourceFilePath
						.segmentCount() - 1);
				String destinationDirectory = destinationDirectoryPath.toOSString();
				boolean useCurrentDestination = (openQuestion(getActiveShell(event), "Question",
						"Use current output directory? " + destinationDirectory));
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
