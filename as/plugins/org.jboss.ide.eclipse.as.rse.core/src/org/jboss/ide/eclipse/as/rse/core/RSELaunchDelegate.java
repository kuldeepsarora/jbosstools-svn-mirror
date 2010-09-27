/******************************************************************************* 
 * Copyright (c) 2010 Red Hat, Inc. 
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
package org.jboss.ide.eclipse.as.rse.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.rse.core.RSECorePlugin;
import org.eclipse.rse.core.model.IHost;
import org.eclipse.rse.core.subsystems.ISubSystem;
import org.eclipse.rse.services.clientserver.messages.SystemMessageException;
import org.eclipse.rse.services.shells.IHostOutput;
import org.eclipse.rse.services.shells.IHostShell;
import org.eclipse.rse.services.shells.IHostShellChangeEvent;
import org.eclipse.rse.services.shells.IHostShellOutputListener;
import org.eclipse.rse.services.shells.IShellService;
import org.eclipse.rse.subsystems.shells.core.subsystems.servicesubsystem.IShellServiceSubSystem;
import org.eclipse.wst.server.core.IServer;
import org.jboss.ide.eclipse.as.core.server.internal.JBossServer;
import org.jboss.ide.eclipse.as.core.server.internal.JBossServerBehavior;
import org.jboss.ide.eclipse.as.core.server.internal.launch.AbstractJBossLaunchConfigType;
import org.jboss.ide.eclipse.as.core.server.internal.launch.JBossServerStartupLaunchConfiguration;
import org.jboss.ide.eclipse.as.core.server.internal.launch.JBossServerStartupLaunchConfiguration.IStartLaunchSetupParticipant;
import org.jboss.ide.eclipse.as.core.server.internal.launch.JBossServerStartupLaunchConfiguration.StartLaunchDelegate;
import org.jboss.ide.eclipse.as.core.server.internal.launch.LocalJBossServerStartupLaunchUtil;
import org.jboss.ide.eclipse.as.core.util.ArgsUtil;
import org.jboss.ide.eclipse.as.core.util.IJBossRuntimeConstants;
import org.jboss.ide.eclipse.as.core.util.IJBossRuntimeResourceConstants;
import org.jboss.ide.eclipse.as.core.util.ServerConverter;

public class RSELaunchDelegate implements StartLaunchDelegate, IStartLaunchSetupParticipant {

	public static final String RSE_STARTUP_COMMAND = "org.jboss.ide.eclipse.as.rse.core.RSELaunchDelegate.STARTUP_COMMAND";
	public static final String RSE_SHUTDOWN_COMMAND = "org.jboss.ide.eclipse.as.rse.core.RSELaunchDelegate.SHUTDOWN_COMMAND";
	public static final String DETECT_STARTUP_COMMAND = "org.jboss.ide.eclipse.as.rse.core.RSELaunchDelegate.DETECT_STARTUP_COMMAND";
	public static final String DETECT_SHUTDOWN_COMMAND = "org.jboss.ide.eclipse.as.rse.core.RSELaunchDelegate.DETECT_SHUTDOWN_COMMAND";
	
	
	public void actualLaunch(
			JBossServerStartupLaunchConfiguration launchConfig,
			ILaunchConfiguration configuration, String mode, ILaunch launch,
			IProgressMonitor monitor) throws CoreException {
		JBossServerBehavior beh = LocalJBossServerStartupLaunchUtil.getServerBehavior(configuration);
		beh.setServerStarting();
		String command = configuration.getAttribute(RSE_STARTUP_COMMAND, (String)null);
		IShellService service = null;
		try {
			service = findShellService(beh);
		} catch(CoreException ce) {
			beh.setServerStopped();
			throw ce;
		}
		IHostShell hs = null;
		IHostShellOutputListener listener = null;
		listener = new IHostShellOutputListener(){
			public void shellOutputChanged(IHostShellChangeEvent event) {
				IHostOutput[] out = event.getLines();
				for(int i = 0; i < out.length; i++ ) {
					// TODO listen here for obvious exceptions or failures
					System.out.println(out[i]);
				}
			}
		};

		try {
			hs = service.runCommand("/", command, new String[]{}, new NullProgressMonitor());
			hs.addOutputListener(listener);
			int x = 0;
			while( x < 30000) {
				x+=1000;
				try {
					Thread.sleep(1000);
				} catch(InterruptedException ie) {
				}
			}
			
			// Now launch ping thread
		} catch(SystemMessageException sme) {
			sme.printStackTrace();
		} catch(RuntimeException re) {
			String className = service.getClass().getName(); 
			if(re instanceof NullPointerException && className.endsWith(".DStoreShellService")) {
				beh.setServerStopped();
				throw new CoreException(new Status(IStatus.ERROR, org.jboss.ide.eclipse.as.rse.core.RSECorePlugin.PLUGIN_ID, 
						"no remote daemon installed. Please install a remote daemon or use an RSE server configured for ssh rather than dstore"));
			}
		}

		// Exiting the shell cancels the process. PROBLEM!!!
//		if( hs != null ) {
//			hs.exit();
//		}
		beh.setServerStarted();
	}

	public static void launchCommandNoResult(JBossServerBehavior behaviour, int delay, String command) {
		IShellService service = null;
		try {
			service = findShellService(behaviour);
		} catch(CoreException ce) {
			// TODO log and return
			return;
		}
		try {
			final IHostShell hs = service.runCommand("/", command, new String[]{}, new NullProgressMonitor());
			if( hs != null ) {
				try {
					Thread.sleep(delay);
				} catch(InterruptedException ie) {
					// ignore
				}
			}
		} catch( SystemMessageException sme) {
			// TODO
			sme.printStackTrace();
		} catch( RuntimeException re ) {
			// TODO
			re.printStackTrace();
		}
	}
	
	public static void launchStopServerCommand(JBossServerBehavior behaviour) {
		behaviour.setServerStopping();
		IPath home = new Path(RSEUtils.getRSEHomeDir(behaviour.getServer()));
		IPath shutdown = home.append(IJBossRuntimeResourceConstants.BIN)
							.append(IJBossRuntimeResourceConstants.SHUTDOWN_SH);
		String hostname = behaviour.getServer().getHost();
		JBossServer jbs = ServerConverter.getJBossServer(behaviour.getServer());
		
		String user = jbs.getUsername();
		String pass = jbs.getPassword(); 
		IJBossRuntimeConstants rc = new IJBossRuntimeConstants() {};
		final String command = shutdown.toString() + rc.SPACE + rc.SHUTDOWN_STOP_ARG + rc.SPACE
						+ rc.SHUTDOWN_SERVER_ARG + rc.SPACE + hostname + rc.SPACE + rc.SHUTDOWN_USER_ARG 
						+ rc.SPACE + user + rc.SPACE + rc.SHUTDOWN_PASS_ARG + rc.SPACE + pass;
		
		IShellService service = null;
		try {
			service = findShellService(behaviour);
		} catch(CoreException ce) {
			// TODO log and return
			return;
		}
		
		final boolean[] saving = new boolean[1];
		saving[0] = false;
		final String[] output = new String[1];
		output[0] = null;
		try {
			final IHostShell hs = service.runCommand("/", command, new String[]{}, new NullProgressMonitor());
			hs.addOutputListener(new IHostShellOutputListener(){
				public void shellOutputChanged(IHostShellChangeEvent event) {
					IHostOutput[] out = event.getLines();
					for(int i = 0; i < out.length; i++ ) {
						if( saving[0] ) {
							output[0] = out[i].getString();
							saving[0] = false;
							hs.exit();
						}
						/* 
						 * This is an extreme hack, because for some reason, 
						 * when the command line comes back, there's an extra space
						 * "shutdown .sh"
						 */
						String outNoSpace = out[i].getString().replaceAll(" ", "");
						String commandNoSpace = command.replaceAll(" ", "");
						boolean contains = outNoSpace.contains(commandNoSpace);
						if(!saving[0] && contains)
							saving[0] = true;
					}
				}
			});
			
			while(output[0] != null ) {
				try {
					Thread.sleep(200);
				} catch(InterruptedException ie) {
				}
			}
			// can log the output somewhere? 
			behaviour.setServerStopped();
		} catch( SystemMessageException sme) {
			// TODO
			sme.printStackTrace();
		} catch( RuntimeException re ) {
			if( re instanceof NullPointerException && service.getClass().getName().equals("DStoreShellService")) {
				// remote server has no dstore shell service
				behaviour.setServerStopped(); // behaviour.setServerStarted(); // failed
			}
		}
	}
	
	
	public boolean preLaunchCheck(ILaunchConfiguration configuration,
			String mode, IProgressMonitor monitor) throws CoreException {
		return true;
	}

	public void preLaunch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
	}

	public void postLaunch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
	}

	public void setupLaunchConfiguration(
			ILaunchConfigurationWorkingCopy workingCopy, IServer server)
			throws CoreException {
		boolean detectStartupCommand, detectShutdownCommand;
		detectStartupCommand = workingCopy.getAttribute(DETECT_STARTUP_COMMAND, true);
		detectShutdownCommand = workingCopy.getAttribute(DETECT_SHUTDOWN_COMMAND, true);
		
		String currentStartupCmd = workingCopy.getAttribute(RSELaunchDelegate.RSE_STARTUP_COMMAND, (String)null);
		if( detectStartupCommand || currentStartupCmd == null || "".equals(currentStartupCmd)) {
			workingCopy.setAttribute(RSELaunchDelegate.RSE_STARTUP_COMMAND, getDefaultLaunchCommand(workingCopy));
		}

		String currentStopCmd = workingCopy.getAttribute(RSELaunchDelegate.RSE_SHUTDOWN_COMMAND, (String)null);
		if( detectShutdownCommand || currentStopCmd == null || "".equals(currentStopCmd)) {
			workingCopy.setAttribute(RSELaunchDelegate.RSE_SHUTDOWN_COMMAND, getDefaultStopCommand(server));
		}
		/*
		 *   /usr/lib/jvm/jre/bin/java -Dprogram.name=run.sh -server -Xms1530M -Xmx1530M 
		 *   -XX:PermSize=425M -XX:MaxPermSize=425M -Dorg.jboss.resolver.warning=true 
		 *   -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 
		 *   -Djboss.partition.udpGroup=228.1.2.3 -Djboss.webpartition.mcast_port=45577 
		 *   -Djboss.hapartition.mcast_port=45566 -Djboss.ejb3entitypartition.mcast_port=43333 
		 *   -Djboss.ejb3sfsbpartition.mcast_port=45551 -Djboss.jvmRoute=node-10.209.183.100 
		 *   -Djboss.gossip_port=12001 -Djboss.gossip_refresh=5000 -Djava.awt.headless=true 
		 *   -Djava.net.preferIPv4Stack=true 
		 *   -Djava.endorsed.dirs=/opt/jboss-eap-5.1.0.Beta/jboss-as/lib/endorsed 
		 *   -classpath /opt/jboss-eap-5.1.0.Beta/jboss-as/bin/run.jar org.jboss.Main 
		 *   -c default -b 10.209.183.100
		 */
	}
	
	public static String getDefaultStopCommand(IServer server) {
		String rseHome = server.getAttribute(RSEUtils.RSE_SERVER_HOME_DIR, "");
		JBossServer jbs = ServerConverter.getJBossServer(server);
		// initialize stop command to something reasonable
		String username = jbs.getUsername();
		String pass = jbs.getPassword();
		
		String stop = new Path(rseHome).append(IJBossRuntimeResourceConstants.BIN).append(IJBossRuntimeResourceConstants.SHUTDOWN_SH).toString() + 
			IJBossRuntimeConstants.SPACE + IJBossRuntimeConstants.SHUTDOWN_STOP_ARG + IJBossRuntimeConstants.SPACE + IJBossRuntimeConstants.SHUTDOWN_SERVER_ARG + 
			IJBossRuntimeConstants.SPACE + server.getHost() + IJBossRuntimeConstants.SPACE +
			IJBossRuntimeConstants.SHUTDOWN_USER_ARG + IJBossRuntimeConstants.SPACE + 
			username + IJBossRuntimeConstants.SPACE + IJBossRuntimeConstants.SHUTDOWN_PASS_ARG + IJBossRuntimeConstants.SPACE + pass;
		return stop;
	}
	
	public static IServer findServer(ILaunchConfiguration config) throws CoreException {
		String serverId = config.getAttribute("server-id", (String)null);
		JBossServer jbs = AbstractJBossLaunchConfigType.findJBossServer(serverId);
		return jbs.getServer();
	}
	
	public static String getDefaultLaunchCommand(ILaunchConfiguration config) throws CoreException {
		IServer server = findServer(config);
		String rseHome = server.getAttribute(RSEUtils.RSE_SERVER_HOME_DIR, "");
		// initialize startup command to something reasonable
		String currentArgs = config.getAttribute(IJavaLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS, ""); //$NON-NLS-1$
		String currentVMArgs = config.getAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS, ""); //$NON-NLS-1$
		
		currentVMArgs= ArgsUtil.setArg(currentVMArgs, null,
				IJBossRuntimeConstants.SYSPROP + IJBossRuntimeConstants.ENDORSED_DIRS,
				new Path(rseHome).append(
						IJBossRuntimeResourceConstants.LIB).append(
								IJBossRuntimeResourceConstants.ENDORSED).toOSString(), true);

		String libPath = new Path(rseHome).append(IJBossRuntimeResourceConstants.BIN)
				.append(IJBossRuntimeResourceConstants.NATIVE).toOSString();
		currentVMArgs= ArgsUtil.setArg(currentVMArgs, null,
				IJBossRuntimeConstants.SYSPROP + IJBossRuntimeConstants.JAVA_LIB_PATH,
				libPath, true);

		
		String cmd = "java " + currentVMArgs + "-classpath " + 
			new Path(rseHome).append(IJBossRuntimeResourceConstants.BIN).append(
					IJBossRuntimeResourceConstants.START_JAR).toString() + IJBossRuntimeConstants.SPACE + 
					IJBossRuntimeConstants.START_MAIN_TYPE + IJBossRuntimeConstants.SPACE + currentArgs + "&";
		return cmd;
	}
	
	protected static IShellService findShellService(JBossServerBehavior behaviour) throws CoreException {
		String connectionName = RSEUtils.getRSEConnectionName(behaviour.getServer());
		IHost host = RSEUtils.findHost(connectionName);
		if( host == null ) {
			throw new CoreException(new Status(IStatus.ERROR, org.jboss.ide.eclipse.as.rse.core.RSECorePlugin.PLUGIN_ID, 
					"Host not found. Host may have been deleted or RSE model may not be completely loaded"));
		}
		ISubSystem[] systems = RSECorePlugin.getTheSystemRegistry().getSubSystems(host);
		for( int i = 0; i < systems.length; i++ ) {
			if( systems[i] instanceof IShellServiceSubSystem)
				return ((IShellServiceSubSystem)systems[i]).getShellService();
		}
		throw new CoreException(new Status(IStatus.ERROR, org.jboss.ide.eclipse.as.rse.core.RSECorePlugin.PLUGIN_ID, "No Shell Service Found"));
	}

}
