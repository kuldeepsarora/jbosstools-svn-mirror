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
package org.jboss.tools.hibernate.ui.bot.testsuite;

import org.jboss.tools.hibernate.ui.bot.testcase.CodeGenerationLauncherTest;
import org.jboss.tools.hibernate.ui.bot.testcase.ConfigurationContextTest;
import org.jboss.tools.hibernate.ui.bot.testcase.ConfigurationFileTest;
import org.jboss.tools.hibernate.ui.bot.testcase.ConsolePerspectiveTest;
import org.jboss.tools.hibernate.ui.bot.testcase.ConsoleTest;
import org.jboss.tools.hibernate.ui.bot.testcase.CriteriaEditorsTest;
import org.jboss.tools.hibernate.ui.bot.testcase.DaliTest;
import org.jboss.tools.hibernate.ui.bot.testcase.JIRATest;
import org.jboss.tools.hibernate.ui.bot.testcase.MappingFileTest;
import org.jboss.tools.hibernate.ui.bot.testcase.MappingsDiagramTest;
import org.jboss.tools.hibernate.ui.bot.testcase.ReverseEngineerFileTest;
import org.jboss.tools.hibernate.ui.bot.testcase.ViewsTest;
import org.jboss.tools.ui.bot.ext.RequirementAwareSuite;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(RequirementAwareSuite.class)
@SuiteClasses({ CodeGenerationLauncherTest.class,
		ConfigurationContextTest.class, ConfigurationFileTest.class,
		ConsolePerspectiveTest.class, ConsoleTest.class,
		CriteriaEditorsTest.class, DaliTest.class, JIRATest.class,
		MappingFileTest.class, MappingsDiagramTest.class,
		ReverseEngineerFileTest.class, ViewsTest.class })
		
		
public class HibernateAllTests extends HibernateTest {

	@BeforeClass
	public static void setUpSuite() {
		HibernateTest.prepare();
	}

	@AfterClass
	public static void tearDownSuite() {
		HibernateTest.clean();
	}
}
