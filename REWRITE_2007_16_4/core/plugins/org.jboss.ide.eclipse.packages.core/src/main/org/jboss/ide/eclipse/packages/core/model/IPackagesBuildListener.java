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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;

/**
 * This interface is inteded to be implemented by classes who are interested in receiving callbacks for various IPackage build events
 * 
 * @author Marshall
 */
public interface IPackagesBuildListener {

	/**
	 * A project has started being built by the packages builder
	 * @param project the project being built
	 */
	public void startedBuild (IProject project);
	
	/**
	 * A package has started being built by the packages builder
	 * @param pkg the package being built
	 */
	public void startedBuildingPackage (IPackage pkg);
	
	/**
	 * A fileset has started being collected for copying into a package
	 * @param fileset the fileset being collected
	 */
	public void startedCollectingFileSet (IPackageFileSet fileset);
	
	/**
	 * The build for the given project has failed
	 * @param pkg The package that failed to build
	 * @param status The status/exception that occurred
	 */
	public void buildFailed (IPackage pkg, IStatus status);
	
	/**
	 * A fileset has finished being collected for copying into a package
	 * @param fileset the fileset being collected
	 */
	public void finishedCollectingFileSet (IPackageFileSet fileset);
	
	/**
	 * A file has been updated, with the given IPackage / IPackageFileSet context
	 * @param topLevelPackage The top level package that was updated
	 * @param fileset The fileset that matched the updated file
	 * @param filePath The path to the file that was copied (filesystem/workspace path)
	 */
	public void fileUpdated (IPackage topLevelPackage, IPackageFileSet fileset, IPath filePath);
	
	/**
	 * A file has been removed, with the given IPackage / IPackageFileSet context
	 * @param topLevelPackage The top level package that was updated
	 * @param fileset The fileset that matched the removed file
	 * @param filePath The path to the file that was removed (filesystem/workspace path)
	 */
	public void fileRemoved (IPackage topLevelPackage, IPackageFileSet fileset, IPath filePath);
	
	/**
	 * A top level package has been changed to either exploded or compressed (see exploded parameter)
	 * @param topLevelPackage The top level package that was changed
	 * @param isExploded Whether or not this package is now exploded
	 */
	public void packageBuildTypeChanged (IPackage topLevelPackage, boolean isExploded);
	
	/**
	 * A project is finished being built by the packages builder
	 * @param project the project being built
	 */
	public void finishedBuild (IProject project);
	
	/**
	 * A package is finished being built by the packages builder
	 * @param pkg the package being built
	 */
	public void finishedBuildingPackage (IPackage pkg);
}
