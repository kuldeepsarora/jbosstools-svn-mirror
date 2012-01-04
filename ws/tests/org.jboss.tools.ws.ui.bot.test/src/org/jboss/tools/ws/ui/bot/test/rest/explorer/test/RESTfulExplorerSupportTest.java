/*******************************************************************************
 * Copyright (c) 2007-2011 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributor:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/

package org.jboss.tools.ws.ui.bot.test.rest.explorer.test;

import org.jboss.tools.ws.ui.bot.test.rest.explorer.RESTfulTestBase;
import org.junit.Before;
import org.junit.Test;

/**
 * Test checks if context menu 'Add RESTful 1.1 Support' works properly
 * @author jjankovi
 *
 */
public class RESTfulExplorerSupportTest extends RESTfulTestBase {
	
	protected String getWsProjectName() {
		return "RestExplorerTest";
	}
	
	protected String getWsPackage() {
		return "org.rest.explorer.validation.test";
	}

	protected String getWsName() {
		return "RestService";
	}
	
	@Before
	public void setup() {		
		if (!projectExists(getWsProjectName())) {
			projectHelper.createProject(getWsProjectName());
		}
	}
	
	
	@Test
	public void testSupportJAX_RS1_1_Explorer() {
		
		addRestSupport(getWsProjectName());
		assertTrue(isRestSupportEnabled(getWsProjectName()));
		
	}

}
