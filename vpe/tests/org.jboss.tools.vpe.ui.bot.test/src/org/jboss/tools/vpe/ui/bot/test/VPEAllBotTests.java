package org.jboss.tools.vpe.ui.bot.test;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.eclipse.swtbot.swt.finder.SWTBotTestCase;
import org.jboss.tools.vpe.ui.bot.test.editor.BlockCommentTest;
import org.jboss.tools.vpe.ui.bot.test.editor.ToggleCommentTest;
import org.jboss.tools.vpe.ui.bot.test.editor.VerificationOfNameSpacesTest;
import org.jboss.tools.vpe.ui.bot.test.editor.JspFileEditingTest;
import org.jboss.tools.vpe.ui.bot.test.editor.VisualEditorContextMenuTest;
import org.jboss.tools.vpe.ui.bot.test.editor.XhtmlFilePerformanceTest;
import org.jboss.tools.vpe.ui.bot.test.editor.pagedesign.AddSubstitutedELExpressionFolderScopeTest;
import org.jboss.tools.vpe.ui.bot.test.editor.pagedesign.ToolbarTextFormattingTest;
import org.jboss.tools.vpe.ui.bot.test.editor.preferences.AlwaysHideSelectionBarWithoutPromptTest;
import org.jboss.tools.vpe.ui.bot.test.editor.preferences.BorderForUnknownTagsTest;
import org.jboss.tools.vpe.ui.bot.test.editor.preferences.ChangeEditorTabForTheFirstOpenPageTest;
import org.jboss.tools.vpe.ui.bot.test.editor.preferences.ShowNonVisualTagsTest;
import org.jboss.tools.vpe.ui.bot.test.editor.preferences.ShowResourceBundlesUsageasELexpressionsTest;
import org.jboss.tools.vpe.ui.bot.test.editor.preferences.ShowSelectionTagBarTest;
import org.jboss.tools.vpe.ui.bot.test.jbide.JBIDE4556Test;
import org.jboss.tools.vpe.ui.bot.test.palette.CancelTagLibDefenitionTest;
import org.jboss.tools.vpe.ui.bot.test.palette.ImportTagsFromTLDFileTest;
import org.jboss.tools.vpe.ui.bot.test.palette.ManagePaletteGroupsTest;
import org.jboss.tools.vpe.ui.bot.test.palette.PaletteEditorTest;
import org.jboss.tools.vpe.ui.bot.test.smoke.EditorSynchronizationTest;
import org.jboss.tools.vpe.ui.bot.test.smoke.JSPPageCreationTest;
import org.jboss.tools.vpe.ui.bot.test.smoke.RenameFacesConfigFileTest;
import org.jboss.tools.vpe.ui.bot.test.smoke.RenameJSPFileTest;
import org.jboss.tools.vpe.ui.bot.test.smoke.RenameXHTMLFileTest;
import org.jboss.tools.vpe.ui.bot.test.smoke.XHTMLPageCreationTest;
import org.jboss.tools.vpe.ui.bot.test.wizard.ExternalizeStringsDialogTest;
import org.jboss.tools.vpe.ui.bot.test.wizard.ImportUnknownTagsWizardTest;
import org.jboss.tools.vpe.ui.bot.test.wizard.NewXHTMLPageWizardTest;
import org.jboss.tools.vpe.ui.bot.test.wizard.VPESourceCodeTemplatesPreferencePageTest;

public class VPEAllBotTests extends SWTBotTestCase{
	public static Test suite(){
		TestSuite suite = new TestSuite("VPE All Tests"); //$NON-NLS-1$
    suite.addTestSuite(NewXHTMLPageWizardTest.class); 
		suite.addTestSuite(CancelTagLibDefenitionTest.class);
		suite.addTestSuite(ImportTagsFromTLDFileTest.class);
		suite.addTestSuite(ToggleCommentTest.class);
		suite.addTestSuite(BlockCommentTest.class);
		suite.addTestSuite(ChangeEditorTabForTheFirstOpenPageTest.class);
		suite.addTestSuite(JBIDE4556Test.class);
		suite.addTestSuite(VerificationOfNameSpacesTest.class);
		suite.addTestSuite(BorderForUnknownTagsTest.class);
		suite.addTestSuite(ShowResourceBundlesUsageasELexpressionsTest.class);
		suite.addTestSuite(ShowSelectionTagBarTest.class);
		suite.addTestSuite(AlwaysHideSelectionBarWithoutPromptTest.class);
		suite.addTestSuite(ShowNonVisualTagsTest.class);
		suite.addTestSuite(AddSubstitutedELExpressionFolderScopeTest.class);
		suite.addTestSuite(EditorSynchronizationTest.class);
		suite.addTestSuite(JSPPageCreationTest.class);
		suite.addTestSuite(XHTMLPageCreationTest.class);
		suite.addTestSuite(RenameFacesConfigFileTest.class);
		suite.addTestSuite(RenameJSPFileTest.class);
		suite.addTestSuite(RenameXHTMLFileTest.class);
		suite.addTestSuite(ImportUnknownTagsWizardTest.class);
		suite.addTestSuite(VPESourceCodeTemplatesPreferencePageTest.class);
		suite.addTestSuite(ExternalizeStringsDialogTest.class);
		suite.addTestSuite(JspFileEditingTest.class);
		suite.addTestSuite(XhtmlFilePerformanceTest.class);
		suite.addTestSuite(ManagePaletteGroupsTest.class);
		suite.addTestSuite(PaletteEditorTest.class);
		suite.addTestSuite(ToolbarTextFormattingTest.class);
		suite.addTestSuite(VisualEditorContextMenuTest.class);
		
		return new TestSetup(suite);
	}
}
