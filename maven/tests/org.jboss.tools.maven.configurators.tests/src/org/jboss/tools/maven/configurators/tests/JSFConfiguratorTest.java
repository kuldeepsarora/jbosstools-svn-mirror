package org.jboss.tools.maven.configurators.tests;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.m2e.core.project.ResolverConfiguration;
import org.eclipse.m2e.tests.common.WorkspaceHelpers;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.jboss.tools.maven.jsf.MavenJSFConstants;
import org.jboss.tools.maven.jsf.configurators.JSFProjectConfigurator;
import org.junit.Test;

@SuppressWarnings("restriction")
public class JSFConfiguratorTest extends AbstractMavenConfiguratorTest {

	@Test
	public void testJBIDE9242_supportMultipleJSFDependencies() throws Exception {
		IProject[] projects = importProjects("projects/jsf/", 
											new String[]{ "jsf-mojarra/pom.xml",
														  "jsf-jboss/pom.xml",
														  "jsf-jsfapi/pom.xml",
														  "jsf-myfaces/pom.xml",
														  "jsf-jsfapi-12/pom.xml"}, 
											new ResolverConfiguration());
		waitForJobsToComplete(new NullProgressMonitor());
		IProject mojarra = projects[0];
		assertNoErrors(mojarra);
		assertIsJSFProject(mojarra, JSFProjectConfigurator.JSF_FACET_VERSION_2_0);
		
		IProject jboss = projects[1];
		assertNoErrors(jboss);
		assertIsJSFProject(jboss, JSFProjectConfigurator.JSF_FACET_VERSION_2_0);
		
		IProject jsfapi_20 = projects[2];
		assertNoErrors(jsfapi_20);
		assertIsJSFProject(jsfapi_20, JSFProjectConfigurator.JSF_FACET_VERSION_2_0);
		
		IProject myfaces = projects[3];
		assertNoErrors(myfaces);
		assertIsJSFProject(myfaces, JSFProjectConfigurator.JSF_FACET_VERSION_1_1);

		IProject jsfapi_12 = projects[4];
		assertNoErrors(jsfapi_12);
		assertIsJSFProject(jsfapi_12, JSFProjectConfigurator.JSF_FACET_VERSION_1_2);
		

	}

	@Test
	public void testJBIDE8687_webXml_changed() throws Exception {
		String projectLocation = "projects/jsf/jsf-webxml";
		String webxmlRelPath = "src/main/webapp/WEB-INF/web.xml";
		
		IProject jsfProject = importProject(projectLocation+"/pom.xml");
		waitForJobsToComplete(new NullProgressMonitor());
		assertIsJSFProject(jsfProject, JSFProjectConfigurator.JSF_FACET_VERSION_2_0);

		IFile webXml = jsfProject.getFile(webxmlRelPath);
		assertTrue(webXml.exists());
		File originalWebXml = new File(projectLocation, webxmlRelPath);
		assertEquals("web.xml content changed ", toString(originalWebXml), toString(webXml));
	}	


	@Test
	public void testJBIDE10468_facesServletInWebXml() throws Exception {
		String projectLocation = "projects/jsf/JBIDE-10468/jsf-webxml-20";
		IProject jsfProject = importProject(projectLocation+"/pom.xml");
		waitForJobsToComplete(new NullProgressMonitor());
		assertIsJSFProject(jsfProject, JSFProjectConfigurator.JSF_FACET_VERSION_2_0);

		projectLocation = "projects/jsf/JBIDE-10468/jsf-webxml-12";
		jsfProject = importProject(projectLocation+"/pom.xml");
		waitForJobsToComplete(new NullProgressMonitor());
		assertIsJSFProject(jsfProject, JSFProjectConfigurator.JSF_FACET_VERSION_1_2);
	}	
	
	@Test
	public void testJBIDE10468_detectFacesConfig() throws Exception {
		String projectLocation = "projects/jsf/JBIDE-10468/jsf-facesconfig";
		IProject jsfProject = importProject(projectLocation+"/pom.xml");
		waitForJobsToComplete(new NullProgressMonitor());
		assertIsJSFProject(jsfProject, JSFProjectConfigurator.JSF_FACET_VERSION_2_0);
	}	
	
	
	@Test
	public void testJBIDE9455_errorMarkers() throws Exception {
		String projectLocation = "projects/jsf/jsf-error";
		IProject jsfProject = importProject(projectLocation+"/pom.xml");
		waitForJobsToComplete(new NullProgressMonitor());
		IFacetedProject facetedProject = ProjectFacetsManager.create(jsfProject);
		assertNotNull(jsfProject.getName() + " is not a faceted project", facetedProject);
		assertFalse("JSF Facet should be missing", facetedProject.hasProjectFacet(JSFProjectConfigurator.JSF_FACET));
		assertHasJSFConfigurationError(jsfProject, "JavaServer Faces 2.0 can not be installed : One or more constraints have not been satisfied.");
		assertHasJSFConfigurationError(jsfProject, "JavaServer Faces 2.0 requires Dynamic Web Module 2.5 or newer.");
		
		//Check markers are removed upon configuration update
		copyContent(jsfProject, "src/main/webapp/WEB-INF/good-web.xml", "src/main/webapp/WEB-INF/web.xml", true);
		updateProject(jsfProject);
		assertNoErrors(jsfProject);
		assertIsJSFProject(jsfProject, JSFProjectConfigurator.JSF_FACET_VERSION_2_0);
	}

	@Test
	public void testJBDS1999_noWebXmlCreated() throws Exception {
		IProject project = importProject("projects/jsf/JBDS-1999/pom.xml");
		waitForJobsToComplete();
		
		IFacetedProject facetedProject = ProjectFacetsManager.create(project);
		assertNotNull(project.getName() + " is not a faceted project", facetedProject);
		assertFalse(project.getName() + " should not have the JSF facet", facetedProject.hasProjectFacet(JSFProjectConfigurator.JSF_FACET));
		assertTrue(project.getName() + " doesn't have the expected Web facet", facetedProject.hasProjectFacet(IJ2EEFacetConstants.DYNAMIC_WEB_25));
		
		IFile webXml = project.getFile("src/main/webapp/WEB-INF/web.xml");
		assertFalse("web.xml was added to the project!", webXml.exists());
	}	
	
	private void assertHasJSFConfigurationError(IProject project, String message) throws Exception {
		WorkspaceHelpers.assertErrorMarker(MavenJSFConstants.JSF_CONFIGURATION_ERROR_MARKER_ID, message, 1, "", project);
	}
}
