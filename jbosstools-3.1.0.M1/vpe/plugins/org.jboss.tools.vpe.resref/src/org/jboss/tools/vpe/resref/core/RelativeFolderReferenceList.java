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
package org.jboss.tools.vpe.resref.core;

import org.eclipse.core.runtime.QualifiedName;
import org.jboss.tools.common.resref.core.ResourceReferenceList;

public class RelativeFolderReferenceList extends ResourceReferenceList {
	private static QualifiedName PROPERTY_NAME = new QualifiedName("", "org.jboss.tools.vpe.editor.css.RelativeFolder");
	static RelativeFolderReferenceList instance = new RelativeFolderReferenceList();
	
	public static RelativeFolderReferenceList getInstance() {
		return instance;
	}

	protected QualifiedName getPropertyName() {
		return PROPERTY_NAME;
	}
	
}
