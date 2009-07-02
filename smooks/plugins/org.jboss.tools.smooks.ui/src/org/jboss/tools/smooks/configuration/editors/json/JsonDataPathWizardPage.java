/*******************************************************************************
 * Copyright (c) 2008 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.smooks.configuration.editors.json;

import java.util.List;

import org.eclipse.jface.viewers.ViewerFilter;
import org.jboss.tools.smooks.configuration.editors.xml.AbstractFileSelectionWizardPage;

/**
 * @author Dart (dpeng@redhat.com)
 *
 */
public class JsonDataPathWizardPage extends AbstractFileSelectionWizardPage {

	public JsonDataPathWizardPage(String pageName, boolean multiSelect, Object[] initSelections,
			List<ViewerFilter> filters) {
		super(pageName, multiSelect, initSelections, filters);
	}

	public JsonDataPathWizardPage(String pageName, String[] fileExtensionNames) {
		super(pageName, fileExtensionNames);
		this.setTitle("JSON file selection");
		this.setDescription("Select a JSON data file");
	}
	
	

	/* (non-Javadoc)
	 * @see org.jboss.tools.smooks.configuration.editors.xml.AbstractFileSelectionWizardPage#loadedTheObject(java.lang.String)
	 */
	@Override
	protected Object loadedTheObject(String path) throws Exception {
		return null;
	}

	@Override
	protected void changeWizardPageStatus() {
		super.changeWizardPageStatus();
	}

	@Override
	public boolean canFlipToNextPage() {
		return super.canFlipToNextPage();
//		String filePath = this.getFilePath();
//		try {
//			filePath = SmooksUIUtils.parseFilePath(filePath);
//			if(filePath == null) return false;
//			return new File(filePath).exists();
//		} catch (InvocationTargetException e) {
//			return false;
//		}
	}
}
