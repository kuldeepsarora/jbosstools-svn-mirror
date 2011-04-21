/*
 * JBoss-IDE, Eclipse plugins for JBoss
 *
 * Distributable under LGPL license.
 * See terms of license at www.gnu.org.
 */
package org.jboss.ide.eclipse.jdt.xml.ui.text.rules;

import org.eclipse.jface.text.rules.IWordDetector;

/*
 * This file contains materials derived from the
 * Solareclipse project. License can be found at :
 * http://solareclipse.sourceforge.net/legal/cpl-v10.html
 */
/**
 * XML Nmtoken detector.
 *
 * @author    Laurent Etiemble
 * @version   $Revision$
 */
public class NmtokenDetector implements IWordDetector
{

   /**
    * Gets the wordPart attribute of the NmtokenDetector object
    *
    * @param ch  Description of the Parameter
    * @return    The wordPart value
    */
   public boolean isWordPart(char ch)
   {
      if (Character.isUnicodeIdentifierPart(ch))
      {
         return true;
      }

      switch (ch)
      {
         case '.':
         case '-':
         case '_':
         case ':':
            return false;
      }

      return false;
   }


   /**
    * Gets the wordStart attribute of the NmtokenDetector object
    *
    * @param ch  Description of the Parameter
    * @return    The wordStart value
    */
   public boolean isWordStart(char ch)
   {
      return isWordPart(ch);
   }
}
