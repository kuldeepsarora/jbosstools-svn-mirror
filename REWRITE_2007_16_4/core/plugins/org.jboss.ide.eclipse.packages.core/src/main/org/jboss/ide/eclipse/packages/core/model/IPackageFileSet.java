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
package org.jboss.ide.eclipse.packages.core.model;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;

/**
 * <p>
 * This interface represents a file set inside of a package definition or folder.
 * </p>
 * 
 * @author <a href="marshall@jboss.org">Marshall Culpepper</a>
 * @version $Revision$
 */
public interface IPackageFileSet extends IPackageNode {

	/**
	 * @return Whether or not this fileset represents a single file
	 */
	public boolean isSingleFile();
	
	/**
	 * @return Whether or not this fileset's basedir is inside the workspace
	 */
	public boolean isInWorkspace();
	
	/**
	 * If this fileset represents a single file, and that file is in the workspace, this will return a reference to that file.
	 * Otherwise this will return null.
	 */
	public IFile getFile();
	
	/**
	 * If this fileset represents a single file, this will return the path to that file.
	 * Otherwise this will return null.
	 */
	public IPath getFilePath();
	
	/**
	 * If this fileset represents a single file, this will return the destination filename of the file within this package
	 */
	public String getDestinationFilename();
	
	/**
	 * This is a convenience method that will cast getSourceContainer() to an IProject if the source/basedir for this fileset represents a project
	 */
	public IProject getSourceProject();
	
	/**
	 * If this fileset is based inside the workspace, this will return the workspace container at the root / base of this fileset.
	 * @return The source container ("basedir" in ant terminology) for this fileset
	 */
	public IContainer getSourceContainer();
	
	/**
	 * @return The path to the source folder ("basedir" in ant terminology) for this fileset. Note that this path can be
	 * filesystem-based or workspace-based.
	 */
	public IPath getSourcePath();
	
	/**
	 * @return The includes pattern for this fileset
	 */
	public String getIncludesPattern();
	
	/**
	 * @return The excludes pattern for this fileset
	 */
	public String getExcludesPattern();
	
	/**
	 * @return An array of matching IFile's in the workspace (for workspace-rooted filesets)
	 */
	public IFile[] findMatchingFiles();
	
	/**
	 * @return An array of matching IPath's in the filesystem (for external filesystem filesets)
	 */
	public IPath[] findMatchingPaths();
	
	/**
	 * @param file The file to check
	 * @return Whether or not this fileset matches the passed-in file
	 */
	public boolean matchesFile(IFile file);
	
	/**
	 * @param path The absolute path on the filesystem to check
	 * @return Whether or not this fileset matches the passed-in path
	 */
	public boolean matchesPath(IPath path);
	
	/**
	 * Set this fileset to be a single file in the workspace
	 * Equivalent to:
	 * <code>setSingleFile(file, null);</code>
	 * 
	 * @param file the single file for this fileset
	 */
	public void setSingleFile(IFile file);
	
	/**
	 * Set this fileset to be a single file in the workspace, using the specified destination filename
	 * @param file The single file to be included in this fileset
	 * @param destinationFilename The filename that the file will be called in the package
	 */
	public void setSingleFile(IFile file, String destinationFilename);
	
	/**
	 * Set this fileset to be a single file represented by the passed-in IPath.
	 * Equivalent to:
	 * <code>setSingleFile(path, null);</code>
	 * @param path Either an absolute path to a file on the filesystem, or a workspace-relative path. 
	 */
	public void setSingleFile(IPath path);
	
	/**
	 * Set this fileset to be a single file, using the specified destination filename
	 * @param path Either an absolute path to a file on the filesystem, or a workspace-relative path.
	 * @param destinationFilename The filename that the file will be called in the package
	 */
	public void setSingleFile(IPath path, String destinationFilename);
	
	/**
	 * Sets the "root" or "source" of this fileset to be the passed-in project
	 * @param project The project that is the source of this fileset
	 */
	public void setSourceProject(IProject project);
	
	/**
	 * Sets the "root" or "source" of this fileset to the be the passed-in container
	 * @param container The container that is the source of this fileset
	 */
	public void setSourceContainer(IContainer container);
	
	/**
	 * Sets the "root" or "source" of this fileset to be an absolute path.
	 * @param path The absolute path that is the source of this fileset
	 */
	public void setSourcePath(IPath path);
	
	/**
	 * Set the includes pattern for this fileset. This pattern uses the same syntax as Ant's include pattern.
	 * @param includes The includes pattern for this fileset
	 */
	public void setIncludesPattern(String includes);
	
	/**
	 * Set the excludes pattern for this fileset. This pattern uses the same syntax as Ant's exclude pattern.
	 * @param excludes The excludes pattern for this fileset
	 */
	public void setExcludesPattern(String excludes);
	
	/**
	 * Set whether or not this fileset's source is in the workspace. This will automatically be handled if you
	 * use setSingleFile, setSourceProject, setSourceContainer, or setSourceFolder.
	 * @param isInWorkspace Whether or not this fileset's source is in the workspace
	 */
	public void setInWorkspace(boolean isInWorkspace);
}
