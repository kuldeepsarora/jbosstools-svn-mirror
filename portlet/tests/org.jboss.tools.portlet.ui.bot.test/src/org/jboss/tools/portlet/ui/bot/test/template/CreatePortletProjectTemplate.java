package org.jboss.tools.portlet.ui.bot.test.template;

import static org.jboss.tools.portlet.ui.bot.matcher.factory.DefaultMatchersFactory.isNumberOfErrors;
import static org.jboss.tools.portlet.ui.bot.matcher.factory.WorkspaceMatchersFactory.exist;
import static org.jboss.tools.portlet.ui.bot.matcher.factory.WorkspaceMatchersFactory.hasFacets;
import static org.jboss.tools.portlet.ui.bot.matcher.factory.WorkspaceMatchersFactory.isExistingProject;

import java.util.ArrayList;
import java.util.List;

import org.jboss.tools.portlet.ui.bot.entity.FacetDefinition;
import org.jboss.tools.portlet.ui.bot.entity.WorkspaceFile;
import org.jboss.tools.portlet.ui.bot.task.AbstractSWTTask;
import org.jboss.tools.portlet.ui.bot.task.facet.FacetsSelectionTask;
import org.jboss.tools.portlet.ui.bot.task.wizard.WizardPageFillingTask;
import org.jboss.tools.portlet.ui.bot.task.wizard.web.DynamicWebProjectCreationTask;
import org.jboss.tools.portlet.ui.bot.test.testcase.SWTTaskBasedTestCase;
import org.jboss.tools.ui.bot.ext.SWTTestExt;
import org.jboss.tools.ui.bot.ext.config.Annotations.Require;
import org.jboss.tools.ui.bot.ext.config.Annotations.Server;
import org.jboss.tools.ui.bot.ext.config.Annotations.ServerState;
import org.jboss.tools.ui.bot.ext.config.Annotations.ServerType;
import org.junit.Test;

/**
 * Template test that creates a new dynamic web project with facets specified by 
 * concrete implementaions. 
 * 
 * @author Lucia Jelinkova
 *
 */
@Require(server=@Server(required=true, state=ServerState.Present, type=ServerType.EPP))
public abstract class CreatePortletProjectTemplate extends SWTTaskBasedTestCase {

	protected static final String JBOSS_FACET_CATEGORY = "JBoss Portlets";
	
	protected static final FacetDefinition CORE_PORTLET_FACET = new FacetDefinition("JBoss Core Portlet", JBOSS_FACET_CATEGORY);
	
	protected static final FacetDefinition JAVA_FACET = new FacetDefinition("Java", null, "1.6");
	
	protected static final String WEB_XML = "WebContent/WEB-INF/web.xml";
	
	protected static final String PORTLET_XML = "WebContent/WEB-INF/portlet.xml";
	
	protected static final String PORTLET_LIBRARIES = "JBoss Portlet Libraries";
	
	public abstract String getProjectName();
	
	public abstract List<FacetDefinition> getRequiredFacets();
	
	public abstract List<WizardPageFillingTask> getAdditionalWizardPages();
	
	public abstract List<String> getExpectedFiles();
	
	@Test
	public void testcreate(){
		doPerform(getCreateDynamicWebProjectTask());

		doAssertThat(0, isNumberOfErrors());
		doAssertThat(getProjectName(), isExistingProject());
		doAssertThat(getProjectName(), hasFacets(getRequiredFacets()));		
		doAssertThat(getExpectedWorkspaceFiles(), exist());
	}
	
	protected AbstractSWTTask getCreateDynamicWebProjectTask() {
		DynamicWebProjectCreationTask task = new DynamicWebProjectCreationTask();
		task.setProjectName(getProjectName());
		task.setWebModuleVersion("2.5");
		task.setServerName(SWTTestExt.configuredState.getServer().name);
		task.setSelectFacetsTask(getSelectFacetsTask());
		task.addAllWizardPages(getAdditionalWizardPages());
		return task;
	}
	
	protected FacetsSelectionTask getSelectFacetsTask() {
		FacetsSelectionTask task = new FacetsSelectionTask();
		task.addAllFacets(getRequiredFacets());
		return task;
	}
	
	private List<WorkspaceFile> getExpectedWorkspaceFiles(){
		List<WorkspaceFile> expectedWorkspaceFiles = new ArrayList<WorkspaceFile>();
		
		for (String file : getExpectedFiles()){
			expectedWorkspaceFiles.add(new WorkspaceFile(getProjectName(), file));
		}
		
		return expectedWorkspaceFiles;
	}
}
