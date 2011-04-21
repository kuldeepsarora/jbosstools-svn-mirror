/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.ide.eclipse.jdt.j2ee.ui.wizards.ejb;

import java.util.Arrays;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.jdom.IDOMCompilationUnit;
import org.eclipse.jdt.core.jdom.IDOMMethod;
import org.eclipse.jdt.core.jdom.IDOMType;
import org.eclipse.jdt.internal.ui.wizards.NewWizardMessages;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.SelectionButtonDialogFieldGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.jboss.ide.eclipse.jdt.j2ee.ui.JDTJ2EEUIMessages;
import org.jboss.ide.eclipse.jdt.j2ee.ui.wizards.*;
import org.jboss.ide.eclipse.jdt.ui.wizards.DOMClassWizardPage;

/**
 * Description of the Class
 *
 * @author    Laurent Etiemble
 * @version   $Revision$
 */
public class NewMessageDrivenEJBWizardPage extends DOMClassWizardPage
{
   private final static String PAGE_NAME = NewMessageDrivenEJBWizardPage.class.getName();

   /**
    *Constructor for the NewMessageDrivenEJBWizardPage object
    *
    * @param root  Description of the Parameter
    */
   public NewMessageDrivenEJBWizardPage(IWorkspaceRoot root)
   {
      super(true, PAGE_NAME);
      this.setTitle(JDTJ2EEUIMessages.getString("NewMessageDrivenEJBWizardPage.title"));//$NON-NLS-1$
      this.setDescription(JDTJ2EEUIMessages.getString("NewMessageDrivenEJBWizardPage.description"));//$NON-NLS-1$
   }

   /**
    * Adds a feature to the Content attribute of the NewMessageDrivenEJBWizardPage object
    *
    * @param type      The feature to be added to the Content attribute
    * @param compUnit  The feature to be added to the Content attribute
    * @param dType     The feature to be added to the Content attribute
    * @param monitor   The feature to be added to the Content attribute
    */
   protected void addContent(IType type, IDOMCompilationUnit compUnit, IDOMType dType, IProgressMonitor monitor)
   {
      Templates manager = new Templates();
      String beanName = J2EENamingUtil.stripEJBSuffix(type.getElementName());

      // Add the custom comment on top of the Servlet
      String comment = manager.getString("wizards.ejb.mdb.class.comment", new Object[]{beanName});//$NON-NLS-1$
      dType.setComment(comment);

      // ejbCreate method
      if (fMethodStubsButtons.isSelected(2))
      {
         IDOMMethod method = buildMethod(manager, "wizards.ejb.mdb.method.ejbCreate");//$NON-NLS-1$
         dType.addChild(method);
         addImport(compUnit, "javax.ejb.CreateException");//$NON-NLS-1$
      }
   }

   /** Description of the Method */
   protected void createContent()
   {
      super.createContent();

      // Constructors and inherited must be left as first elements
      String[] buttonNames = new String[]
      {NewWizardMessages.NewClassWizardPage_methods_constructors, //$NON-NLS-1$
            NewWizardMessages.NewClassWizardPage_methods_inherited, //$NON-NLS-1$
            "ejbCreate() method"};//$NON-NLS-1$

      fMethodStubsButtons = new SelectionButtonDialogFieldGroup(SWT.CHECK, buttonNames, 2);
      fMethodStubsButtons.setLabelText(NewWizardMessages.NewClassWizardPage_methods_label);//$NON-NLS-1$
   }

   /**
    * Description of the Method
    *
    * @param composite  Description of the Parameter
    * @param nColumns   Description of the Parameter
    */
   protected void createControls(Composite composite, int nColumns)
   {
      super.createControls(composite, nColumns);

      setSuperInterfaces(Arrays.asList(new String[]
      {"javax.ejb.MessageDrivenBean", "javax.jms.MessageListener"}), //$NON-NLS-1$ //$NON-NLS-2$
            true);
   }

   /** Description of the Method */
   protected void initContent()
   {
      super.initContent();

      // Constructor is checked
      fMethodStubsButtons.setSelection(0, true);
      // Inherited is checked
      fMethodStubsButtons.setSelection(1, true);
      // ejbCreate is checked
      fMethodStubsButtons.setSelection(2, true);
   }
}
