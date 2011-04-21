package org.jboss.ide.eclipse.packages.ui.wizards;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.ui.IWorkbench;
import org.jboss.ide.eclipse.packages.core.Trace;
import org.jboss.ide.eclipse.packages.core.model.IPackage;
import org.jboss.ide.eclipse.packages.core.model.PackagesCore;
import org.jboss.ide.eclipse.packages.core.model.types.JARPackageType;
import org.jboss.ide.eclipse.packages.ui.PackagesUIMessages;
import org.jboss.ide.eclipse.packages.ui.PackagesUIPlugin;
import org.jboss.ide.eclipse.packages.ui.wizards.pages.DefaultJARConfigWizardPage;

public class NewJARWizard extends AbstractPackageWizard
{
	public WizardPage[] createWizardPages() {
//		if (existingPackage == null) {
//			return new WizardPage[] {
//					new DefaultJARConfigWizardPage(this)
//			};
//		} else
			return new WizardPage[0];
	}

	public NewJARWizard ()
	{
		setWindowTitle(PackagesUIMessages.NewJARWizard_windowTitle);
	}
	
	public NewJARWizard (IPackage existingPackage)
	{
		super(existingPackage);
		
		setWindowTitle(PackagesUIMessages.NewJARWizard_windowTitle_editJAR);
	}
	
	public boolean performFinish(IPackage pkg) {
		Trace.trace(getClass(), "performing finish");
		
		pkg.setPackageType(PackagesCore.getPackageType(JARPackageType.TYPE_ID));
		return true;
	}
	
	public ImageDescriptor getImageDescriptor() {
		return PackagesUIPlugin.getImageDescriptor(PackagesUIPlugin.IMG_NEW_JAR_WIZARD);
	}
	
	public String getPackageExtension() {
		return "jar";
	}
}
