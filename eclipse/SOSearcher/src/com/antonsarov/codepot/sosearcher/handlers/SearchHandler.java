package com.antonsarov.codepot.sosearcher.handlers;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.osgi.framework.Bundle;

public class SearchHandler extends AbstractHandler {

	private static final String SO_SEARCH_URL = "http://stackoverflow.com/search?q=";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		InputDialog dialog = new InputDialog(
				HandlerUtil.getActiveShellChecked(event),
				"Search on Stack Overflow", "Search for posts containing",
				null, null);
		dialog.open();
		String input = dialog.getValue();
		if (input == null || input.trim().isEmpty()) {
			Bundle bundle = Platform.getBundle("com.antonsarov.codepot.sosearcher");
			ILog log = Platform.getLog(bundle);
			IStatus status = new Status(IStatus.WARNING,
					"com.antonsarov.codepot.sosearcher", -1,
					"Not really sure what you want, huh?", null);
			log.log(status);
		} else {
			final String url = SO_SEARCH_URL + input;
			try {
				PlatformUI.getWorkbench().getBrowserSupport()
						.getExternalBrowser().openURL(new URL(url));
			} catch (PartInitException | MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
