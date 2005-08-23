package org.hibernate.eclipse.console.actions;

import java.util.Iterator;

import org.eclipse.swt.widgets.Event;
import org.hibernate.HibernateException;
import org.hibernate.console.ConsoleConfiguration;
import org.hibernate.console.ImageConstants;
import org.hibernate.eclipse.console.HibernateConsolePlugin;
import org.hibernate.eclipse.console.utils.EclipseImages;

public class HQLScratchpadAction extends ConsoleConfigurationBasedAction {
	public HQLScratchpadAction() {
		super( "HQL Scratchpad" );
		setImageDescriptor(EclipseImages.getImageDescriptor(ImageConstants.HQL_EDITOR));
		setToolTipText("Open HQL Scratchpad");
		setEnabled(false);
	}

	public void runWithEvent(Event event) {
		run();
	}

	protected void doRun() {
		for (Iterator i = getSelectedNonResources().iterator(); i.hasNext();) {
			try {
				Object node = i.next();
				if(node instanceof ConsoleConfiguration) {
					final ConsoleConfiguration config = (ConsoleConfiguration) node;
					HibernateConsolePlugin.getDefault().openScratchHQLEditor(config.getName(), "");
				}
			} catch(HibernateException he) {
				HibernateConsolePlugin.getDefault().showError(null, "Exception while trying to edit configuration", he);
			}
		} 
					
	}
	
}