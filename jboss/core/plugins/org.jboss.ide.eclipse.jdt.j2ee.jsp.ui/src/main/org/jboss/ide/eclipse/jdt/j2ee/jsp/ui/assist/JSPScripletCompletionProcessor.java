/*
 * JBoss-IDE, Eclipse plugins for JBoss
 *
 * Distributable under LGPL license.
 * See terms of license at www.gnu.org.
 */
package org.jboss.ide.eclipse.jdt.j2ee.jsp.ui.assist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.jasper.JasperException;
import org.apache.jasper.compiler.Node;
import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.codeassist.CompletionEngine;
import org.eclipse.jdt.internal.core.DefaultWorkingCopyOwner;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jdt.internal.core.SearchableEnvironment;
import org.eclipse.jdt.internal.corext.template.java.JavaContextType;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.JavaUIMessages;
import org.eclipse.jdt.internal.ui.text.JavaCodeReader;
import org.eclipse.jdt.internal.ui.text.java.ExperimentalResultCollector;
import org.eclipse.jdt.internal.ui.text.java.JavaCompletionProposal;
import org.eclipse.jdt.internal.ui.text.java.JavaCompletionProposalComparator;
import org.eclipse.jdt.internal.ui.text.java.JavaParameterListValidator;
import org.eclipse.jdt.internal.ui.text.java.ResultCollector;
import org.eclipse.jdt.internal.ui.text.template.contentassist.TemplateEngine;
import org.eclipse.jdt.ui.IWorkingCopyManager;
import org.eclipse.jdt.ui.PreferenceConstants;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationExtension;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.jboss.ide.eclipse.jdt.j2ee.jsp.core.compiler.jasper.JSPElementInfo;
import org.jboss.ide.eclipse.jdt.j2ee.jsp.core.compiler.jasper.JSPNodeByJSPLineLocator;
import org.jboss.ide.eclipse.jdt.j2ee.jsp.core.compiler.jasper.JSPProject;
import org.jboss.ide.eclipse.jdt.j2ee.jsp.core.compiler.jasper.JSPProjectManager;

/**
 * Description of the Class
 *
 * @author    Laurent Etiemble
 * @version   $Revision$
 */
public class JSPScripletCompletionProcessor implements IContentAssistProcessor
{
   /** Description of the Field */
   protected IWorkingCopyManager fManager;

   private ResultCollector collector;
   private JavaCompletionProposalComparator comparator;
   private ExperimentalResultCollector experimentalCollector;

   private IFile file;
   private JSPProject jspProject;
   private int numberOfComputedResults = 0;
   private char[] proposalAutoActivationSet;
   private TemplateEngine templateEngine;
   private IContextInformationValidator validator;
   private final static String DISABLED = "disabled";//$NON-NLS-1$
   private final static String ENABLED = "enabled";//$NON-NLS-1$
   private final static String VISIBILITY = JavaCore.CODEASSIST_VISIBILITY_CHECK;


   /**
    *Constructor for the JSPScripletCompletionProcessor object
    *
    * @param file  Description of the Parameter
    */
   public JSPScripletCompletionProcessor(IFile file)
   {
      this.file = file;
      this.collector = new JSPResultCollector();
      this.jspProject = JSPProjectManager.getJSPProject(file.getProject());
      this.fManager = JavaPlugin.getDefault().getWorkingCopyManager();

      TemplateContextType contextType = JavaPlugin.getDefault().getTemplateContextRegistry().getContextType("java");//$NON-NLS-1$
      if (contextType == null)
      {
         contextType = new JavaContextType();
         JavaPlugin.getDefault().getTemplateContextRegistry().addContextType(contextType);
      }
      if (contextType != null)
      {
         this.templateEngine = new TemplateEngine(contextType);
      }
      this.experimentalCollector = new ExperimentalResultCollector();
      this.comparator = new JavaCompletionProposalComparator();
   }


   /**
    * @param viewer  Description of the Parameter
    * @param offset  Description of the Parameter
    * @return        Description of the Return Value
    * @see           IContentAssistProcessor#computeCompletionProposals(ITextViewer, int)
    */
   public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset)
   {
      int contextInformationPosition = this.guessContextInformationPosition(viewer, offset);
      return this.internalComputeCompletionProposals(viewer, offset, contextInformationPosition);
   }


   /**
    * @param viewer  Description of the Parameter
    * @param offset  Description of the Parameter
    * @return        Description of the Return Value
    * @see           IContentAssistProcessor#computeContextInformation(ITextViewer, int)
    */
   public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset)
   {
      int contextInformationPosition = this.guessContextInformationPosition(viewer, offset);
      List result = this.addContextInformations(viewer, contextInformationPosition);
      return (IContextInformation[]) result.toArray(new IContextInformation[result.size()]);
   }


   /**
    * @return   The completionProposalAutoActivationCharacters value
    * @see      IContentAssistProcessor#getCompletionProposalAutoActivationCharacters()
    */
   public char[] getCompletionProposalAutoActivationCharacters()
   {
      return new char[]{'.', ' '};
   }


   /**
    * @return   The contextInformationAutoActivationCharacters value
    * @see      IContentAssistProcessor#getContextInformationAutoActivationCharacters()
    */
   public char[] getContextInformationAutoActivationCharacters()
   {
      return null;
   }


   /**
    * @return   The contextInformationValidator value
    * @see      IContentAssistProcessor#getContextInformationValidator()
    */
   public IContextInformationValidator getContextInformationValidator()
   {
      if (validator == null)
      {
         validator = new JavaParameterListValidator();
      }
      return validator;
   }


   /**
    * @return   The errorMessage value
    * @see      IContentAssistProcessor#getErrorMessage()
    */
   public String getErrorMessage()
   {

      if (numberOfComputedResults == 0)
      {
         String errorMsg = collector.getErrorMessage();
         if (errorMsg == null || errorMsg.trim().length() == 0)
         {
            errorMsg = JavaUIMessages.getString("JavaEditor.codeassist.noCompletions");//$NON-NLS-1$
         }
         return errorMsg;
      }

      if (PreferenceConstants.getPreferenceStore().getBoolean(PreferenceConstants.CODEASSIST_FILL_ARGUMENT_NAMES))
      {
         return experimentalCollector.getErrorMessage();
      }

      return collector.getErrorMessage();
   }


   /**
    * Tells this processor to order the proposals alphabetically.
    *
    * @param order  <code>true</code> if proposals should be ordered.
    */
   public void orderProposalsAlphabetically(boolean order)
   {
      comparator.setOrderAlphabetically(order);
   }


   /**
    * Tells this processor to restrict its proposal to those element
    * visible in the actual invocation context.
    *
    * @param restrict  <code>true</code> if proposals should be restricted
    */
   public void restrictProposalsToVisibility(boolean restrict)
   {
      Hashtable options = JavaCore.getOptions();
      Object value = options.get(VISIBILITY);
      if (value instanceof String)
      {
         String newValue = restrict ? ENABLED : DISABLED;
         if (!newValue.equals(value))
         {
            options.put(VISIBILITY, newValue);
            JavaCore.setOptions(options);
         }
      }
   }


   /**
    * Sets this processor's set of characters triggering the activation of the
    * completion proposal computation.
    *
    * @param activationSet  the activation set
    */
   public void setCompletionProposalAutoActivationCharacters(char[] activationSet)
   {
      proposalAutoActivationSet = activationSet;
   }


   /**
    * Adds a feature to the ContextInformations attribute of the JSPScripletCompletionProcessor object
    *
    * @param viewer  The feature to be added to the ContextInformations attribute
    * @param offset  The feature to be added to the ContextInformations attribute
    * @return        Description of the Return Value
    */
   private List addContextInformations(ITextViewer viewer, int offset)
   {
      ICompletionProposal[] proposals = internalComputeCompletionProposals(viewer, offset, -1);

      List result = new ArrayList();
      for (int i = 0; i < proposals.length; i++)
      {
         IContextInformation contextInformation = proposals[i].getContextInformation();
         if (contextInformation != null)
         {
            ContextInformationWrapper wrapper = new ContextInformationWrapper(contextInformation);
            wrapper.setContextInformationPosition(offset);
            result.add(wrapper);
         }
      }
      return result;
   }


   /**
    * Description of the Method
    *
    * @param info                    Description of the Parameter
    * @param javaOffset              Description of the Parameter
    * @exception JavaModelException  Description of the Exception
    */
   private void complete(JSPElementInfo info, int javaOffset)
      throws JavaModelException
   {
      org.eclipse.jdt.internal.compiler.env.ICompilationUnit sourceUnit = info.getCompilationUnit();

      JavaProject jProject = (JavaProject) this.jspProject.getJavaProject();
      SearchableEnvironment searchEnv = jProject.newSearchableNameEnvironment(DefaultWorkingCopyOwner.PRIMARY);

      HashMap map = new HashMap(JavaCore.getOptions());
      
      CompletionEngine engine = new CompletionEngine(searchEnv, this.collector, JavaCore.getOptions(), jProject);
      engine.complete(sourceUnit, javaOffset, 0);
   }


   /**
    * Description of the Method
    *
    * @param viewer  Description of the Parameter
    * @param offset  Description of the Parameter
    * @return        Description of the Return Value
    */
   private int guessContextInformationPosition(ITextViewer viewer, int offset)
   {
      int contextPosition = offset;

      IDocument document = viewer.getDocument();
      try
      {
         JavaCodeReader reader = new JavaCodeReader();
         reader.configureBackwardReader(document, offset, true, true);

         int nestingLevel = 0;

         int curr = reader.read();
         while (curr != JavaCodeReader.EOF)
         {
            if (')' == (char) curr)
            {
               ++nestingLevel;
            }
            else if ('(' == (char) curr)
            {
               --nestingLevel;

               if (nestingLevel < 0)
               {
                  int start = reader.getOffset();
                  if (this.looksLikeMethod(reader))
                  {
                     return start + 1;
                  }
               }
            }
            curr = reader.read();
         }
      }
      catch (IOException e)
      {
         // Do nothing
      }
      return contextPosition;
   }


   /**
    * Description of the Method
    *
    * @param viewer         Description of the Parameter
    * @param offset         Description of the Parameter
    * @param contextOffset  Description of the Parameter
    * @return               Description of the Return Value
    */
   private ICompletionProposal[] internalComputeCompletionProposals(ITextViewer viewer, int offset, int contextOffset)
   {
      JSPElementInfo info = this.jspProject.compileJSP(this.file);
      ICompletionProposal[] results;

      try
      {
         if (info != null)
         {
            IDocument document = viewer.getDocument();
            int line = document.getLineOfOffset(offset);
            int lineOffset = document.getLineOffset(line);
            int lineOff = offset - lineOffset;

            // Translate in JSP coordinate system
            line++;
            lineOff++;
            //System.out.println("Offset at " + line + ":" + lineOff);

            JSPNodeByJSPLineLocator visitor = new JSPNodeByJSPLineLocator(line, lineOff);
            info.getNodes().visit(visitor);
            Node srcNode = visitor.getNode();
            if (srcNode != null)
            {
               //System.out.println("Found " + srcNode.getStart().getLineNumber() + ":" + srcNode.getStart().getColumnNumber());

               int offsetLineNumber = visitor.getLineOffset();
               int offsetColNumber = lineOff;
               if (offsetLineNumber == 0)
               {
                  offsetColNumber = visitor.getColumnOffset();
               }

               // Translate back in Document coordinate system
               int javaLine = srcNode.getBeginJavaLine() + offsetLineNumber - 1;
               int javaColumn = offsetColNumber - 1;

               IDocument javaDocument = new Document(info.getContent());
               int javaOffset = javaDocument.getLineOffset(javaLine) + javaColumn;

               this.collector.setPreventEating(false);
               this.collector.reset(javaOffset, this.jspProject.getJavaProject(), null);
               this.collector.setViewer(viewer);

               Point selection = viewer.getSelectedRange();
               if (selection.y > 0)
               {
                  this.collector.setReplacementLength(selection.y);
               }

               this.complete(info, javaOffset);
               results = this.collector.getResults();

               // Re-translate the final coordinates
               for (int i = 0; i < results.length; i++)
               {
                  JavaCompletionProposal proposal = (JavaCompletionProposal) results[i];
                  int pOffset = proposal.getReplacementOffset();
                  proposal.setReplacementOffset(pOffset - javaOffset + offset);
               }

               //                    if (templateEngine != null)
               //                    {
               //                        templateEngine.reset();
               //                        templateEngine.complete(viewer, offset, null);
               //
               //                        TemplateProposal[] templateResults = templateEngine.getResults();
               //
               //                        // update relavance of template proposals that match with a keyword
               //                        JavaCompletionProposal[] keyWordResults = collector.getKeywordCompletions();
               //                        for (int i = 0; i < keyWordResults.length; i++)
               //                        {
               //                            String keyword = keyWordResults[i].getReplacementString();
               //                            for (int k = 0; k < templateResults.length; k++)
               //                            {
               //                                TemplateProposal curr = templateResults[k];
               //                                if (keyword.equals(curr.getTemplate().getName()))
               //                                {
               //                                    curr.setRelevance(keyWordResults[i].getRelevance());
               //                                }
               //                            }
               //                        }
               //
               //                        // concatenate arrays
               //                        ICompletionProposal[] total = new ICompletionProposal[results.length + templateResults.length];
               //                        System.arraycopy(templateResults, 0, total, 0, templateResults.length);
               //                        System.arraycopy(results, 0, total, templateResults.length, results.length);
               //                        results = total;
               //                    }

               this.numberOfComputedResults = (results == null ? 0 : results.length);

               /*
                * Order here and not in result collector to make sure that the order
                * applies to all proposals and not just those of the compilation unit.
                */
               return this.order(results);
            }
         }
      }
      catch (JavaModelException x)
      {
         Shell shell = viewer.getTextWidget().getShell();
         if (x.isDoesNotExist())
         {
            MessageDialog.openInformation(shell, ("CompletionProcessor.error.notOnBuildPath.title"), ("CompletionProcessor.error.notOnBuildPath.message"));//$NON-NLS-1$//$NON-NLS-2$
         }
         else
         {
            ErrorDialog.openError(shell, ("CompletionProcessor.error.accessing.title"), ("CompletionProcessor.error.accessing.message"), x.getStatus());//$NON-NLS-2$ //$NON-NLS-1$
         }
      }
      catch (BadLocationException e)
      {
         e.printStackTrace();
      }
      catch (JasperException e) // was JasperException...
      {
      	e.printStackTrace();
      }
//      catch (E e)
//      {
//         e.printStackTrace();
//      }
      return null;
   }


   /**
    * Description of the Method
    *
    * @param reader           Description of the Parameter
    * @return                 Description of the Return Value
    * @exception IOException  Description of the Exception
    */
   private boolean looksLikeMethod(JavaCodeReader reader)
      throws IOException
   {
      int curr = reader.read();
      while (curr != JavaCodeReader.EOF && Character.isWhitespace((char) curr))
      {
         curr = reader.read();
      }
      if (curr == JavaCodeReader.EOF)
      {
         return false;
      }
      return Character.isJavaIdentifierPart((char) curr) || Character.isJavaIdentifierStart((char) curr);
   }


   /**
    * Order the given proposals.
    *
    * @param proposals  Description of the Parameter
    * @return           Description of the Return Value
    */
   private ICompletionProposal[] order(ICompletionProposal[] proposals)
   {
      Arrays.sort(proposals, this.comparator);
      return proposals;
   }


   /**
    * Description of the Class
    *
    * @author    Laurent Etiemble
    * @version   $Revision$
    */
   private static class ContextInformationWrapper implements IContextInformation, IContextInformationExtension
   {
      private int position;

      private final IContextInformation contextInformation;


      /**
       *Constructor for the ContextInformationWrapper object
       *
       * @param contextInformation  Description of the Parameter
       */
      public ContextInformationWrapper(IContextInformation contextInformation)
      {
         this.contextInformation = contextInformation;
      }


      /**
       * Description of the Method
       *
       * @param object  Description of the Parameter
       * @return        Description of the Return Value
       */
      public boolean equals(Object object)
      {
         if (object instanceof ContextInformationWrapper)
         {
            return this.contextInformation.equals(((ContextInformationWrapper) object).contextInformation);
         }
         return this.contextInformation.equals(object);
      }


      /**
       * Gets the contextDisplayString attribute of the ContextInformationWrapper object
       *
       * @return   The contextDisplayString value
       */
      public String getContextDisplayString()
      {
         return this.contextInformation.getContextDisplayString();
      }


      /**
       * Gets the contextInformationPosition attribute of the ContextInformationWrapper object
       *
       * @return   The contextInformationPosition value
       */
      public int getContextInformationPosition()
      {
         return this.position;
      }


      /**
       * Gets the image attribute of the ContextInformationWrapper object
       *
       * @return   The image value
       */
      public Image getImage()
      {
         return this.contextInformation.getImage();
      }


      /**
       * Gets the informationDisplayString attribute of the ContextInformationWrapper object
       *
       * @return   The informationDisplayString value
       */
      public String getInformationDisplayString()
      {
         return this.contextInformation.getInformationDisplayString();
      }


      /**
       * Sets the contextInformationPosition attribute of the ContextInformationWrapper object
       *
       * @param position  The new contextInformationPosition value
       */
      public void setContextInformationPosition(int position)
      {
         this.position = position;
      }
   }
}
