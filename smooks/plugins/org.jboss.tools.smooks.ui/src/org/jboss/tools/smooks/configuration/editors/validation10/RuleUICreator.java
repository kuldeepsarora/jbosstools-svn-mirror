/*******************************************************************************
 * Copyright (c) 2009 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.smooks.configuration.editors.validation10;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.jboss.tools.smooks.configuration.editors.AttributeFieldEditPart;
import org.jboss.tools.smooks.configuration.editors.PropertyUICreator;
import org.jboss.tools.smooks.editor.ISmooksModelProvider;
import org.jboss.tools.smooks.model.validation10.Validation10Package;

/**
 * @author Dart (dpeng@redhat.com)
 *         <p>
 *         Apr 8, 2009
 */
public class RuleUICreator extends PropertyUICreator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jboss.tools.smooks.configuration.editors.PropertyUICreator#ignoreProperty
	 * (org.eclipse.emf.ecore.EAttribute)
	 */
	@Override
	public boolean ignoreProperty(EAttribute feature) {
		if (feature == Validation10Package.Literals.RULE_TYPE__EXECUTE_ON) {
			return true;
		}
		if (feature == Validation10Package.Literals.RULE_TYPE__EXECUTE_ON_NS) {
			return true;
		}
		return super.ignoreProperty(feature);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jboss.tools.smooks.configuration.editors.PropertyUICreator#createExtendUI
	 * (org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain,
	 * org.eclipse.ui.forms.widgets.FormToolkit,
	 * org.eclipse.swt.widgets.Composite, java.lang.Object,
	 * org.jboss.tools.smooks.configuration.editors.SmooksMultiFormEditor)
	 */
	@Override
	public List<AttributeFieldEditPart> createExtendUIOnTop(AdapterFactoryEditingDomain editingdomain,
			FormToolkit toolkit, Composite parent, Object model, ISmooksModelProvider formEditor, IEditorPart part) {
		return createElementSelectionSection("Execute On Element", editingdomain, toolkit, parent, model, formEditor,
				part, Validation10Package.Literals.RULE_TYPE__EXECUTE_ON,
				Validation10Package.Literals.RULE_TYPE__EXECUTE_ON_NS);
	}
}
