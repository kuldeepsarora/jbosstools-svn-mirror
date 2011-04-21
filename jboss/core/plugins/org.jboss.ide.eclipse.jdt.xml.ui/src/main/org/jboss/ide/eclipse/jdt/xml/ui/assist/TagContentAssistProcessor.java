/*
 * JBoss-IDE, Eclipse plugins for JBoss
 *
 * Distributable under LGPL license.
 * See terms of license at www.gnu.org.
 */
package org.jboss.ide.eclipse.jdt.xml.ui.assist;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.jboss.ide.eclipse.jdt.ui.text.rules.IPositionTranslator;
import org.jboss.ide.eclipse.jdt.xml.ui.JDTXMLUIMessages;
import org.jboss.ide.eclipse.jdt.xml.ui.reconciler.IReconcilierHolder;
import org.jboss.ide.eclipse.jdt.xml.ui.reconciler.XMLNode;

/*
 * This file contains materials derived from the
 * XMen project. License can be found at :
 * http://www.eclipse.org/legal/cpl-v10.html
 */
/**
 * @author    Laurent Etiemble
 * @version   $Revision$
 */
public class TagContentAssistProcessor extends XMLContentAssistProcessor
{

   /**
    *Constructor for the TagContentAssistProcessor object
    *
    * @param holder      Description of the Parameter
    * @param store       Description of the Parameter
    * @param translator  Description of the Parameter
    */
   public TagContentAssistProcessor(IReconcilierHolder holder, IPreferenceStore store, IPositionTranslator translator)
   {
      super(holder, store, translator);
   }


   /**
    * Description of the Method
    *
    * @param viewer  Description of the Parameter
    * @param offset  Description of the Parameter
    * @return        Description of the Return Value
    */
   public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset)
   {
      if (this.getReconcilierHolder() == null)
      {
         return new ICompletionProposal[0];
      }

      if (offset == 0)
      {
         String word = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";//$NON-NLS-1$
         ICompletionProposal[] cp = new ICompletionProposal[1];
         cp[0] = new CompletionProposal(word, offset, 0, word.length(), null, word, null, null);
         return cp;
      }

      // Use the translator in case of nested document views
      int translated = this.translator.translateParentOffset(viewer.getDocument(), offset);
      XMLNode node = this.getNodeAt(viewer.getDocument(), translated);

      if (node == null)
      {
         return new ICompletionProposal[0];
      }

      char quote = '\0';
      int state = node.getStateAt(translated);

      switch (state)
      {
         case XMLNode.ATTRIBUTE:
            return this.computeAttributes(viewer.getDocument(), node, translated, offset);
         case XMLNode.TAG:
            return this.computeTags(viewer.getDocument(), node, translated, offset);
         case XMLNode.SINGLEQUOTE:
            quote = '\'';
         case XMLNode.DOUBLEQUOTE:
            quote = '"';
         case XMLNode.ATT_VALUE:
            return this.computeAttributeValues(viewer.getDocument(), node, translated, offset, quote);
      }

      return this.computeTags(viewer.getDocument(), node, translated, offset);
   }


   /**
    * Description of the Method
    *
    * @param viewer  Description of the Parameter
    * @param offset  Description of the Parameter
    * @return        Description of the Return Value
    */
   public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset)
   {
      return null;
   }


   /**
    * Gets the completionProposalAutoActivationCharacters attribute of the TagContentAssistProcessor object
    *
    * @return   The completionProposalAutoActivationCharacters value
    */
   public char[] getCompletionProposalAutoActivationCharacters()
   {
      return new char[]{' ', '/', '<', '=', '\'', '"'};
   }


   /**
    * Gets the contextInformationAutoActivationCharacters attribute of the TagContentAssistProcessor object
    *
    * @return   The contextInformationAutoActivationCharacters value
    */
   public char[] getContextInformationAutoActivationCharacters()
   {
      return null;
   }


   /**
    * Gets the contextInformationValidator attribute of the TagContentAssistProcessor object
    *
    * @return   The contextInformationValidator value
    */
   public IContextInformationValidator getContextInformationValidator()
   {
      return null;
   }


   /**
    * Gets the errorMessage attribute of the TagContentAssistProcessor object
    *
    * @return   The errorMessage value
    */
   public String getErrorMessage()
   {
      return JDTXMLUIMessages.getString("TagContentAssistProcessor.error.message");//$NON-NLS-1$
   }

}
