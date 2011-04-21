/*
 * JBoss-IDE, Eclipse plugins for JBoss
 *
 * Distributable under LGPL license.
 * See terms of license at www.gnu.org.
 */
package org.jboss.ide.eclipse.xdoclet.run.ui.dialogs;

import java.util.Collection;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.dialogs.ListContentProvider;
import org.jboss.ide.eclipse.ui.util.StringViewSorter;
import org.jboss.ide.eclipse.xdoclet.run.XDocletRunMessages;
import org.jboss.ide.eclipse.xdoclet.run.model.XDocletData;

/**
 * @author    Laurent Etiemble
 * @version   $Revision$
 * @created   18 mars 2003
 */
public class ConfigurationChoiceDialog extends Dialog
{
   private Text nameText;
   /** Description of the Field */
   private Collection choices;
   /** Description of the Field */
   private XDocletData data = null;
   /** Description of the Field */
   private ListViewer viewer;


   /**
    *Constructor for the DataChoiceDialog object
    *
    * @param parentShell  Description of the Parameter
    * @param choices      Description of the Parameter
    */
   public ConfigurationChoiceDialog(Shell parentShell, Collection choices)
   {
      super(parentShell);
      this.data = null;
      this.choices = choices;
   }


   /**
    * Gets the xDocletData attribute of the DataChoiceDialog object
    *
    * @return   The xDocletData value
    */
   public XDocletData getXDocletData()
   {
      return this.data;
   }


   /**
    * Description of the Method
    *
    * @param shell  Description of the Parameter
    */
   protected void configureShell(Shell shell)
   {
      super.configureShell(shell);
      shell.setText(XDocletRunMessages.getString("DataChoiceDialog.title"));//$NON-NLS-1$
   }



   /**
    * Description of the Method
    *
    * @param parent  Description of the Parameter
    * @return        Description of the Return Value
    * @see           org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
    */
   protected Control createDialogArea(Composite parent)
   {
      Composite composite = (Composite) super.createDialogArea(parent);
      GridLayout gridLayout = new GridLayout(2, false);
      gridLayout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
      gridLayout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
      composite.setLayout(gridLayout);

      Label fileLabel = new Label(composite, SWT.NONE);
      fileLabel.setText(XDocletRunMessages.getString("ConfigurationEditDialog.xdoclet.configuration.name"));//$NON-NLS-1$

      this.nameText = new Text(composite, SWT.BORDER);
      this.nameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      
      this.nameText.addModifyListener(new ModifyListener()
         {
            public void modifyText(ModifyEvent e)
            {
               enableButtons();
            }
         });

      List dataList = new List(composite, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
      GridData layoutData = new GridData(GridData.FILL_BOTH);
      layoutData.horizontalSpan = 2;
      dataList.setLayoutData(layoutData);

      this.viewer = new ListViewer(dataList);
      this.viewer.setContentProvider(new ListContentProvider());
      this.viewer.setSorter(new StringViewSorter());
      this.viewer.setInput(this.choices);

      return composite;
   }


   protected Control createContents(Composite parent)
   {
      Control control = super.createContents(parent);
      enableButtons();
      return control;
   }
   
   
   protected Point getInitialSize()
   {
      return getShell().computeSize(220, 300, true);
   }
   

   /** Description of the Method */
   protected void okPressed()
   {
      IStructuredSelection selection = (IStructuredSelection) this.viewer.getSelection();
      if (!selection.isEmpty())
      {
         XDocletData tempData = (XDocletData) selection.getFirstElement();
         this.data = (XDocletData) tempData.cloneData();
         this.data.setName(this.nameText.getText());
      }
      super.okPressed();
   }


   /**
    * 
    */
   private void enableButtons()
   {
      Button okButton = getButton(IDialogConstants.OK_ID);
      if (nameText.getText().length() > 0)
      {
         okButton.setEnabled(true);
      }
      else
      {
         okButton.setEnabled(false);
      }
   }
}
