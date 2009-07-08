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
package org.jboss.tools.smooks.configuration.editors.javabean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.jboss.tools.smooks.configuration.editors.AttributeFieldEditPart;
import org.jboss.tools.smooks.configuration.editors.PropertyUICreator;
import org.jboss.tools.smooks.configuration.editors.SmooksMultiFormEditor;
import org.jboss.tools.smooks.configuration.editors.uitls.SmooksUIUtils;
import org.jboss.tools.smooks.model.javabean.BindingsType;
import org.jboss.tools.smooks.model.javabean.JavabeanFactory;
import org.jboss.tools.smooks.model.javabean.JavabeanPackage;
import org.jboss.tools.smooks.model.javabean.ValueType;
import org.jboss.tools.smooks.model.javabean.WiringType;

/**
 * @author Dart (dpeng@redhat.com)
 *         <p>
 *         Apr 8, 2009
 */
public class BindingsPropertyUICreator extends PropertyUICreator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jboss.tools.smooks.configuration.editors.IPropertyUICreator#canCreate
	 * (org.eclipse.emf.edit.provider.IItemPropertyDescriptor, java.lang.Object,
	 * org.eclipse.emf.ecore.EAttribute)
	 */
	public boolean canCreate(IItemPropertyDescriptor itemPropertyDescriptor, Object model, EAttribute feature) {
		if (feature == JavabeanPackage.eINSTANCE.getBindingsType_Class()) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jboss.tools.smooks.configuration.editors.PropertyUICreator#ignoreProperty
	 * (org.eclipse.emf.ecore.EAttribute)
	 */
	@Override
	public boolean ignoreProperty(EAttribute feature) {
		if (feature == JavabeanPackage.eINSTANCE.getBindingsType_CreateOnElement()) {
			return true;
		}
		if (feature == JavabeanPackage.eINSTANCE.getBindingsType_CreateOnElementNS()) {
			return true;
		}
		return super.ignoreProperty(feature);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.jboss.tools.smooks.configuration.editors.IPropertyUICreator#
	 * createPropertyUI(org.eclipse.ui.forms.widgets.FormToolkit,
	 * org.eclipse.swt.widgets.Composite,
	 * org.eclipse.emf.edit.provider.IItemPropertyDescriptor, java.lang.Object,
	 * org.eclipse.emf.ecore.EAttribute)
	 */
	public AttributeFieldEditPart createPropertyUI(FormToolkit toolkit, Composite parent,
			IItemPropertyDescriptor propertyDescriptor, Object model, EAttribute feature,
			SmooksMultiFormEditor formEditor) {
		if (feature == JavabeanPackage.eINSTANCE.getBindingsType_Class()) {
			return createBeanClassTextWithButton(parent, toolkit, propertyDescriptor, model);
		}
		return super.createPropertyUI(toolkit, parent, propertyDescriptor, model, feature, formEditor);
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
			FormToolkit toolkit, Composite parent, Object model, SmooksMultiFormEditor formEditor) {
		return createElementSelectionSection("Create On Element", editingdomain, toolkit, parent, model, formEditor,
				JavabeanPackage.Literals.BINDINGS_TYPE__CREATE_ON_ELEMENT,
				JavabeanPackage.Literals.BINDINGS_TYPE__CREATE_ON_ELEMENT_NS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.jboss.tools.smooks.configuration.editors.PropertyUICreator#
	 * createExtendUIOnBottom
	 * (org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain,
	 * org.eclipse.ui.forms.widgets.FormToolkit,
	 * org.eclipse.swt.widgets.Composite, java.lang.Object,
	 * org.jboss.tools.smooks.configuration.editors.SmooksMultiFormEditor)
	 */
	@Override
	public List<AttributeFieldEditPart> createExtendUIOnBottom(AdapterFactoryEditingDomain editingdomain,
			FormToolkit toolkit, Composite parent, Object model, SmooksMultiFormEditor formEditor) {
		List<AttributeFieldEditPart> lists = super.createExtendUIOnBottom(editingdomain, toolkit, parent, model,
				formEditor);

		Composite separator = toolkit.createCompositeSeparator(parent);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 1;
		gd.horizontalSpan = 2;
		separator.setLayoutData(gd);

		Hyperlink link = toolkit.createHyperlink(parent, "Add Binding", SWT.NONE);
		final Composite fp = parent;
		final BindingsType fb = (BindingsType)model;
		final SmooksMultiFormEditor ff = formEditor;
		link.addHyperlinkListener(new IHyperlinkListener(){

			/* (non-Javadoc)
			 * @see org.eclipse.ui.forms.events.IHyperlinkListener#linkActivated(org.eclipse.ui.forms.events.HyperlinkEvent)
			 */
			public void linkActivated(HyperlinkEvent e) {
				addValueWiringAuto(fp, fb, ff);
			}

			/* (non-Javadoc)
			 * @see org.eclipse.ui.forms.events.IHyperlinkListener#linkEntered(org.eclipse.ui.forms.events.HyperlinkEvent)
			 */
			public void linkEntered(HyperlinkEvent e) {
				// TODO Auto-generated method stub
				
			}

			/* (non-Javadoc)
			 * @see org.eclipse.ui.forms.events.IHyperlinkListener#linkExited(org.eclipse.ui.forms.events.HyperlinkEvent)
			 */
			public void linkExited(HyperlinkEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		gd = new GridData();
		gd.horizontalSpan = 2;
		link.setLayoutData(gd);

		return lists;
	}

	private void addValueWiringAuto(Composite parent, BindingsType bindings, SmooksMultiFormEditor formEditor) {
		boolean haveClassValue = false;
		if (bindings.eIsSet(JavabeanPackage.Literals.BINDINGS_TYPE__CLASS)) {
			haveClassValue = true;
		}
		if (!haveClassValue) {
			MessageDialog.openError(parent.getShell(), "Error", "Can't get the 'class' value.");
			return;
		}
		String className = bindings.getClass_();
		if (className == null || className.length() == 0) {
			MessageDialog.openError(parent.getShell(), "Error", "The 'class' value shouldn't be empty.");
			return;
		}
		try {
			Class<?> clazz = SmooksUIUtils.loadClass(className, ((IFileEditorInput) formEditor.getEditorInput())
					.getFile());
			JavaBeanModel beanModel = JavaBeanModelFactory.getJavaBeanModelWithLazyLoad(clazz);
			if (beanModel != null) {
				String[] ignores = findoutIgnoreProperty(bindings);
				ValueWiringBindingSelectionDialog dialog = new ValueWiringBindingSelectionDialog(parent.getShell(),
						beanModel, ignores);
				if(dialog.open() == Dialog.OK){
					Object[] checkedModels = dialog.getCheckedObject();
					if(checkedModels == null) return;
					generateValueWiringModel(checkedModels, formEditor, bindings);
				}
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	private void generateValueWiringModel(Object[] models,SmooksMultiFormEditor editor , BindingsType owner){
		EditingDomain domain = editor.getEditingDomain();
		CompoundCommand command = new CompoundCommand();
		command.setDescription("Add Binding");
		command.setLabel("Auto add binding");
		for (int i = 0; i < models.length; i++) {
			Object model = models[i];
			if(model instanceof JavaBeanModel){
				Command c = generateAddCommand((JavaBeanModel)model, domain, owner);
				command.append(c);
			}
		}
		domain.getCommandStack().execute(command);
	}
	
	private Command generateAddCommand(JavaBeanModel beanModel,EditingDomain domain,BindingsType owner){
		if(beanModel.isPrimitive()){
			ValueType valueType = JavabeanFactory.eINSTANCE.createValueType();
			valueType.setProperty(beanModel.getName());
			return AddCommand.create(domain, owner, JavabeanPackage.Literals.BINDINGS_TYPE__VALUE,valueType );
		}else{
			WiringType wiring = JavabeanFactory.eINSTANCE.createWiringType();
			wiring.setProperty(beanModel.getName());
			return AddCommand.create(domain, owner, JavabeanPackage.Literals.BINDINGS_TYPE__WIRING,wiring );
		}
	}
	
	private String[] findoutIgnoreProperty(BindingsType bindings){
		List<String> ignores = new ArrayList<String>();
		List<ValueType> valueList = bindings.getValue();
		
		for (Iterator<?> iterator = valueList.iterator(); iterator.hasNext();) {
			ValueType valueType = (ValueType) iterator.next();
//			boolean unset = valueType.eIsSet(JavabeanPackage.Literals.VALUE_TYPE__PROPERTY);
//			if(unset) continue;
			String pro = valueType.getProperty();
			if(pro != null && pro.length() != 0){
				ignores.add(pro);
			}
		}
		List<WiringType> wiringList = bindings.getWiring();
		for (Iterator<?> iterator = wiringList.iterator(); iterator.hasNext();) {
			WiringType wiringType = (WiringType) iterator.next();
//			boolean unset = wiringType.eIsSet(JavabeanPackage.Literals.WIRING_TYPE__PROPERTY);
//			if(unset) continue;
			String pro = wiringType.getProperty();
			if(pro != null && pro.length() != 0){
				ignores.add(pro);
			}
		}
		if(ignores.isEmpty()) return null;
		return ignores.toArray(new String[]{});
		
	}

	@Override
	public boolean isSelectorFeature(EAttribute attribute) {
		return super.isSelectorFeature(attribute);
	}

	protected AttributeFieldEditPart createBeanClassTextWithButton(Composite composite, FormToolkit toolkit,
			final IItemPropertyDescriptor propertyDescriptor, final Object model) {
		return SmooksUIUtils.createJavaTypeSearchFieldEditor(composite, toolkit, propertyDescriptor, (EObject) model);
	}
}
