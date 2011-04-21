/*
 * JBoss-IDE, Eclipse plugins for JBoss
 *
 * Distributable under LGPL license.
 * See terms of license at www.gnu.org.
 */
package org.jboss.ide.eclipse.xdoclet.run.configuration;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.jboss.ide.eclipse.core.AbstractPlugin;
import org.jboss.ide.eclipse.ui.util.Progress;
import org.jboss.ide.eclipse.xdoclet.run.XDocletRunMessages;
import org.jboss.ide.eclipse.xdoclet.run.model.XDocletConfiguration;

/**
 * @author    Laurent Etiemble
 * @version   $Revision$
 * @created   18 mars 2003
 * @todo      Javadoc to complete
 */
public abstract class Configurations
{
   /** Description of the Field */
   private ArrayList configurations = new ArrayList();


   /**Constructor for the StandardConfigurations object */
   public Configurations() { }


   /**
    * Gets the configurations attribute of the ProjectConfigurations object
    *
    * @return   The configurations value
    */
   public List getConfigurations()
   {
      return this.configurations;
   }


   /**
    * Description of the Method
    *
    * @exception CoreException  Description of the Exception
    */
   public abstract void loadConfigurations()
          throws CoreException;


   /**
    * Description of the Method
    *
    * @param o  Description of the Parameter
    */
   public void moveDown(Object o)
   {
      if (this.configurations.contains(o))
      {
         int pos = this.configurations.indexOf(o) + 1;
         if (pos < this.configurations.size())
         {
            this.configurations.remove(o);
            this.configurations.add(pos, o);
         }
      }
   }



   /**
    * Description of the Method
    *
    * @param o  Description of the Parameter
    */
   public void moveUp(Object o)
   {
      if (this.configurations.contains(o))
      {
         int pos = this.configurations.indexOf(o) - 1;
         if (pos >= 0)
         {
            this.configurations.remove(o);
            this.configurations.add(pos, o);
         }
      }
   }


   /**
    * Gets the contents attribute of the StandardConfigurations object
    *
    * @return                   The contents value
    * @exception CoreException  Description of the Exception
    */
   protected abstract InputStream getContents()
          throws CoreException;


   /**
    * Description of the Method
    *
    * @exception CoreException  Description of the Exception
    */
   protected void load()
          throws CoreException
   {
      IRunnableWithProgress runnable =
         new IRunnableWithProgress()
         {
            public void run(IProgressMonitor monitor)
                   throws InvocationTargetException
            {
               try
               {
                  monitor.beginTask(XDocletRunMessages.getString("Configurations.configuration.load"), 100);//$NON-NLS-1$

                  Configurations.this.getConfigurations().clear();

                  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                  DocumentBuilder docBuilder = factory.newDocumentBuilder();

                  Document document = docBuilder.parse(getContents());
                  Node root = document.getDocumentElement();
                  NodeList children = root.getChildNodes();

                  // Compute the progress bar steps
                  int step = 1;
                  if (children.getLength() > 0)
                  {
                     step = 100 / children.getLength();
                  }

                  for (int i = 0; i < children.getLength(); i++)
                  {
                     Node child = children.item(i);

                     monitor.subTask(MessageFormat.format(XDocletRunMessages.getString("Configurations.configuration.restore"), new Object[]{child.getNodeValue()}));//$NON-NLS-1$

                     if (child.getNodeName().equals("configuration")//$NON-NLS-1$
                     )
                     {
                        XDocletConfiguration configuration = new XDocletConfiguration();
                        configuration.readFromXml(child);
                        Configurations.this.configurations.add(configuration);
                     }

                     monitor.worked(step);
                  }
               }
               catch (Exception e)
               {
                  throw new InvocationTargetException(e);
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
}
