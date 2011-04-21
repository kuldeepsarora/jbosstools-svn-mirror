/*
 * JBoss-IDE, Eclipse plugins for JBoss
 *
 * Distributable under LGPL license.
 * See terms of license at www.gnu.org.
 */
package org.jboss.ide.eclipse.xdoclet.run.ui.actions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.transform.TransformerException;

import org.eclipse.ant.internal.ui.launchConfigurations.AntLaunchShortcut;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.actions.ActionDelegate;
import org.eclipse.ui.externaltools.internal.model.IExternalToolConstants;
import org.jboss.ide.eclipse.core.AbstractPlugin;
import org.jboss.ide.eclipse.xdoclet.run.XDocletRunMessages;
import org.jboss.ide.eclipse.xdoclet.run.XDocletRunPlugin;

/**
 * @author    Laurent Etiemble
 * @version   $Revision$
 * @todo      Javadoc to complete
 */
public class XDocletRunAction extends ActionDelegate implements IObjectActionDelegate, IWorkbenchWindowActionDelegate
{
   /** Description of the Field */
   protected IWorkbenchPart part = null;
   /** Description of the Field */
   protected ISelection selection = null;
   /** Description of the Field */
   protected IWorkbenchWindow window = null;


   /**Constructor for the XDocletRunAction object */
   public XDocletRunAction()
   {
      super();
   }


   /** Description of the Method */
   public void dispose() { }


   /**
    * Description of the Method
    *
    * @param window  Description of the Parameter
    */
   public void init(IWorkbenchWindow window)
   {
      this.window = window;
   }


   /**
    * Main processing method for the XDocletRunAction object
    *
    * @param action  Description of the Parameter
    */
   public void run(IAction action)
   {
      if (this.selection != null && (this.selection instanceof IStructuredSelection))
      {
         IStructuredSelection sel = (IStructuredSelection) this.selection;
         Object o = sel.getFirstElement();

         // For each Java Project
         if (o instanceof IResource)
         {
            IProject project = ((IResource) o).getProject();
            this.process(project);
         }
         if (o instanceof IJavaProject) {
         	IProject project = ((IJavaProject)o).getProject();
         	this.process(project);
         }
      }
   }


   /**
    * Description of the Method
    *
    * @param action     Description of the Parameter
    * @param selection  Description of the Parameter
    */
   public void selectionChanged(IAction action, ISelection selection)
   {
      this.selection = selection;
   }


   /**
    * @param action      The new ActivePart value
    * @param targetPart  The new ActivePart value
    * @see               org.eclipse.ui.IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
    */
   public void setActivePart(IAction action, IWorkbenchPart targetPart)
   {
      this.part = targetPart;
   }


   /**
    * Description of the Method
    *
    * @param project  Description of the Parameter
    */
   protected void process(final IProject project)
   {
      final IJavaProject jProject = JavaCore.create(project);
      final IFile projectFile = project.getFile(XDocletRunPlugin.PROJECT_FILE);

      // If the xdoclet build file exists, then process it
      if (projectFile.exists())
      {
         Job job =
            new Job(XDocletRunMessages.getString("XDocletRunAction.job.title")//$NON-NLS-1$
            )
            {
               protected IStatus run(IProgressMonitor monitor)
               {
                  InputStream is = null;
                  try
                  {
                     monitor.beginTask(XDocletRunMessages.getString("XDocletRunAction.xdoclet.run"), 100);//$NON-NLS-1$

                     // Transform configuration to Ant build file
                     monitor.subTask(XDocletRunMessages.getString("XDocletRunAction.xdoclet.generate"));//$NON-NLS-1$

                     IFile buildFile = XDocletRunPlugin.getDefault().createBuildFile(jProject);
                     
                     monitor.worked(10);

                     // Run XDoclet Ant build file
                     monitor.subTask(XDocletRunMessages.getString("XDocletRunAction.xdoclet.process"));//$NON-NLS-1$

                     ILaunchConfiguration configuration = null;
                     List cfgs = AntLaunchShortcut.findExistingLaunchConfigurations(buildFile);
                     if (cfgs.size() > 0)
                     {
                        // Take the first and remove the others
                        for (int i = 0; i < cfgs.size(); i++)
                        {
                           ((ILaunchConfiguration) cfgs.get(i)).delete();
                        }
                     }

                     // Create a new one
                     configuration = AntLaunchShortcut.createDefaultLaunchConfiguration(buildFile);
                     ILaunchConfigurationWorkingCopy copy = configuration.getWorkingCopy();
                     copy.setAttribute(IExternalToolConstants.ATTR_CAPTURE_OUTPUT, true);
                     copy.setAttribute(IExternalToolConstants.ATTR_SHOW_CONSOLE, true);
                     copy.setAttribute(IDebugUIConstants.ATTR_LAUNCH_IN_BACKGROUND, false);
                     copy.setAttribute(IDebugUIConstants.ATTR_PRIVATE, true);
                     configuration = copy.doSave();

                     // Launch the generation
                     configuration.launch(ILaunchManager.RUN_MODE, monitor);

                     monitor.worked(80);

                     // Refresh the project
                     monitor.subTask(XDocletRunMessages.getString("XDocletRunAction.xdoclet.refresh"));//$NON-NLS-1$
                     project.refreshLocal(IResource.DEPTH_INFINITE, monitor);
                     monitor.worked(10);
                  }
                  catch (FileNotFoundException fnfe)
                  {
                     AbstractPlugin.logError("Cannot find XSL file", fnfe);//$NON-NLS-1$
                     XDocletRunPlugin.getDefault().showErrorMessage(XDocletRunMessages.getString("XDocletRunAction.failed") + fnfe.getMessage());//$NON-NLS-1$
                  }
                  catch (IOException ioe)
                  {
                     AbstractPlugin.logError("Cannot open XSL file", ioe);//$NON-NLS-1$
                     XDocletRunPlugin.getDefault().showErrorMessage(XDocletRunMessages.getString("XDocletRunAction.failed") + ioe.getMessage());//$NON-NLS-1$
                  }
                  catch (TransformerException te)
                  {
                     AbstractPlugin.logError("Error while building Ant file", te);//$NON-NLS-1$
                     XDocletRunPlugin.getDefault().showErrorMessage(XDocletRunMessages.getString("XDocletRunAction.failed") + te.getMessage());//$NON-NLS-1$
                  }
                  catch (CoreException ce)
                  {
                     AbstractPlugin.logError("Error while running XDoclet", ce);//$NON-NLS-1$
                     XDocletRunPlugin.getDefault().showErrorMessage(XDocletRunMessages.getString("XDocletRunAction.failed") + ce.getMessage());//$NON-NLS-1$
                  }
                  finally
                  {
                     // Ensure that the input stream is closed.
                     if (is != null)
                     {
                        try
                        {
                           is.close();
                        }
                        catch (Throwable ignore)
                        {
                        }
                     }
                  }
                  return Status.OK_STATUS;
               }
            };
         job.setRule(project.getProject());
         job.setPriority(Job.BUILD);
         job.schedule();
      }
   }
}
