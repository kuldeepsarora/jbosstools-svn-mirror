/**
 * JBoss, Home of Professional Open Source
 * Copyright 2009, JBoss Inc., and others contributors as indicated
 * by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 *
 * (C) 2009, JBoss Inc.
 */
package org.jboss.tools.smooks.model.core;

import org.milyn.StreamFilterType;
import org.milyn.delivery.Filter;

/**
 * Global Parameters.
 *
 * @author <a href="mailto:tom.fennelly@jboss.com">tom.fennelly@jboss.com</a>
 */
public class GlobalParams extends Params {
	
	public GlobalParams setFilterType(StreamFilterType filterType) {
		setParam(Filter.STREAM_FILTER_TYPE, filterType.toString());		
		return this;
	}
	
	public StreamFilterType getFilterType() {
		String filterType = getParam(Filter.STREAM_FILTER_TYPE);
		
		if(filterType == null) {
			return null;
		} 
		
		try {
			return StreamFilterType.valueOf(filterType);
		} catch(Exception e) {
			return null;
		}
	}
}
