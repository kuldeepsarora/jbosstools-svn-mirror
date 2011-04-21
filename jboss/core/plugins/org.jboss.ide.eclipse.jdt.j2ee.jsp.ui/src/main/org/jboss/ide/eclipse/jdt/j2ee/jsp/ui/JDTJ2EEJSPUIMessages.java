/*
 * JBoss-IDE, Eclipse plugins for JBoss
 *
 * Distributable under LGPL license.
 * See terms of license at www.gnu.org.
 */
package org.jboss.ide.eclipse.jdt.j2ee.jsp.ui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Message bundle accessor helper class
 *
 * @author    Laurent Etiemble
 * @version   $Revision$
 */
public class JDTJ2EEJSPUIMessages
{
   private final static String BUNDLE_NAME = JDTJ2EEJSPUIMessages.class.getName();
   private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);


   /** Default Constructor */
   private JDTJ2EEJSPUIMessages() { }


   /**
    * Gets the string corresponding to a given key
    *
    * @param key  The bundle key
    * @return     The internationalized value or the key if not found
    */
   public static String getString(String key)
   {
      try
      {
         return RESOURCE_BUNDLE.getString(key);
      }
      catch (MissingResourceException e)
      {
         return '!' + key + '!';
      }
   }
}
