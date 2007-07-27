/*******************************************************************************
 * Copyright (c) 2007 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributor:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.hibernate.ui.veditor.editors.parts;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.swt.graphics.RGB;
import org.hibernate.mapping.RootClass;
import org.hibernate.mapping.Table;
import org.jboss.tools.hibernate.ui.veditor.editors.figures.ComponentFigure;
import org.jboss.tools.hibernate.ui.veditor.editors.figures.TitleLabel;
import org.jboss.tools.hibernate.ui.veditor.editors.figures.TopLineBorder;
import org.jboss.tools.hibernate.ui.veditor.editors.model.ComponentShape;
import org.jboss.tools.hibernate.ui.veditor.editors.model.Connection;
import org.jboss.tools.hibernate.ui.veditor.editors.model.ExpandeableShape;
import org.jboss.tools.hibernate.ui.veditor.editors.model.OrmDiagram;
import org.jboss.tools.hibernate.ui.veditor.editors.model.OrmShape;
import org.jboss.tools.hibernate.ui.veditor.editors.model.Shape;


public class ExpandeableShapeEditPart extends ShapeEditPart {
	protected IFigure createFigure() {
		if (getModel() instanceof Shape) {
			Label label = new TitleLabel();
			label.setText(ormLabelProvider.getText(getElement()));	
			label.setBackgroundColor(getColor());
			label.setIcon(ormLabelProvider.getImage(getElement()));
			label.setLabelAlignment(PositionConstants.LEFT);
			label.setOpaque(true);
			TopLineBorder border = new TopLineBorder(1,2+getCastedModel().getIndent(),1,2);
			border.setColor(getOrmShapeEditPart().getColor());
			label.setBorder(border);
			return label;
		} else {
			throw new IllegalArgumentException();
		}
	}
	public void performRequest(Request req) {
		if(RequestConstants.REQ_OPEN.equals(req.getType())) {
			((ExpandeableShape)getModel()).refreshReferences(getViewer().getContents().getModel());
		}
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		if (Shape.SHOW_SELECTION.equals(prop)) {
			if(getFigure().getChildren().size() > 0){
				((IFigure)getFigure().getChildren().get(0)).setBackgroundColor(getSelectionColor());	
				((IFigure)getFigure().getChildren().get(0)).setForegroundColor(ResourceManager.getInstance().getColor(new RGB(255,255,255)));
			}
		} else if (Shape.HIDE_SELECTION.equals(prop)) {
			if(getFigure().getChildren().size() > 0){
				((IFigure)getFigure().getChildren().get(0)).setBackgroundColor(getColor());		
				((IFigure)getFigure().getChildren().get(0)).setForegroundColor(ResourceManager.getInstance().getColor(new RGB(0,0,0)));
			}
		}else if (ExpandeableShape.SHOW_REFERENCES.equals(prop)) {
			//referenceList.add((OrmShape)getCastedModel().getParent());
			//refreshReference((ExpandeableShape)getCastedModel(), ((ExpandeableShape)getCastedModel()).isReferenceVisible());
			refreshReferences((Shape)getCastedModel(), ((ExpandeableShape)getCastedModel()).isReferenceVisible());
			((TitleLabel)getFigure()).setHidden(!((ExpandeableShape)getCastedModel()).isReferenceVisible());
//			((IFigure)getFigure().getChildren().get(0)).setBackgroundColor(getSelectionColor());	
//			((IFigure)getFigure().getChildren().get(0)).setForegroundColor(ResourceManager.getInstance().getColor(new RGB(255,255,255)));
		} else {
			super.propertyChange(evt);
		}
	}
	
	protected ArrayList<OrmShape> referenceList = new ArrayList<OrmShape>();
	
	protected void refreshReference(ExpandeableShape shape, boolean visible){
		OrmShape refShape = shape.getReference();
		if(refShape == null) return;
		if(!isReferencesCorrect(refShape)) return;
		
		OrmEditPart refPart = (OrmEditPart)getViewer().getEditPartRegistry().get(refShape);
		if(refPart != null){
			refPart.getFigure().setVisible(visible);
			setLinksVisible(refPart, visible);
		}
		Object element = refShape.getOrmElement();
		if(element instanceof RootClass){
			RootClass rc = (RootClass)element;
			Table table = rc.getTable();
			OrmShape tableShape = refShape.getOrmDiagram().getShape(table);
			OrmEditPart tablePart = (OrmEditPart)getViewer().getEditPartRegistry().get(tableShape);
			if(tablePart != null){
				if(isTableCanBeInvisible(tablePart, visible)){
					tablePart.getFigure().setVisible(visible);
					setLinksVisible(tablePart, visible);
				}
			}
		}
	
		referenceList.add(refShape);
		for(int i=0;i<refShape.getChildren().size();i++){
			if(refShape.getChildren().get(i) instanceof ExpandeableShape){
				refreshReference((ExpandeableShape)refShape.getChildren().get(i), visible);
			}
		}
		referenceList.remove(refShape);
		shape.getOrmDiagram().update();
	}
	
	protected void refreshReferences(Shape shape, boolean visible){
		Connection link;
		OrmShape refShape;
		
		OrmEditPart shapePart = (OrmEditPart)getViewer().getEditPartRegistry().get(shape);
		
		for(int i=0;i<shape.getSourceConnections().size();i++){
			link = (Connection)shape.getSourceConnections().get(i);
			refShape = (OrmShape)link.getTarget().getOrmShape();
			if(refShape == null) continue;
			if(!isReferencesCorrect(refShape)) continue;
		
			OrmEditPart refPart = (OrmEditPart)getViewer().getEditPartRegistry().get(refShape);
			if(refPart != null){
				if(isShapeCanBeInvisible(shapePart, refPart, visible)){
					refPart.getFigure().setVisible(visible);
					setLinksVisible(refPart, visible);
				}
			}
			referenceList.add(shape.getOrmShape());
			refreshReferences(refShape, visible);
			referenceList.remove(shape.getOrmShape());
		}
	
		referenceList.add(shape.getOrmShape());
		
		for(int i=0;i<shape.getChildren().size();i++){
			refreshReferences((Shape)shape.getChildren().get(i), visible);
		}
		referenceList.remove(shape.getOrmShape());
		shape.getOrmDiagram().update();
	}
	
	private boolean isTableCanBeInvisible(OrmEditPart tablePart, boolean visible){
		if(visible) return true;
		ConnectionEditPart link;
		for(int i=0;i<tablePart.getTargetConnections().size();i++){
			link = (ConnectionEditPart)tablePart.getTargetConnections().get(i);
			if(link.getFigure().isVisible()) return false;
		}
		return true;
	}
	
	private boolean isShapeCanBeInvisible(OrmEditPart source, OrmEditPart target, boolean visible){
		if(visible) return true;
		ConnectionEditPart link;
		for(int i=0;i<target.getTargetConnections().size();i++){
			link = (ConnectionEditPart)target.getTargetConnections().get(i);
			if(link.getFigure().isVisible() && link.getSource() != source) return false;
		}
		return true;
	}
	
	private boolean isReferencesCorrect(OrmShape shape){
		if(shape == null) return false;
		for(int i=0;i < referenceList.size();i++){
			if(shape.equals(referenceList.get(i))) return false;
		}
		return true;
	}
	
	private void setLinksVisible(OrmEditPart editPart, boolean flag){
		ConnectionEditPart link;
		OrmEditPart child;
		
		for(int i=0;i<editPart.getSourceConnections().size();i++){
			link = (ConnectionEditPart)editPart.getSourceConnections().get(i);
			if(isLinkCanBeVisible(link, flag))
				link.getFigure().setVisible(flag);
		}
		for(int i=0;i<editPart.getTargetConnections().size();i++){
			link = (ConnectionEditPart)editPart.getTargetConnections().get(i);
			if(isLinkCanBeVisible(link, flag))
				link.getFigure().setVisible(flag);
		}
		for(int i=0;i<editPart.getChildren().size();i++){
			child = (OrmEditPart)editPart.getChildren().get(i);
			setLinksVisible(child, flag);
		}
	}
	
	private boolean isLinkCanBeVisible(ConnectionEditPart link, boolean visible){
		if(!visible) return true;
		if(!((OrmEditPart)link.getSource()).getFigure().isVisible()) return false;
		if(!((OrmEditPart)link.getTarget()).getFigure().isVisible()) return false;
		if(!validateShape((Shape)((OrmEditPart)link.getSource()).getModel())) return false;
		if(!validateShape((Shape)((OrmEditPart)link.getTarget()).getModel())) return false;
		return true;
	}
	
	private boolean validateShape(Shape shape){
		if(!shape.getClass().equals(OrmShape.class)){
			OrmShape ormShape = shape.getOrmShape();
			if(ormShape != null){
				if(ormShape.isHiden()) return false;
			}
		}
		ExpandeableShape expanableShape = shape.getExtendeableShape();
		if(expanableShape != null && !shape.equals(expanableShape) && !expanableShape.getClass().equals(OrmShape.class)){
			if(!expanableShape.isReferenceVisible()) return false;
		}
		
		
		return true;
	}
	
	
	protected List getModelChildren() {
		return ((ExpandeableShape)getModel()).getChildren(); 
	}
}
