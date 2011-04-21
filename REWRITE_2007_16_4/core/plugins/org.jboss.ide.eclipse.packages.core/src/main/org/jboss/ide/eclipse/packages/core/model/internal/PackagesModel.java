/*
 * JBoss, a division of Red Hat
 * Copyright 2006, Red Hat Middleware, LLC, and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.ide.eclipse.packages.core.model.internal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.jboss.ide.eclipse.core.util.ProjectUtil;
import org.jboss.ide.eclipse.packages.core.Trace;
import org.jboss.ide.eclipse.packages.core.model.IPackage;
import org.jboss.ide.eclipse.packages.core.model.IPackageFileSet;
import org.jboss.ide.eclipse.packages.core.model.IPackageFolder;
import org.jboss.ide.eclipse.packages.core.model.IPackageNode;
import org.jboss.ide.eclipse.packages.core.model.IPackageReference;
import org.jboss.ide.eclipse.packages.core.model.IPackagesBuildListener;
import org.jboss.ide.eclipse.packages.core.model.IPackagesModelListener;
import org.jboss.ide.eclipse.packages.core.model.PackagesCore;
import org.jboss.ide.eclipse.packages.core.model.internal.xb.XMLBinding;
import org.jboss.ide.eclipse.packages.core.model.internal.xb.XbFileSet;
import org.jboss.ide.eclipse.packages.core.model.internal.xb.XbFolder;
import org.jboss.ide.eclipse.packages.core.model.internal.xb.XbPackage;
import org.jboss.ide.eclipse.packages.core.model.internal.xb.XbPackageNode;
import org.jboss.ide.eclipse.packages.core.model.internal.xb.XbPackages;
import org.jboss.ide.eclipse.packages.core.project.PackagesNature;

public class PackagesModel {

	private static PackagesModel _instance;
	
	public static final String PROJECT_PACKAGES_FILE = ".packages";
	
	private Hashtable projectPackages;
	private Hashtable xbPackages;
	private ArrayList buildListeners;
	private ArrayList modelListeners;
	private IProject projectBeingRegistered;
	 
	private PackagesModel ()
	{
		projectPackages = new Hashtable();
		xbPackages = new Hashtable();
		buildListeners = new ArrayList();
		modelListeners = new ArrayList();
		projectBeingRegistered = null;
		packageRefs = new Hashtable();
	}
	
	public static PackagesModel instance ()
	{
		if (_instance == null)
			_instance = new PackagesModel();
		
		return _instance;
	}
	
	public Hashtable getTopLevelPackageAndPathway (IPackageNode node)
	{
		ArrayList parents = new ArrayList();
		IPackageNode tmp = node.getParent(), top = tmp;
		
		while (tmp != null)
		{
			top = tmp;
			parents.add(0, top);
			
			tmp = tmp.getParent();
		}
		
		IPackage topLevelPackage = null;
		
		if (top instanceof IPackage)
			topLevelPackage = (IPackage)top;
		
		Hashtable pkgAndPath = new Hashtable();
		
		if (topLevelPackage != null)
			pkgAndPath.put(topLevelPackage, parents);
		
		return pkgAndPath;
	}
	
	/**
	 * This will return a hashtable of top-level packages and their corresponding
	 * pathways from the passed-in node. This is used for determining all the pathway possibilities
	 * for a single package node.
	 *
	 */
	public Hashtable getTopLevelPackagesAndPathways (IPackageNode node)
	{
		Hashtable pkgsAndPaths = new Hashtable();
		
		IPackage originalSource = PackagesCore.getTopLevelPackage(node);
		ArrayList parents = new ArrayList();
		
		IPackageNode parent = node.getParent();
		while (parent != null)
		{
			parents.add(0, parent);
			
			parent = parent.getParent();
		}
		pkgsAndPaths.put(originalSource, parents);
		
		parents = new ArrayList();
		
		IPackageNode tmp = node.getParent(), top = tmp;
		while (tmp != null)
		{
			top = tmp;
			if (top.getNodeType() == IPackageNode.TYPE_PACKAGE)
			{
				IPackage pkg = (IPackage) top;
				IPackageReference refs[] = pkg.getReferences();
				for (int i = 0; i < refs.length; i++)
				{
					Hashtable pkgAndPath = getTopLevelPackageAndPathway(refs[i]);
					Iterator iter = pkgAndPath.keySet().iterator();
					if (iter.hasNext())
					{
						IPackage topLevelPackage = (IPackage) iter.next();
						ArrayList pathway = (ArrayList) pkgAndPath.get(topLevelPackage);
						ArrayList fullPathway = new ArrayList(parents);
						fullPathway.addAll(pathway);
						fullPathway.add(refs[i]);
						
						pkgsAndPaths.put(topLevelPackage, fullPathway);
					}
				}
			} else {
				parents.add(0, top);
			}
			tmp = tmp.getParent();
		}
		
		return pkgsAndPaths;
	}
	
	public void registerProject(IProject project, IProgressMonitor monitor)
	{
		if (projectBeingRegistered == null)
		{
			projectBeingRegistered = project;
			monitor.beginTask("Loading configuration...", XMLBinding.NUM_UNMARSHAL_MONITOR_STEPS + 2);
			
			try {
				if (!project.hasNature(PackagesNature.NATURE_ID)) {
					ProjectUtil.addProjectNature(project, PackagesNature.NATURE_ID);
				}
			} catch (CoreException e) {
				Trace.trace(getClass(), e);
			}
			
			IFile packagesFile = project.getFile(PROJECT_PACKAGES_FILE);
			if (packagesFile.exists())
			{
				try {
					XbPackages packages = XMLBinding.unmarshal(packagesFile.getContents(), monitor);
					monitor.worked(1);
					
					if (packages == null)
					{
						// Empty / non-working XML file loaded
						Trace.trace(getClass(), "WARNING: .packages file for project " + project.getName() + " is empty or contains the wrong content");
						projectBeingRegistered = null;
						return;
					}
					
					xbPackages.put(project, packages);
					createPackageNodeImpl(project, packages, null);
					linkPackageReferences(project);
					
					monitor.worked(1);
					fireProjectRegistered(project);
				} catch (CoreException e) {
					Trace.trace(getClass(), e);
				}
			} else {
				// This is the first time this project has been registered (probably before the model has even been saved)
				
				saveModel(project, monitor);
				registerProject(project, monitor);
			}
			projectBeingRegistered = null;
		}
	}
	
	public IPackage createPackage(IProject project, boolean isTopLevel)
	{
		Assert.isNotNull(project);
		
		PackageImpl pkg = new PackageImpl(project, new XbPackage());
		pkg.setParentShouldBeNull(isTopLevel);
		
		return pkg;
	}
	
	public IPackageFolder createPackageFolder(IProject project) {
		Assert.isNotNull(project);
		
		return new PackageFolderImpl(project, new XbFolder());
	}

	public IPackageFileSet createPackageFileSet(IProject project) {
		Assert.isNotNull(project);
		
		return new PackageFileSetImpl(project, new XbFileSet());
	}
	
	public void removePackage(IPackage pkg)
	{
		removePackage(pkg, true);
	}
	
	public void removePackage (IPackage pkg, boolean save)
	{
		if (projectPackages.containsKey(pkg.getProject()))
		{
			if (getProjectPackages(pkg.getProject()).contains(pkg))
				getProjectPackages(pkg.getProject()).remove(pkg);
		}
		if (xbPackages.containsKey(pkg.getProject()))
		{
			PackageImpl packageImpl = (PackageImpl) pkg;
			
			XbPackages packages = getXbPackages(pkg.getProject());
			packages.removeChild(packageImpl.getNodeDelegate());
		}
		
		if (save)
			saveModel(pkg.getProject(), null);
		
		fireNodeRemoved(pkg);
	}
	
	public XbPackages getXbPackages(IProject project)
	{
		return (XbPackages) xbPackages.get(project);
	}
	
	public Set getRegisteredProjects ()
	{
		return projectPackages.keySet();
	}
	
	public List getProjectPackages (IProject project)
	{
		return (List)projectPackages.get(project);
	}
	
	public void addPackagesModelListener (IPackagesModelListener listener)
	{
		addPackagesModelListener (listener, modelListeners.size());
	}
	
	public void addPackagesModelListener (IPackagesModelListener listener, int index)
	{
		synchronized (modelListeners)
		{
			if (!modelListeners.contains(listener))
				modelListeners.add(index, listener);
		}
	}
	
	public void removePackagesModelListener (IPackagesModelListener listener)
	{
		synchronized (modelListeners)
		{
			if (modelListeners.contains(listener))
				modelListeners.remove(listener);
		}
	}
	
	public void addPackagesBuildListener (IPackagesBuildListener listener)
	{
		buildListeners.add(listener);
	}
	
	public void removePackagesBuildListener (IPackagesBuildListener listener)
	{
		if (buildListeners.contains(listener))
			buildListeners.remove(listener);
	}
	
	public List getBuildListeners ()
	{
		return buildListeners;
	}
	
	protected void clearModel (IProject project)
	{
		List packages = getProjectPackages(project);
		for (Iterator iter = packages.iterator(); iter.hasNext(); )
		{
			PackageImpl pkg = (PackageImpl) iter.next();
			unregisterPackage(pkg);
		}
	}
	
	public void saveModel (IProject project, IProgressMonitor monitor)
	{
		try {
			if (monitor == null)
				monitor = new NullProgressMonitor();
			
			ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
			OutputStreamWriter writer = new OutputStreamWriter(bytesOut);
			XMLBinding.marshal(PackagesModel.instance().getXbPackages(project), writer, monitor);
			writer.close();
			
			ByteArrayInputStream bytesIn = new ByteArrayInputStream(bytesOut.toByteArray());
			IFile packagesFile = project.getFile(PackagesModel.PROJECT_PACKAGES_FILE);
			if (!packagesFile.exists())
				packagesFile.create(bytesIn, true, monitor);
			else
				packagesFile.setContents(bytesIn, true, true, monitor);
			
			bytesIn.close();
			bytesOut.close();
			
			if (!project.hasNature(PackagesNature.NATURE_ID)) {
				ProjectUtil.addProjectNature(project, PackagesNature.NATURE_ID);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected PackageNodeImpl createPackageNodeImpl (IProject project, XbPackageNode node, IPackageNode parent)
	{
		PackageNodeImpl nodeImpl = null;
		
		if (node instanceof XbPackage)
		{
			XbPackage xbPackage = (XbPackage)node;
			
			if (xbPackage.getRef() != null)
			{
				storePackageReference(xbPackage, parent);
			}
			else {
				PackageImpl packageImpl = new PackageImpl(project, xbPackage);
				if (node.getParent() == null || node.getParent() instanceof XbPackages)
				{
					packageImpl.setParentShouldBeNull(true);
				}

				nodeImpl = packageImpl;
			}
		}
		else if (node instanceof XbFolder)
		{
			nodeImpl = new PackageFolderImpl(project, (XbFolder)node);
		}
		else if (node instanceof XbFileSet)
		{
			nodeImpl = new PackageFileSetImpl(project, (XbFileSet)node);
		}
		
		for (Iterator iter = node.getAllChildren().iterator(); iter.hasNext(); )
		{
			XbPackageNode child = (XbPackageNode) iter.next();
			
			PackageNodeImpl childImpl = createPackageNodeImpl(project, child, nodeImpl);
			
			if (nodeImpl != null && childImpl != null)
			{
				nodeImpl.addChildImpl(childImpl);
			}
		}
		
		if (nodeImpl != null && nodeImpl.getNodeType() == IPackageNode.TYPE_PACKAGE)
		{
			registerPackage((PackageImpl)nodeImpl);
		}
		
		return nodeImpl;
	}

	private Hashtable packageRefs;
	
	protected void storePackageReference (XbPackage xbPackage, IPackageNode parent)
	{
		ArrayList references = null;
		if (packageRefs.containsKey(xbPackage))
		{
			references = (ArrayList) packageRefs.get(xbPackage);
		}
		else {
			references = new ArrayList();
			packageRefs.put(xbPackage, references);
		}
		
		references.add(parent);
	}
	
	protected void linkPackageReferences (IProject project)
	{
		for (Iterator iter = packageRefs.keySet().iterator(); iter.hasNext(); )
		{
			XbPackage xbPackage = (XbPackage) iter.next();
			String ref = xbPackage.getRef();
			PackageReferenceImpl.RefAttributes attrs = PackageReferenceImpl.getRefAttributes(ref);
			
			IPackageReference pkgRef = null;
			if (attrs.locationType == PackageReferenceImpl.RefAttributes.WORKSPACE)
			{
				IFile packageFile = ResourcesPlugin.getWorkspace().getRoot().getFile(attrs.packagePath);
				IPackage pkg = PackagesCore.getPackage(packageFile);
				
				if (pkg != null)
				{
					pkgRef = new PackageReferenceImpl(pkg, xbPackage);
				}
			}
			else if (attrs.locationType == PackageReferenceImpl.RefAttributes.FILESYSTEM)
			{
				IPackage pkg = PackagesCore.getPackageFromFilesystem(project, attrs.packagePath);
				
				if (pkg != null)
				{
					pkgRef = new PackageReferenceImpl(pkg, xbPackage);
				}
			}
			
			ArrayList references = (ArrayList) packageRefs.get(xbPackage);
			for (Iterator refIter = references.iterator(); refIter.hasNext(); )
			{
				PackageNodeImpl parent = (PackageNodeImpl) refIter.next();
				if (pkgRef != null)
				{
					parent.addChild(pkgRef);
					refIter.remove();
				}
			}
			
			if (references.isEmpty())
			{
				iter.remove();
			}
		}
	}
	
	protected void fireProjectRegistered (final IProject project)
	{
		synchronized (modelListeners)
		{
			for (Iterator iter = modelListeners.iterator(); iter.hasNext(); )
			{
				IPackagesModelListener listener = (IPackagesModelListener) iter.next();
				listener.projectRegistered(project);
			}
		}
	}
	
	protected void fireNodeAdded (final IPackageNode added)
	{
		synchronized (modelListeners)
		{
			// need to make sure if this is a package or folder node that we don't fire the "added" event prematurely
			// basically we need to check if the node has been properly added to it's parent or not.
			// since a package can be top level (i.e. no parent), i've added a special "shouldParentBeNull" flag (internal)
			// to see if we can trigger the "added" event or not for packages. folders are only children of each other and packages
			if (added.getNodeType() == IPackageNode.TYPE_PACKAGE)
			{
				PackageImpl pkg = (PackageImpl) added;
				if (!pkg.shouldParentBeNull() && pkg.isTopLevel())
				{
					return;
				}
			}
			else if (added.getNodeType() == IPackageNode.TYPE_PACKAGE_FOLDER)
			{
				PackageFolderImpl folder = (PackageFolderImpl) added;
				if (folder.getParent() == null)
				{
					return;
				}
			}
			
			fireEvent(added, new Runnable() {
				public void run() {
					for (Iterator iter = modelListeners.iterator(); iter.hasNext(); )
					{
						IPackagesModelListener listener = (IPackagesModelListener) iter.next();
						listener.packageNodeAdded(added);
					}
				}
			});
		}
	}
	
	protected void fireNodeRemoved (final IPackageNode removed) {
		synchronized (modelListeners)
		{
			fireEvent(removed, new Runnable() {
				public void run() {
					for (Iterator iter = modelListeners.iterator(); iter.hasNext(); )
					{
						IPackagesModelListener listener = (IPackagesModelListener) iter.next();
						listener.packageNodeRemoved(removed);
					}
				}
			});
		}
	}
	
	protected void fireNodeChanged (final IPackageNode changed)
	{
		synchronized (modelListeners)
		{
			fireEvent(changed, new Runnable() {
				public void run() {
					for (Iterator iter = modelListeners.iterator(); iter.hasNext(); )
					{
						IPackagesModelListener listener = (IPackagesModelListener) iter.next();
						listener.packageNodeChanged(changed);
					}
				}
			});
		}
	}
	
	protected void fireNodeAttached (final IPackageNode attached)
	{
		synchronized (modelListeners)
		{
			fireEvent(attached, new Runnable() {
				public void run ()
				{
					for (Iterator iter = modelListeners.iterator(); iter.hasNext(); )
					{
						IPackagesModelListener listener = (IPackagesModelListener) iter.next();
						listener.packageNodeAttached(attached);
					}
				}
			});
		}
	}
	
	protected void fireEvent (IPackageNode source, Runnable runnable) {
		if (projectBeingRegistered != null && source.getProject().equals(projectBeingRegistered))
			return;
	
		runnable.run();
	}
	
	protected void registerPackage (PackageImpl pkg)
	{
		if (pkg.isTopLevel() && pkg.shouldParentBeNull()) {
			if (!projectPackages.containsKey(pkg.getProject()))
			{
				projectPackages.put(pkg.getProject(), new ArrayList());
			}
			getProjectPackages(pkg.getProject()).add(pkg);
		}
		
		if (pkg.isTopLevel() && pkg.shouldParentBeNull())
		{
			XbPackages packages = getXbPackages(pkg.getProject());
			if (packages == null) {
				packages = new XbPackages();
				xbPackages.put(pkg.getProject(), packages);
			}
			
			if (packages.getChildren(XbPackage.class) == null || !packages.getChildren(XbPackage.class).contains(pkg.getNodeDelegate()))
			{	
				packages.addChild(pkg.getNodeDelegate());
			}
		}
		
		
	}
	
	protected void unregisterPackage (PackageImpl pkg)
	{
		if (pkg.isTopLevel()) {
			if (projectPackages.containsKey(pkg.getProject()))
			{
				List pkgs = getProjectPackages(pkg.getProject());
				if (pkgs.contains(pkg))
				{
					pkgs.remove(pkg);
				}
			}
		}
	}
	
	public IPackage createDetachedPackage (IProject project, boolean isTopLevel)
	{
		IPackage pkg = createPackage(project, isTopLevel);
		PackageImpl pkgImpl = (PackageImpl) pkg;
		
		pkgImpl.setDetached(true);
		return pkgImpl;
	}

	public IPackageFolder createDetachedFolder (IProject project)
	{
		IPackageFolder folder = createPackageFolder(project);
		PackageFolderImpl folderImpl = (PackageFolderImpl) folder;
		
		folderImpl.setDetached(true);
		return folderImpl;
	}

	public IPackageFileSet createDetachedFileSet (IProject project)
	{
		IPackageFileSet fileset = createPackageFileSet(project);
		PackageFileSetImpl filesetImpl = (PackageFileSetImpl) fileset;
		
		filesetImpl.setDetached(true);
		return filesetImpl;
	}

	public void attach (IPackageNode nodeToAttach, IProgressMonitor monitor)
	{
		PackageNodeImpl node = (PackageNodeImpl) nodeToAttach;
		
		if (node.isDetached() && !node.areAnyParentsDetached())
		{
			node.setDetached(false);
			
			if (node.getNodeType() == IPackageNode.TYPE_PACKAGE)
			{
				registerPackage((PackageImpl) node);
			}

			fireNodeAttached(node);
			saveModel(node.getProject(), monitor);
		}
	}
}
