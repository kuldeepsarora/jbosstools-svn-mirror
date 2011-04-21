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
package org.hibernate.eclipse.console.workbench;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.hibernate.console.ImageConstants;
import org.hibernate.eclipse.console.utils.EclipseImages;
import org.hibernate.mediator.stubs.util.StringHelper;
import org.hibernate.mediator.x.mapping.Property;
import org.hibernate.mediator.x.mapping.Value;

public class HibernateWorkbenchHelper {

	public static ImageDescriptor getImageDescriptor(Property property) {
		if(property==null) return null;
		if(property.getPersistentClass()!=null) {
			if(property.getPersistentClass().getIdentifierProperty()==property) {
				return EclipseImages.getImageDescriptor(ImageConstants.IDPROPERTY);
			}
		}
		String iconNameForValue = getIconNameForValue(property.getValue());
		
		return EclipseImages.getImageDescriptor(iconNameForValue);
	}
	
	public static Image getImage(Property property) {
		if(property==null) return null;
		if(property.getPersistentClass()!=null) {
			if(property.getPersistentClass().getIdentifierProperty()==property) {
				return EclipseImages.getImage(ImageConstants.IDPROPERTY);
			}
		}
		String iconNameForValue = getIconNameForValue(property.getValue());
		
		return EclipseImages.getImage(iconNameForValue);
	}
	
	static private String getIconNameForValue(Value value) {
		String result;
		
		result = (String) value.accept(new IconNameValueVisitor());
		
		if(result==null) {
			result = ImageConstants.UNKNOWNPROPERTY;
		}
		return result;
	}

	public static String getLabelForClassName(String classOrEntityName) {
		if(classOrEntityName.indexOf('.')>=0) {
			classOrEntityName = StringHelper.unqualify(classOrEntityName);
		}
		return classOrEntityName;
	}

}
