/*
 * JBoss-IDE, Eclipse plugins for JBoss
 *
 * Distributable under LGPL license.
 * See terms of license at www.gnu.org.
 */
package org.jboss.ide.eclipse.packaging.core.configuration;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.jboss.ide.eclipse.core.AbstractPlugin;
import org.jboss.ide.eclipse.core.util.IXMLSerializable;
import org.jboss.ide.eclipse.packaging.core.PackagingCoreMessages;
import org.jboss.ide.eclipse.packaging.core.PackagingCorePlugin;
import org.jboss.ide.eclipse.packaging.core.model.PackagingArchive;
import org.jboss.ide.eclipse.ui.util.Progress;

/**
 * @author    Laurent Etiemble
 * @version   $Revision$
 */
public class ProjectConfigurations extends Configurations
{
   /** Description of the Field */
   private IFile file;
   /** Description of the Field */
   private IJavaProject project;


   /**
    *Constructor for the ProjectConfigurations object
    *
    * @param project  Description of the Parameter
    */
   public ProjectConfigurations(IJavaProject project)
   {
      this.project = project;
      this.file = this.project.getProject().getFile(PackagingCorePlugin.PROJECT_FILE);
   }


   /**
    * Description of the Method
    *
    * @exception CoreException  Description of the Exception
    */
   public void loadConfigurations()
      throws CoreException
   {
      if (this.file.exists())
      {
         this.load();
      }
   }


   /**
    * Description of the Method
    *
    * @exception CoreException  Description of the Exception
    */
   public void storeConfigurations()
      throws CoreException
   {
      IRunnableWithProgress runnable =
         new IRunnableWithProgress()
         {
            public void run(IProgressMonitor monitor)
               throws InvocationTargetException, InterruptedException
            {
               InputStream is = null;
               try
               {
                  Source source;
                  Result result;
                  Transformer transformer;
                  ByteArrayOutputStream baos;

                  monitor.beginTask(PackagingCoreMessages.getString("ProjectConfigurations.configuration.save"), 200);//$NON-NLS-1$

                  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                  DocumentBuilder docBuilder = factory.newDocumentBuilder();
                  Document document = docBuilder.newDocument();
                  Element root = document.createElement("configurations");//$NON-NLS-1$
                  document.appendChild(root);

                  // Compute the progress bar steps
                  int step = 1;
                  if (ProjectConfigurations.this.getConfigurations().size() > 0)
                  {
                     step = 100 / ProjectConfigurations.this.getConfigurations().size();
                  }

                  Iterator iterator = ProjectConfigurations.this.getConfigurations().iterator();
                  while (iterator.hasNext())
                  {
                     IXMLSerializable serializable = (IXMLSerializable) iterator.next();

                     monitor.subTask(MessageFormat.format(PackagingCoreMessages.getString("ProjectConfigurations.configuration.build"), new Object[]{serializable.toString()}));//$NON-NLS-1$

                     serializable.writeToXml(document, root);

                     monitor.worked(step);
                  }

                  // Serialize the DOM to a file using TRAX
                  monitor.subTask(PackagingCoreMessages.getString("ProjectConfigurations.configuration.write"));//$NON-NLS-1$

                  TransformerFactory tFactory = TransformerFactory.newInstance();
                  transformer = tFactory.newTransformer();

                  source = new DOMSource(document);
                  baos = new ByteArrayOutputStream();
                  result = new StreamResult(baos);

                  transformer.setOutputProperty("indent", "yes");//$NON-NLS-1$ //$NON-NLS-2$
                  transformer.transform(source, result);

                  IFile file = ProjectConfigurations.this.project.getProject().getFile(PackagingCorePlugin.PROJECT_FILE);
                  if (!file.exists())
                  {
                     file.create(null, true, null);
                  }
                  file.setContents(new ByteArrayInputStream(baos.toByteArray()), true, true, null);

                  monitor.worked(50);

                  /*
                   * / XSL-Transfrom configuration to Ant build file
                   * monitor.subTask(PackagingCoreMessages.getString("ProjectConfigurations.configuration.xsl"));//$NON-NLS-1$
                   * / Gets the XSL file
                   * String xslFile = PackagingCorePlugin.getDefault().find(new Path(PackagingCorePlugin.XSL_FILE)).getFile();
                   * is = new BufferedInputStream(new FileInputStream(xslFile));
                   * Source stylesheet = new StreamSource(is);
                   * / Create a new transformer on the stylesheet
                   * transformer = tFactory.newTransformer(stylesheet);
                   * source = new StreamSource(file.getContents());
                   * baos = new ByteArrayOutputStream();
                   * result = new StreamResult(baos);
                   * / Apply the XSL style
                   * transformer.setOutputProperty("indent", "yes");//$NON-NLS-1$ //$NON-NLS-2$
                   * transformer.transform(source, result);
                   * / Save the file
                   * IFile buildFile = ProjectConfigurations.this.project.getProject().getFile(PackagingCorePlugin.BUILD_FILE);
                   * if (!buildFile.exists())
                   * {
                   * buildFile.create(null, true, null);
                   * }
                   * buildFile.setContents(new ByteArrayInputStream(baos.toByteArray()), true, true, null);
                   */
                  monitor.worked(50);
               }
               catch (Exception e)
               {
                  e.printStackTrace();
                  throw new InvocationTargetException(e);
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
            }
         };

      // Launch the task with progress
      try
      {
         Progress progress = new Progress(AbstractPlugin.getShell(), runnable);
         progress.run();
      }
      catch (InvocationTargetException ite)
      {
         throw AbstractPlugin.wrapException(ite);
      }
      catch (InterruptedException ie)
      {
         throw AbstractPlugin.wrapException(ie);
      }
   }


   /**
    * Adds a feature to the Archive attribute of the ProjectConfigurations object
    *
    * @return   Description of the Return Value
    */
   protected PackagingArchive createArchive()
   {
      PackagingArchive archive = new PackagingArchive();
      archive.setProject(this.project.getProject());
      return archive;
   }


   /**
    * Gets the contents attribute of the ProjectConfigurations object
    *
    * @return                   The contents value
    * @exception CoreException  Description of the Exception
    * @see                      org.jboss.ide.eclipse.xdoclet.run.configuration.StandardConfigurations#getContents()
    */
   protected InputStream getContents()
      throws CoreException
   {
      return this.file.getContents(true);
   }


   /**
    * Gets the file attribute of the StandardConfigurations object
    *
    * @return                   The file value
    * @exception CoreException  Description of the Exception
    * @see                      org.jboss.ide.eclipse.xdoclet.run.configuration.Configurations#getFile()
    */
   protected String getFile()
      throws CoreException
   {
      return this.file.getLocation().toString();
   }
}
