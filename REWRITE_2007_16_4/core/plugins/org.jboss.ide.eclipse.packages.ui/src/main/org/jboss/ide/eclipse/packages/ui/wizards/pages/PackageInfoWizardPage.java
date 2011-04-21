package org.jboss.ide.eclipse.packages.ui.wizards.pages;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.jboss.ide.eclipse.packages.core.Trace;
import org.jboss.ide.eclipse.packages.core.model.IPackage;
import org.jboss.ide.eclipse.packages.core.model.IPackageNode;
import org.jboss.ide.eclipse.packages.core.model.PackagesCore;
import org.jboss.ide.eclipse.packages.core.model.internal.PackagesModel;
import org.jboss.ide.eclipse.packages.ui.PackagesUIMessages;
import org.jboss.ide.eclipse.packages.ui.PackagesUIPlugin;
import org.jboss.ide.eclipse.packages.ui.util.DestinationChangeListener;
import org.jboss.ide.eclipse.packages.ui.util.PackageDestinationComposite;
import org.jboss.ide.eclipse.packages.ui.wizards.AbstractPackageWizard;
import org.jboss.ide.eclipse.ui.wizards.WizardPageWithNotification;
import org.jboss.ide.eclipse.ui.wizards.WizardWithNotification;

public class PackageInfoWizardPage extends WizardPageWithNotification {

	private AbstractPackageWizard wizard;
	private Text packageNameText;
	private Button compressedButton;
	private Button explodedButton;
	private String packageName;
	private boolean packageExploded;
	private IFile manifestFile;
	private PackageDestinationComposite destinationComposite;
	private IPackage pkg;
	
	public PackageInfoWizardPage (AbstractPackageWizard wizard, IPackage existingPackage)
	{
		super (PackagesUIMessages.PackageInfoWizardPage_title, PackagesUIMessages.PackageInfoWizardPage_title, wizard.getImageDescriptor());
		setWizard(wizard);
		this.pkg = existingPackage;
	}
	
	public void createControl(Composite parent) {
		setMessage(PackagesUIMessages.PackageInfoWizardPage_message);		
		
		Composite main = new Composite(parent, SWT.NONE);
		main.setLayout(new GridLayout(1, false));
		
		Group infoGroup = new Group(main, SWT.NONE);
		infoGroup.setLayout(new GridLayout(3, false));
		infoGroup.setText(PackagesUIMessages.PackageInfoWizardPage_infoGroup_label);
		expand(infoGroup);
		
		new Label(infoGroup, SWT.NONE).setText(PackagesUIMessages.PackageInfoWizardPage_packageName_label);
		Composite pkgNameComposite = new Composite(infoGroup, SWT.NONE);
		GridLayout pkgNameLayout = new GridLayout(2, false);
		pkgNameLayout.marginHeight = 0;
		pkgNameLayout.marginWidth = 0;
		pkgNameComposite.setLayout(pkgNameLayout);
		pkgNameComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(pkgNameComposite, SWT.NONE).setImage(PackagesUIPlugin.getImage(PackagesUIPlugin.IMG_PACKAGE));
		
		packageNameText = new Text(pkgNameComposite, SWT.BORDER);
		packageName = wizard.getProject().getName() + "." + wizard.getPackageExtension();
		packageNameText.setText(packageName);
		packageNameText.setSelection(0, wizard.getProject().getName().length());
		expand(packageNameText);
		
		GridData pkgNameData = new GridData(GridData.FILL_HORIZONTAL);
		pkgNameData.horizontalSpan = 2;
		pkgNameComposite.setLayoutData(pkgNameData);
		
		packageNameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (validate())
				{
					packageName = packageNameText.getText();
				}
			}
		});

		new Label(infoGroup, SWT.NONE).setText(PackagesUIMessages.PackageInfoWizardPage_destination_label);

		GridData destinationTextData = new GridData(GridData.FILL_BOTH);
		destinationTextData.horizontalSpan = 2;
		GridData buttonData = new GridData(GridData.FILL_HORIZONTAL);
		buttonData.horizontalSpan = 3;
		buttonData.horizontalAlignment = SWT.END;
		
		Object destination = wizard.getSelectedDestination();
		destinationComposite = new PackageDestinationComposite(
			infoGroup, SWT.NONE, null, buttonData, destination);
		destinationComposite.addDestinationChangeListener(new DestinationChangeListener () {
			public void destinationChanged(Object newDestination) {
				validate();
			}
		});
		destinationComposite.setLayoutData(destinationTextData);
		
		Group packageTypeGroup = new Group(main, SWT.NONE);
		packageTypeGroup.setLayout(new GridLayout(1, false));
		packageTypeGroup.setText(PackagesUIMessages.PackageInfoWizardPage_packageTypeGroup_label);
		expand(packageTypeGroup);
		
		packageExploded = false;
		compressedButton = new Button(packageTypeGroup, SWT.RADIO);
		compressedButton.setText(PackagesUIMessages.PackageInfoWizardPage_compressedButton_label);
		compressedButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				packageExploded = false;
			}
		});
		compressedButton.setSelection(true);
		explodedButton = new Button(packageTypeGroup, SWT.RADIO);
		explodedButton.addSelectionListener(new SelectionAdapter () {
			public void widgetSelected(SelectionEvent e) {
				packageExploded = true;
			}
		});
		explodedButton.setText(PackagesUIMessages.PackageInfoWizardPage_explodedButton_label);
		setControl(main);
		
		fillDefaults();
		validate();
	}
	
	private void fillDefaults ()
	{
		if (pkg != null)
		{
			compressedButton.setSelection(!pkg.isExploded());
			explodedButton.setSelection(pkg.isExploded());
			packageNameText.setText(pkg.getName());
			packageName = pkg.getName();
			
			if (pkg.isTopLevel()) {
				if (pkg.isDestinationInWorkspace())
				{
					destinationComposite.setPackageNodeDestination(pkg.getDestinationContainer());
				} else {
					destinationComposite.setPackageNodeDestination(pkg.getDestinationPath());
				}
			} else {
				destinationComposite.setPackageNodeDestination(pkg.getParent());
			}
			
			if (pkg.isExploded())
			{
				explodedButton.setEnabled(true);
			} else {
				compressedButton.setEnabled(true);
			}
		}
	}

	private boolean validate ()
	{
		if (packageNameText.getText() == null || packageNameText.getText().length() == 0)
		{
			setErrorMessage(PackagesUIMessages.PackageInfoWizardPage_error_noPackageName);
			setPageComplete(false);
			return false;
		}
		else {
			setErrorMessage(null);
		}
		
		Object destination = getPackageDestination();
		if (destination instanceof IPackageNode)
		{
			IPackageNode parentNode = (IPackageNode) destination;
			
			IPackageNode subPackages[] = parentNode.getChildren(IPackageNode.TYPE_PACKAGE);
			for (int i = 0; i < subPackages.length; i++)
			{
				IPackage subPackage = (IPackage) subPackages[i];
				if (subPackage.getName().equals(packageNameText.getText())
					&& (!pkg.equals(this.pkg)))
				{
					setErrorMessage(
						PackagesUIMessages.bind(
							PackagesUIMessages.PackageInfoWizardPage_error_packageAlreadyExists, packageNameText.getText()));
					setPageComplete(false);
					return false;
				}
			}
		} else if (destination instanceof IContainer) {
			IContainer container = (IContainer) destination;
			List packages = PackagesModel.instance().getProjectPackages(wizard.getProject());
			if (packages != null)
			{
				for (Iterator iter = packages.iterator(); iter.hasNext(); )
				{
					IPackage pkg = (IPackage) iter.next();
					if (pkg.getName().equals(packageNameText.getText())
						&& (pkg.getDestinationContainer() != null && pkg.getDestinationContainer().equals(container))
						&& (!pkg.equals(this.pkg)))
					{
						setErrorMessage(
								PackagesUIMessages.bind(
									PackagesUIMessages.PackageInfoWizardPage_error_packageAlreadyExists, packageNameText.getText()));
							setPageComplete(false);
							return false;
					}
				}
			}
		} else if (destination instanceof IPath) {
			IPath path = (IPath) destination;
			List packages = PackagesModel.instance().getProjectPackages(wizard.getProject());
			if (packages != null)
			{
				for (Iterator iter = packages.iterator(); iter.hasNext(); )
				{
					IPackage pkg = (IPackage) iter.next();
					if (pkg.getName().equals(packageNameText.getText())
						&& (pkg.getDestinationPath() != null && pkg.getDestinationPath().equals(path))
						&& (!pkg.equals(this.pkg)))
					{
						setErrorMessage(
								PackagesUIMessages.bind(
									PackagesUIMessages.PackageInfoWizardPage_error_packageAlreadyExists, packageNameText.getText()));
							setPageComplete(false);
							return false;
					}
				}
			} else if (destination == null) {
				setErrorMessage(PackagesUIMessages.PackageInfoWizardPage_error_noDestination);
				setPageComplete(false);
				return false;
			}
		}
		
		setPageComplete(true);
		return true;
	}
	
	
	public void pageExited(int button) {
		Trace.trace(getClass(), "pageExited");
		if (button == WizardWithNotification.NEXT || button == WizardWithNotification.FINISH)
		{
			createPackage();
		}
	}
	
	private void createPackage ()
	{
		Trace.trace(getClass(), "creating package");
		
		Object destContainer = getPackageDestination();
		
		boolean isTopLevel = (destContainer == null || (!(destContainer instanceof IPackageNode)));
		
		IProject project = null;
		
		if (destContainer instanceof IPackageNode)
		{
			project = ((IPackageNode)destContainer).getProject();
		}
		else if (destContainer instanceof IContainer)
		{
			project = ((IContainer)destContainer).getProject();
		}
		else {
			project = wizard.getProject();
		}
		
		if (pkg == null) {
			pkg = PackagesCore.createDetachedPackage(project, isTopLevel);
		}
		
		pkg.setName(getPackageName());
		pkg.setExploded(isPackageExploded());
		
		if (!destContainer.equals(wizard.getProject()) && destContainer instanceof IContainer) {
			pkg.setDestinationContainer((IContainer)destContainer);
		}
		else if (destContainer instanceof IPath)
		{
			pkg.setDestinationPath((IPath) destContainer);
		}
	}
	
	private void expand(Control control)
	{
		control.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	public String getPackageName() {
		return packageName;
	}

	public boolean isPackageExploded() {
		return packageExploded;
	}

	public Object getPackageDestination() {
		return destinationComposite.getPackageNodeDestination();
	}
	
	private void setWizard(AbstractPackageWizard wizard)
	{
		this.wizard = wizard;
	}
	
	public IPackage getPackage ()
	{
		return pkg;
	}
}
