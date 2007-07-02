/*******************************************************************************
 * Copyright (c) 2007 Exadel, Inc. and Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Exadel, Inc. and Red Hat, Inc. - initial API and implementation
 ******************************************************************************/ 
package org.jboss.tools.hibernate.veditor.editors.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Konstantin Mishin
 *
 */
public abstract class ModelElement{

	private transient PropertyChangeSupport pcsDelegate = new PropertyChangeSupport(this);

	public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
		if (l == null) {
			throw new IllegalArgumentException();
		}
		pcsDelegate.addPropertyChangeListener(l);
	}
	
	protected void firePropertyChange(String property, Object oldValue, Object newValue) {
		if (pcsDelegate.hasListeners(property)) {
			pcsDelegate.firePropertyChange(property, oldValue, newValue);
		}
	}
	
	public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
		if (l != null) {
			pcsDelegate.removePropertyChangeListener(l);
		}
	}
	
	private List children = new OList();
	private ModelElement parent;
	
	public List getChildren(){
		return children;
	}
	
	public ModelElement getParent(){
		return parent;
	}
	
	public void setParent(ModelElement element){
		parent = element;
	}
	
	class OList extends ArrayList{
		public OList(){
			
		}
		
		public boolean add(Object item){
			if(item instanceof ModelElement)((ModelElement)item).setParent(ModelElement.this);
			return super.add(item);
		}
		
		public boolean remove(Object item){
			if(item instanceof ModelElement)((ModelElement)item).setParent(null);
			return super.remove(item);
		}
	}

}
