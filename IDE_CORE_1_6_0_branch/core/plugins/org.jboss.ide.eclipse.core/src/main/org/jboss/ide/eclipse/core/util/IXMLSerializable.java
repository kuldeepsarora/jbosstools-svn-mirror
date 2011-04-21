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
package org.jboss.ide.eclipse.core.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @author    Laurent Etiemble
 * @version   $Revision$
 */
public interface IXMLSerializable
{
   /**
    * Render a XML tree
    *
    * @param doc   Description of the Parameter
    * @param node  Description of the Parameter
    */
   public void writeToXml(Document doc, Node node);

   /**
    * Read from a XML tree
    *
    * @param node  Description of the Parameter
    */
   public void readFromXml(Node node);

   /**
    * Read from a XML tree recursively
    *
    * @param node       Description of the Parameter
    * @param recursive  Description of the Parameter
    */
   public void readFromXml(Node node, boolean recursive);
}
