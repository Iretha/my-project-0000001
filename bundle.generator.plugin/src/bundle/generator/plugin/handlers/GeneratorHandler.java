/**
 * TODO
 */
package bundle.generator.plugin.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.internal.resources.File;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.ui.handlers.HandlerUtil;

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
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil
				.getActiveMenuSelection(event);
		File firstElement = (File) selection.getFirstElement();
		IPath sourceFilePath = firstElement.getLocation();
		if (sourceFilePath.segmentCount() < 2) {
			MessageDialog.openError(HandlerUtil.getActiveShell(event), "Error",
					"File is not valid!");
		} else {
			if (firstElement.getFileExtension().equalsIgnoreCase("txt")) {
				IPath destinationDirectoryPath = sourceFilePath.uptoSegment(firstElement
						.getLocation().segmentCount() - 1);
				String destinationDirectory = destinationDirectoryPath.toOSString();

				boolean changeDestination = !(MessageDialog.openQuestion(
						HandlerUtil.getActiveShell(event), "Question",
						"Use current output directory? " + destinationDirectory));
				if (changeDestination) {
					destinationDirectory = new DirectoryDialog(HandlerUtil.getActiveShell(event))
							.open();
				}
				if (destinationDirectory != null && destinationDirectory.length() > 0) {
					System.out.println(destinationDirectory);
					try {
						BundleGenerator.generateBundleFiles(destinationDirectory, firstElement
								.getLocation().toOSString());
						MessageDialog.openInformation(HandlerUtil.getActiveShell(event),
								"Information", "Done!");
					} catch (GeneratorException e) {
						MessageDialog.openError(HandlerUtil.getActiveShell(event), "Runtime Exception",
								e.getMessage());
					}
				}

			} else {
				MessageDialog.openInformation(HandlerUtil.getActiveShell(event), "Information",
						"Please select a txt source file");
			}
		}
		return null;
	}
}
