/******************************************************************************* 
 * Copyright (c) 2007 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.jboss.tools.cdi.internal.core.impl.definition;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IAnnotatable;
import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.IType;
import org.jboss.tools.cdi.core.CDIConstants;
import org.jboss.tools.cdi.core.CDICorePlugin;
import org.jboss.tools.cdi.internal.core.impl.AnnotationDeclaration;
import org.jboss.tools.cdi.internal.core.impl.InterceptorBindingDeclaration;
import org.jboss.tools.cdi.internal.core.impl.StereotypeDeclaration;

/**
 * 
 * @author Viacheslav Kabanovich
 *
 */
public abstract class AbstractMemberDefinition {
	protected List<AnnotationDeclaration> annotations = new ArrayList<AnnotationDeclaration>();
	protected IAnnotatable member;
	protected AnnotationDeclaration injectAnnotation;
	protected AnnotationDeclaration producesAnnotation;

	public AbstractMemberDefinition() {}

	protected void setAnnotatable(IAnnotatable member, IType contextType, DefinitionContext context) {
		this.member = member;
		try {
			init(contextType, context);
		} catch (CoreException e) {
			CDICorePlugin.getDefault().logError(e);
		}
	}

	protected void init(IType contextType, DefinitionContext context) throws CoreException {
		IAnnotation[] ts = member.getAnnotations();
		for (int i = 0; i < ts.length; i++) {
			AnnotationDeclaration a = new AnnotationDeclaration();
			a.setProject(context.getProject());
			a.setDeclaration(ts[i], contextType);
			if(context.getAnnotationKind(a.getType()) == AnnotationDefinition.STEREOTYPE) {
				a = new StereotypeDeclaration(a);
			} else if(context.getAnnotationKind(a.getType()) == AnnotationDefinition.INTERCEPTOR_BINDING) {
				a = new InterceptorBindingDeclaration(a);
			}
			annotations.add(a);
			if(CDIConstants.INJECT_ANNOTATION_TYPE_NAME.equals(a.getTypeName())) {
				injectAnnotation = a;
			} else if(CDIConstants.PRODUCES_ANNOTATION_TYPE_NAME.equals(a.getTypeName())) {
				producesAnnotation = a;
			}
		}
	}

	public List<AnnotationDeclaration> getAnnotations() {
		return annotations;
	}

	public boolean isCDIAnnotated() {
		return injectAnnotation != null || producesAnnotation != null;
	}

	public AnnotationDeclaration getProducesAnnotation() {
		return producesAnnotation;
	}

	public AnnotationDeclaration getInjectAnnotation() {
		return injectAnnotation;
	}

}
