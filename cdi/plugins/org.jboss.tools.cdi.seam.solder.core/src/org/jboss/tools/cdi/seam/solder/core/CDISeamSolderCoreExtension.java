package org.jboss.tools.cdi.seam.solder.core;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.jboss.tools.cdi.core.IAnnotated;
import org.jboss.tools.cdi.core.IAnnotationDeclaration;
import org.jboss.tools.cdi.core.IParametedType;
import org.jboss.tools.cdi.core.extension.ICDIExtension;
import org.jboss.tools.cdi.core.extension.feature.IBeanNameFeature;
import org.jboss.tools.cdi.core.extension.feature.IProcessAnnotatedTypeFeature;
import org.jboss.tools.cdi.internal.core.impl.ParametedType;
import org.jboss.tools.cdi.internal.core.impl.TypeDeclaration;
import org.jboss.tools.cdi.internal.core.impl.definition.DefinitionContext;
import org.jboss.tools.cdi.internal.core.impl.definition.FieldDefinition;
import org.jboss.tools.cdi.internal.core.impl.definition.MethodDefinition;
import org.jboss.tools.cdi.internal.core.impl.definition.ParameterDefinition;
import org.jboss.tools.cdi.internal.core.impl.definition.TypeDefinition;
import org.jboss.tools.common.model.util.EclipseResourceUtil;
import org.jboss.tools.common.util.EclipseJavaUtil;

public class CDISeamSolderCoreExtension implements ICDIExtension,
		IProcessAnnotatedTypeFeature {

	public Object getAdapter(Class adapter) {
		if (adapter == IBeanNameFeature.class) {
			return BeanNameFeature.instance;
		}
		return null;
	}

	public void processAnnotatedType(TypeDefinition typeDefinition,
			DefinitionContext context) {
		if (typeDefinition
				.isAnnotationPresent(CDISeamSolderConstants.VETO_ANNOTATION_TYPE_NAME)
				|| (typeDefinition.getPackageDefinition() != null && typeDefinition
						.getPackageDefinition()
						.isAnnotationPresent(
								CDISeamSolderConstants.VETO_ANNOTATION_TYPE_NAME))) {
			typeDefinition.veto();
			return;
		}

		Set<String> requiredClasses = new HashSet<String>();
		List<String> typeRequiredClasses = getRequiredClasses(typeDefinition);
		if (typeRequiredClasses != null)
			requiredClasses.addAll(typeRequiredClasses);
		List<String> packageRequiredClasses = getRequiredClasses(typeDefinition
				.getPackageDefinition());
		;
		if (packageRequiredClasses != null)
			requiredClasses.addAll(packageRequiredClasses);
		IJavaProject jp = EclipseResourceUtil.getJavaProject(context
				.getProject().getProject());
		if (!requiredClasses.isEmpty() && jp != null) {
			for (String c : requiredClasses) {
				try {
					if (EclipseJavaUtil.findType(jp, c) == null) {
						typeDefinition.veto();
						return;
					}
				} catch (JavaModelException e) {
					CDISeamSolderCorePlugin.getDefault().logError(e);
					typeDefinition.veto();
					return;
				}
			}
		}

		List<FieldDefinition> fs = typeDefinition.getFields();
		for (FieldDefinition f : fs) {
			TypeDeclaration exact = getExactType(f, typeDefinition, context);
			if (exact != null) {
				f.setOverridenType(exact);
			}
		}

		List<MethodDefinition> ms = typeDefinition.getMethods();
		for (MethodDefinition m : ms) {
			List<ParameterDefinition> ps = m.getParameters();
			for (ParameterDefinition p : ps) {
				TypeDeclaration exact = getExactType(p, typeDefinition, context);
				if (exact != null) {
					p.setOverridenType(exact);
				}
			}
		}
	}

	private List<String> getRequiredClasses(IAnnotated d) {
		if (d == null)
			return null;
		IAnnotationDeclaration requires = d
				.getAnnotation(CDISeamSolderConstants.REQUIRES_ANNOTATION_TYPE_NAME);
		return requires != null ? getArrayValue(requires) : null;
	}

	private List<String> getArrayValue(IAnnotationDeclaration d) {
		Object value = d.getMemberValue(null);
		List<String> result = new ArrayList<String>();
		if (value instanceof Object[]) {
			Object[] array = (Object[]) value;
			for (int i = 0; i < array.length; i++) {
				if (array[i] != null)
					result.add(array[i].toString());
			}
		} else if (value instanceof String) {
			result.add(value.toString());
		}
		return result;
	}

	private TypeDeclaration getExactType(IAnnotated annotated, TypeDefinition declaringType, DefinitionContext context) {
		IAnnotationDeclaration a = annotated.getAnnotation(CDISeamSolderConstants.EXACT_ANNOTATION_TYPE_NAME);
		if(a != null) {
			Object o = a.getMemberValue(null);
			if(o != null) {
				String s = o.toString();
				if(s.length() > 0) {
					try {
						ParametedType p = context.getProject().getTypeFactory().getParametedType(declaringType.getType(), "Q" + s + ";");
						int b = a.getStartPosition();
						int e = b + a.getLength();
						if(b >= 0 && e > b) {
							String content = declaringType.getContent().substring(b, e);
							int i = content.indexOf(s);
							if(i >= 0) {
								b = i;
								e = i + s.length();
							}
						}
						return new TypeDeclaration(p, b, e - b);
					} catch (JavaModelException e) {
						CDISeamSolderCorePlugin.getDefault().logError(e);
					}
				}
			}
		}
		return null;
	}
}
