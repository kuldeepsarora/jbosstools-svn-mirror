/*
 * JBoss-IDE, Eclipse plugins for JBoss
 *
 * Distributable under LGPL license.
 * See terms of license at www.gnu.org.
 */
package org.jboss.ide.eclipse.jdt.xml.ui.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.jboss.ide.eclipse.jdt.ui.wizards.NewFileWizard;
import org.jboss.ide.eclipse.jdt.ui.wizards.NewFileWizardPage;
import org.jboss.ide.eclipse.jdt.xml.ui.JDTXMLUIMessages;

/**
 * Description of the Class
 *
 * @author    Laurent Etiemble
 * @version   $Revision$
 */
public class NewXHTMLPageWizard extends NewFileWizard
{
   /**Constructor for the NewXHTMLPageWizard object */
   public NewXHTMLPageWizard() { }


   /**
    * Description of the Method
    *
    * @param selection  Description of the Parameter
    * @return           Description of the Return Value
    */
   protected NewFileWizardPage createNewFileWizardPage(IStructuredSelection selection)
   {
      return new NewXHTMLPageWizardPage(selection);
   }


   /**
    * Gets the wizardTitle attribute of the NewXHTMLPageWizard object
    *
    * @return   The wizardTitle value
    */
   protected String getWizardTitle()
   {
      return JDTXMLUIMessages.getString("NewXHTMLPageWizard.window.title");//$NON-NLS-1$
   }
}
