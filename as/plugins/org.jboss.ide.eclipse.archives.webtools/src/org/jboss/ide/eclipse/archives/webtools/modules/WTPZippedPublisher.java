/******************************************************************************* 
 * Copyright (c) 2011 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 * 
 * TODO: Logging and Progress Monitors
 ******************************************************************************/ 
package org.jboss.ide.eclipse.archives.webtools.modules;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.model.IModuleFile;
import org.eclipse.wst.server.core.model.IModuleResourceDelta;
import org.eclipse.wst.server.core.util.ModuleFile;
import org.jboss.ide.eclipse.as.core.Trace;
import org.jboss.ide.eclipse.as.core.publishers.PublishUtil;
import org.jboss.ide.eclipse.as.core.server.IDeployableServer;
import org.jboss.ide.eclipse.as.core.server.IJBossServerPublishMethod;
import org.jboss.ide.eclipse.as.core.server.IJBossServerPublisher;
import org.jboss.ide.eclipse.as.core.server.IPublishCopyCallbackHandler;
import org.jboss.ide.eclipse.as.core.server.internal.v7.DeploymentMarkerUtils;
import org.jboss.ide.eclipse.as.core.util.IJBossToolingConstants;
import org.jboss.ide.eclipse.as.core.util.ServerConverter;

public class WTPZippedPublisher implements IJBossServerPublisher {
	private int moduleState = IServer.PUBLISH_STATE_NONE;
	
	public boolean accepts(String method, IServer server, IModule[] module) {
		IDeployableServer ds = ServerConverter.getDeployableServer(server);
		return ds != null && (module == null || ds.zipsWTPDeployments());
	}
	
	public int getPublishState() {
		return moduleState;
	}
	
	protected String getDeployRoot(IModule[] module, IDeployableServer ds) {
		return PublishUtil.getDeployRootFolder(
				module, ds, ds.getDeployFolder(), 
				IJBossToolingConstants.LOCAL_DEPLOYMENT_LOC);
	}
	
	public IStatus publishModule(
			IJBossServerPublishMethod method,
			IServer server, IModule[] module,
			int publishType, IModuleResourceDelta[] delta,
			IProgressMonitor monitor) throws CoreException {
		// Build all parts together at once. 
		// When a call for [ear, childWar] comes in, ignore it. 
		IStatus status = Status.OK_STATUS;
		
		if( module.length > 1 ) 
			return null;
		
		if( DeploymentMarkerUtils.supportsJBoss7MarkerDeployment(server)) {
			status = handleJBoss7Deployment(method, server, module, publishType, delta, monitor);
		} else {		
			Trace.trace(Trace.STRING_FINER, "Using as<=6 publishModule logic in WTPZippedPublisher for module " + module[module.length-1].getName() ); //$NON-NLS-1$
			IDeployableServer ds = ServerConverter.getDeployableServer(server);
			String deployRoot = getDeployRoot(module, ds); 
			LocalZippedPublisherUtil util = new LocalZippedPublisherUtil();
			status = util.publishModule(server, deployRoot, module, publishType, delta, monitor);
			Trace.trace(Trace.STRING_FINER, "Zipping complete for module " + module[module.length-1].getName() ); //$NON-NLS-1$			monitor.done();
		}
		return status;
	}
	
	public IStatus handleJBoss7Deployment(
			IJBossServerPublishMethod method,
			IServer server, IModule[] module,
			int publishType, IModuleResourceDelta[] delta,
			IProgressMonitor monitor) throws CoreException {
		IDeployableServer ds = ServerConverter.getDeployableServer(server);
		String deployRoot = getDeployRoot(module, ds);
		if( publishType == IJBossServerPublisher.REMOVE_PUBLISH) {
			Trace.trace(Trace.STRING_FINER, "Removing .dodeploy marker in WTPZippedPublisher to undeploy module " + module[module.length-1].getName() ); //$NON-NLS-1$
			DeploymentMarkerUtils.removeDeployedMarkerIfExists(method, ds, module, monitor);
		} else {
			Trace.trace(Trace.STRING_FINER, "Zipping module in WTPZippedPublisher for module " + module[module.length-1].getName() ); //$NON-NLS-1$
			LocalZippedPublisherUtil util = new LocalZippedPublisherUtil();
			IStatus s = util.publishModule(server, deployRoot, module, publishType, delta, monitor);
			IPath outPath = util.getOutputFilePath();
			if( util.hasBeenChanged()) {
				Trace.trace(Trace.STRING_FINER, "Output zip changed. Copying file to destination. WTPZippedPublisher for module " + module[module.length-1].getName() ); //$NON-NLS-1$

				// Copy out file
				IPath depPath = PublishUtil.getDeployPath(method, module, ds);
				IPath folder = depPath.removeLastSegments(1);
				IPublishCopyCallbackHandler callback = method.getCallbackHandler(folder, server);
				IModuleFile mf = new ModuleFile(outPath.toFile(), "", new Path("/")); //$NON-NLS-1$ //$NON-NLS-2$
				callback.copyFile(mf, new Path(depPath.lastSegment()), monitor);

				// Add marker
				DeploymentMarkerUtils.addDoDeployMarker(method, ds, module, new NullProgressMonitor());
			}
			monitor.done();
			return s;
		}
		monitor.done();
		return Status.OK_STATUS;
	}
}
