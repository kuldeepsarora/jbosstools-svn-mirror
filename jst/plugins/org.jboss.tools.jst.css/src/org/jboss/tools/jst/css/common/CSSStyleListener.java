/*******************************************************************************
 * Copyright (c) 2007-2009 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributor:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/

package org.jboss.tools.jst.css.common;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapter;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.jboss.tools.jst.css.CssPlugin;

public class CSSStyleListener implements ISelectionListener, INodeAdapter,
		IPartListener {

	private static CSSStyleListener instance;

	private ListenerList listeners = new ListenerList();

	private CSSStyleManager styleManager = new CSSStyleManager();

	private StyleContainer currentStyle;

	private IWorkbenchPart currentPart;

	private CSSStyleListener() {
	}

	public synchronized static CSSStyleListener getInstance() {

		if (instance == null) {
			instance = new CSSStyleListener();
		}
		return instance;
	}

	public void addSelectionListener(ICSSViewListner listener) {

		// if added the first listener start listing
		if (listeners.size() == 0)
			startListening();

		listeners.add(listener);
	}

	public void removeSelectionListener(ICSSViewListner listener) {
		listeners.remove(listener);

		// if removed last listener start listing
		if (listeners.size() == 0)
			stopListening();
	}

	private void startListening() {
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService()
				.addPartListener(this);
		PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getSelectionService().addPostSelectionListener(this);

		// PlatformUI.getWorkbench().getActiveWorkbenchWindow()
		// .getSelectionService().addSelectionListener(this);
	}

	private void stopListening() {
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService()
				.removePartListener(this);
		PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getSelectionService().removePostSelectionListener(this);
		// PlatformUI.getWorkbench().getActiveWorkbenchWindow()
		// .getSelectionService().addSelectionListener(this);

	}

	public void selectionChanged(IWorkbenchPart part, ISelection selection) {

		StyleContainer newStyle = styleManager.recognizeCSSStyle(selection);

		if (isImportant(part)
				&& ((currentStyle == null) || !(currentStyle.equals(newStyle)))) {

			disconnect(currentStyle);
			connect(newStyle);
			currentStyle = newStyle;

			ISelection selectionToLiteners = null;

			if (newStyle != null && newStyle.isValid()) {
				selectionToLiteners = new StructuredSelection(newStyle);
			} else {
				selectionToLiteners = StructuredSelection.EMPTY;
			}

			Object[] array = listeners.getListeners();
			for (int i = 0; i < array.length; i++) {
				final ICSSViewListner l = (ICSSViewListner) array[i];
				if ((part != null) && (l != currentPart) && (selection != null)) {

					try {
						l.selectionChanged(part, selectionToLiteners);
					} catch (Exception e) {
						CssPlugin.log(e.getLocalizedMessage());
					}
				}

			}

		}

	}

	protected boolean isImportant(IWorkbenchPart part) {
		if ((part instanceof IEditorPart) || (part instanceof ContentOutline))
			return true;
		return false;
	}

	private void connect(StyleContainer style) {

		if (style != null) {
			style.addNodeListener(this);
		}

	}

	private void disconnect(StyleContainer style) {
		if (style != null) {
			style.removeNodelListener(this);
		}
	}

	public boolean isAdapterForType(Object type) {
		return type.equals(CSSStyleListener.class);
	}

	public void notifyChanged(INodeNotifier notifier, int eventType,
			Object changedFeature, Object oldValue, Object newValue, int pos) {
		Object[] array = listeners.getListeners();
		for (int i = 0; i < array.length; i++) {
			final ICSSViewListner l = (ICSSViewListner) array[i];

			if (currentPart != l) {
				try {
					l.styleChanged(currentStyle);
				} catch (Exception e) {
					CssPlugin.log(e.getLocalizedMessage());
				}
			}

		}

	}

	public void partActivated(IWorkbenchPart part) {
		currentPart = part;
		Object[] array = listeners.getListeners();
		for (int i = 0; i < array.length; i++) {
			final ICSSViewListner l = (ICSSViewListner) array[i];

			if (l instanceof IPartListener) {
				try {
					((IPartListener) l).partActivated(part);
				} catch (Exception e) {
					CssPlugin.log(e.getLocalizedMessage());
				}
			}
		}
	}

	public void partBroughtToTop(IWorkbenchPart part) {
		partActivated(part);
	}

	public void partClosed(IWorkbenchPart part) {
	}

	public void partDeactivated(IWorkbenchPart part) {
	}

	public void partOpened(IWorkbenchPart part) {
	}
}
