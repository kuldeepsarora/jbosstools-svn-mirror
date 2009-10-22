/*******************************************************************************
 * Copyright (c) 2008 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.smooks.graphical.editors;

import java.util.List;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.jboss.tools.smooks.configuration.editors.actions.ISmooksActionGrouper;
import org.jboss.tools.smooks.configuration.editors.actions.JavaBean11ActionGrouper;
import org.jboss.tools.smooks.editor.ISmooksModelProvider;
import org.jboss.tools.smooks.gef.model.AbstractSmooksGraphicalModel;
import org.jboss.tools.smooks.graphical.editors.model.JavaBeanGraphModel;
import org.jboss.tools.smooks.model.javabean.BindingsType;
import org.jboss.tools.smooks.model.javabean.ExpressionType;
import org.jboss.tools.smooks.model.javabean.ValueType;
import org.jboss.tools.smooks.model.javabean.WiringType;
import org.jboss.tools.smooks.model.javabean12.BeanType;

/**
 * @author Dart
 * 
 */
public class SmooksJavaMappingGraphicalEditor extends SmooksGraphicalEditorPart {

	public SmooksJavaMappingGraphicalEditor(ISmooksModelProvider provider) {
		super(provider);
		// TODO Auto-generated constructor stub
	}
	
	

	/* (non-Javadoc)
	 * @see org.jboss.tools.smooks.graphical.editors.SmooksGraphicalEditorPart#getPaletteRoot()
	 */
	@Override
	protected PaletteRoot getPaletteRoot() {
		SmooksGraphicalEditorPaletteRootCreator creator = new SmooksGraphicalEditorPaletteRootCreator(
				this.smooksModelProvider, (AdapterFactoryEditingDomain) this.smooksModelProvider.getEditingDomain(),
				getSmooksResourceListType()){

					/* (non-Javadoc)
					 * @see org.jboss.tools.smooks.graphical.editors.SmooksGraphicalEditorPaletteRootCreator#fillActionGrouper(java.util.List)
					 */
					@Override
					protected void fillActionGrouper(List<ISmooksActionGrouper> grouperList) {
						grouperList.add(new JavaBean11ActionGrouper());
					}
			
		};
		return creator.createPaletteRoot();
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.jboss.tools.smooks.graphical.editors.SmooksGraphicalEditorPart#
	 * createConnectionModelFactory()
	 */
	@Override
	protected ConnectionModelFactory createConnectionModelFactory() {
		return new JavaMappingConnectionModelFactory();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.jboss.tools.smooks.graphical.editors.SmooksGraphicalEditorPart#
	 * createGraphicalModelFactory()
	 */
	@Override
	protected GraphicalModelFactory createGraphicalModelFactory() {
		return new JavaMappingGraphicalModelFactory();
	}

	private class JavaMappingConnectionModelFactory extends ConnectionModelFactoryImpl {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.jboss.tools.smooks.graphical.editors.ConnectionModelFactoryImpl
		 * #hasXSLConnection
		 * (org.jboss.tools.smooks.gef.model.AbstractSmooksGraphicalModel)
		 */
		@Override
		public boolean hasXSLConnection(AbstractSmooksGraphicalModel model) {
			return false;
		}

	}

	private class JavaMappingGraphicalModelFactory extends GraphicalModelFactoryImpl {
		protected String getGraphLabelText(Object element) {
			Object obj = AdapterFactoryEditingDomain.unwrap(element);
			if (obj instanceof BeanType) {
				String p = ((BeanType) obj).getBeanId();
				if (p == null) {
					p = "<NULL>";
				}
				return p;
			}
			if (obj instanceof BindingsType) {
				String p = ((BindingsType) obj).getBeanId();
				if (p == null) {
					p = "<NULL>";
				}
				return p;
			}

			if (obj instanceof ValueType) {
				String p = ((ValueType) obj).getProperty();
				if (p == null) {
					p = "<NULL>";
				}
				return p;
			}
			if (obj instanceof WiringType) {
				String p = ((WiringType) obj).getProperty();
				if (p == null) {
					p = "<NULL>";
				}
				return p;
			}
			if (obj instanceof ExpressionType) {
				String p = ((ExpressionType) obj).getProperty();
				if (p == null) {
					p = "<NULL>";
				}
				return p;
			}

			if (obj instanceof org.jboss.tools.smooks.model.javabean12.ValueType) {
				String p = ((org.jboss.tools.smooks.model.javabean12.ValueType) obj).getProperty();
				if (p == null) {
					p = "<NULL>";
				}
				return p;
			}
			if (obj instanceof org.jboss.tools.smooks.model.javabean12.WiringType) {
				String p = ((org.jboss.tools.smooks.model.javabean12.WiringType) obj).getProperty();
				if (p == null) {
					p = "<NULL>";
				}
				return p;
			}
			if (obj instanceof org.jboss.tools.smooks.model.javabean12.ExpressionType) {
				String p = ((org.jboss.tools.smooks.model.javabean12.ExpressionType) obj).getProperty();
				if (p == null) {
					p = "<NULL>";
				}
				return p;
			}
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.jboss.tools.smooks.graphical.editors.GraphicalModelFactoryImpl
		 * #createGraphicalModel(java.lang.Object,
		 * org.jboss.tools.smooks.editor.ISmooksModelProvider)
		 */
		@Override
		public Object createGraphicalModel(Object model, ISmooksModelProvider provider) {
			if (canCreateGraphicalModel(model, provider)) {
				AbstractSmooksGraphicalModel graphModel = null;
				AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain) provider.getEditingDomain();
				ITreeContentProvider contentProvider = new AdapterFactoryContentProvider(editingDomain
						.getAdapterFactory());
				ILabelProvider labelProvider = createLabelProvider(editingDomain.getAdapterFactory());

				if (model instanceof BindingsType || model instanceof BeanType) {
					graphModel = new JavaBeanGraphModel(model, contentProvider, labelProvider, provider);
					((JavaBeanGraphModel) graphModel).setHeaderVisable(true);
				}
				// if (model instanceof Xsl) {
				// graphModel = new XSLTemplateGraphicalModel(model, new
				// XSLTemplateContentProvider(contentProvider),
				// new XSLLabelProvider(labelProvider), provider);
				// ((TreeContainerModel) graphModel).setHeaderVisable(true);
				// }
				// if (graphModel == null && model instanceof
				// AbstractResourceConfig) {
				// graphModel = new ResourceConfigGraphModelImpl(model,
				// contentProvider, labelProvider, provider);
				// ((ResourceConfigGraphModelImpl)
				// graphModel).setHeaderVisable(true);
				// }
				if (graphModel != null) {
					return graphModel;
				}
				return super.createGraphicalModel(graphModel, provider);
			}
			return null;
		}

	}

}
