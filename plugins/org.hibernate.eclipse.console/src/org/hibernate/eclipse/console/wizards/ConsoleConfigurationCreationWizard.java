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
package org.hibernate.eclipse.console.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.hibernate.console.ConsoleConfiguration;
import org.hibernate.console.ImageConstants;
import org.hibernate.console.KnownConfigurations;
import org.hibernate.console.preferences.ConsoleConfigurationPreferences;
import org.hibernate.console.preferences.ConsoleConfigurationPreferences.ConfigurationMode;
import org.hibernate.eclipse.console.EclipseConsoleConfiguration;
import org.hibernate.eclipse.console.EclipseConsoleConfigurationPreferences;
import org.hibernate.eclipse.console.HibernateConsolePlugin;
import org.hibernate.eclipse.console.utils.EclipseImages;

/**
 * @author max
 */
public class ConsoleConfigurationCreationWizard extends Wizard implements
		INewWizard {

	private ConsoleConfigurationWizardPage page;
	private ISelection selection;

	/**
	 * Constructor for SampleNewWizard.
	 */
	public ConsoleConfigurationCreationWizard() {
		super();
        setDefaultPageImageDescriptor(EclipseImages.getImageDescriptor(ImageConstants.NEW_WIZARD) );
		setNeedsProgressMonitor(true);
	}
	
	/**
	 * Adding the page to the wizard.
	 */

	public void addPages() {
		page = new ConsoleConfigurationWizardPage(selection);
		addPage(page);
	}

	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	public boolean performFinish() {
		final ConsoleConfigurationWizardPage confPage = this.page;
		return createConsoleConfiguration( getContainer(), confPage );
	}

	static boolean createConsoleConfiguration(IWizardContainer container, final ConsoleConfigurationWizardPage confPage) {
		final String configName = confPage.getConfigurationName();
		final String entityResolver = confPage.getEntityResolverClassName();
		final IPath propertyFile = confPage.getPropertyFilePath();
		final IPath fileName = confPage.getConfigurationFilePath();
		final ConfigurationMode annotations = confPage.getConfigurationMode();
		final IPath[] mappings = confPage.getMappingFiles();
		final IPath[] classpaths = confPage.getClassPath();
		final boolean useProjectClasspath = confPage.useProjectClassPath();
		final String projectName = confPage.getProjectName();
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					createConsoleConfiguration(confPage.getOldConfiguration(), configName, annotations, projectName, useProjectClasspath, entityResolver, propertyFile, fileName, mappings, classpaths, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			container.run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();			
			IStatus s = null;
			if(realException instanceof CoreException) {
				s = ( (CoreException)realException).getStatus();
			} else {
				IStatus se = HibernateConsolePlugin.throwableToStatus( e ); 
				s = new MultiStatus(HibernateConsolePlugin.ID, IStatus.OK, new IStatus[] { se }, "Probably missing classes or errors with classloading", e);
				
			}
			HibernateConsolePlugin.getDefault().showError( container.getShell(), "Error while finishing Wizard", s );			
			return false;
		}
		return true;
	}
	
	static private void createConsoleConfiguration(
			EclipseConsoleConfiguration oldConfig,
			String configName,
			ConfigurationMode cmode, String projectName, boolean useProjectClasspath, String entityResolver, IPath propertyFilename,
			IPath cfgFile, IPath[] mappings, IPath[] classpaths, IProgressMonitor monitor)
		throws CoreException {

		monitor.beginTask("Configuring Hibernate Console" + propertyFilename, IProgressMonitor.UNKNOWN);
								
		ConsoleConfigurationPreferences ccp = new EclipseConsoleConfigurationPreferences(configName, cmode, projectName, useProjectClasspath, entityResolver, cfgFile, propertyFilename, mappings, classpaths);
		
		
		final ConsoleConfiguration cfg = new EclipseConsoleConfiguration(ccp);
			
		if(oldConfig!=null) {
			oldConfig.reset(); // reset it no matter what.
			KnownConfigurations.getInstance().removeConfiguration(oldConfig);
		} 
		KnownConfigurations.getInstance().addConfiguration(cfg, true);
		ResourcesPlugin.getWorkspace().save( false, monitor );
		monitor.worked(1);
	} 
	
	
	/**
	 * We will accept the selection in the workbench to see if
	 * we can initialize from it.
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}
