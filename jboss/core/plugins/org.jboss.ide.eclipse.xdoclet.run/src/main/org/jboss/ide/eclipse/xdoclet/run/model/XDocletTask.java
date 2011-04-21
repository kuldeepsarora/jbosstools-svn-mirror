/*
 * JBoss-IDE, Eclipse plugins for JBoss
 *
 * Distributable under LGPL license.
 * See terms of license at www.gnu.org.
 */
package org.jboss.ide.eclipse.xdoclet.run.model;

import java.util.Iterator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.jboss.ide.eclipse.core.util.IXMLSerializable;

/**
 * Description of the Class
 *
 * @author    Laurent Etiemble
 * @version   $Revision$
 * @created   17 mars 2003
 * @todo      Javadoc to complete
 */
public class XDocletTask extends XDocletElement
{
   /** Description of the Field */
   private String className = null;


   /**Constructor for the XDocletTask object */
   public XDocletTask() { }


   /**
    * Description of the Method
    *
    * @return   Description of the Return Value
    * @see      java.lang.Object#clone()
    */
   public Object clone()
   {
      Iterator iterator;
      XDocletTask task = new XDocletTask();

      task.setName(this.getName());
      task.setClassName(this.getClassName());
      task.setUsed(this.isUsed());

      iterator = this.getAttributes().iterator();
      while (iterator.hasNext())
      {
         XDocletAttribute attribute = (XDocletAttribute) iterator.next();
         task.addAttribute((XDocletAttribute) attribute.clone());
      }

      return task;
   }


   /**
    * Description of the Method
    *
    * @return   Description of the Return Value
    */
   public XDocletData cloneData()
   {
      Iterator iterator;
      XDocletTask task = (XDocletTask) this.clone();

      iterator = this.getNodes().iterator();
      while (iterator.hasNext())
      {
         XDocletElement elmt = (XDocletElement) iterator.next();
         task.addNode(elmt.cloneData());
      }

      return task;
   }



   /**
    * @return   String
    */
   public String getClassName()
   {
      return this.className;
   }


   /**
    * Gets the parent attribute of the XDocletTask object
    *
    * @return   The parent value
    * @see      xdoclet.ide.eclipse.configuration.model.XDocletData#getParent()
    */
   public XDocletData getParent()
   {
      return null;
   }


   /**
    * Description of the Method
    *
    * @param node       Description of the Parameter
    * @param recursive  Description of the Parameter
    * @see              xdoclet.ide.eclipse.configuration.model.IXMLSerializable#readFromXml(org.w3c.dom.Node, boolean)
    */
   public void readFromXml(Node node, boolean recursive)
   {
      Element element = (Element) node;
      this.setName(element.getAttribute("name"));//$NON-NLS-1$
      this.setClassName(element.getAttribute("className"));//$NON-NLS-1$
      this.setUsed(new Boolean(element.getAttribute("used")).booleanValue());//$NON-NLS-1$

      NodeList children = node.getChildNodes();
      for (int i = 0; i < children.getLength(); i++)
      {
         Node child = children.item(i);
         if (child.getNodeName().equals("attribute")//$NON-NLS-1$
         )
         {

            XDocletAttribute attribute = new XDocletAttribute();
            attribute.readFromXml(child);
            this.addAttribute(attribute);
         }

         if (recursive)
         {
            if (child.getNodeName().equals("element")//$NON-NLS-1$
            )
            {

               XDocletElement elmt = new XDocletElement();
               elmt.readFromXml(child);
               this.addNode(elmt);
            }
         }
      }
   }


   /**
    * Sets the className.
    *
    * @param className  The className to set
    */
   public void setClassName(String className)
   {
      this.className = className;
   }


   /**
    * Description of the Method
    *
    * @param doc   Description of the Parameter
    * @param node  Description of the Parameter
    */
   public void writeToXml(Document doc, Node node)
   {
      Element element = doc.createElement("task");//$NON-NLS-1$
      node.appendChild(element);

      element.setAttribute("name", this.getName());//$NON-NLS-1$
      element.setAttribute("used", "" + this.isUsed());//$NON-NLS-1$ //$NON-NLS-2$
      element.setAttribute("className", this.getClassName());//$NON-NLS-1$

      Iterator iterator = this.getAttributes().iterator();
      while (iterator.hasNext())
      {
         IXMLSerializable serializable = (IXMLSerializable) iterator.next();
         serializable.writeToXml(doc, element);
      }

      iterator = this.getNodes().iterator();
      while (iterator.hasNext())
      {
         IXMLSerializable serializable = (IXMLSerializable) iterator.next();
         serializable.writeToXml(doc, element);
      }
   }

}
