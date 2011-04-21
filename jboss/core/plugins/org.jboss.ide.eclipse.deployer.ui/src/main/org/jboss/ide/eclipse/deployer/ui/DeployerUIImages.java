/*
 * JBoss-IDE, Eclipse plugins for JBoss
 *
 * Distributable under LGPL license.
 * See terms of license at www.gnu.org.
 */
package org.jboss.ide.eclipse.deployer.ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.jboss.ide.eclipse.core.AbstractPlugin;

/**
 * @author    letiemble
 * @version   $Revision$
 */
public class DeployerUIImages
{
   /** Declare Common paths */
   protected static URL ICON_BASE_URL = null;
   /** A table of all the <code>ImageDescriptor</code>s. */
   protected static HashMap imageDescriptors;
   /** The image registry containing <code>Image</code>s. */
   protected static ImageRegistry imageRegistry;

   /** basic colors - size 16x16 */
   protected final static String CTOOL = "ctool16/";//$NON-NLS-1$
   /** disabled - size 16x16 */
   protected final static String DLCL = "dlcl16/";//$NON-NLS-1$
   /** disable colors - size 16x16 */
   protected final static String DTOOL = "dtool16/";//$NON-NLS-1$
   /** basic colors - size 16x16 */
   protected final static String ELCL = "elcl16/";//$NON-NLS-1$
   /** enabled - size 16x16 */
   protected final static String LOCALTOOL = "clcl16/";//$NON-NLS-1$
   /** basic colors - size 16x16 */
   protected final static String OBJECT = "obj16/";//$NON-NLS-1$
   /** basic colors - size 7x8 */
   protected final static String OVR = "ovr16/";//$NON-NLS-1$
   /** basic colors - size 16x16 */
   protected final static String VIEW = "cview16/";//$NON-NLS-1$
   /** wizard banners */
   protected final static String WIZBAN = "wizban/";//$NON-NLS-1$


   /**
    * Returns the <code>Image<code> identified by the given key,
    * or <code>null</code> if it does not exist.
    *      *
    *
    * @param key  Description of the Parameter
    * @return     The image value
    */
   public static Image getImage(String key)
   {
      return getImageRegistry().get(key);
   }


   /**
    * Returns the <code>ImageDescriptor<code> identified by the given key,
    * or <code>null</code> if it does not exist.
    *      *
    *
    * @param key  Description of the Parameter
    * @return     The imageDescriptor value
    */
   public static ImageDescriptor getImageDescriptor(String key)
   {
      if (imageDescriptors == null)
      {
         initializeImageRegistry();
      }
      return (ImageDescriptor) imageDescriptors.get(key);
   }


   /**
    * Returns the ImageRegistry.
    *
    * @return   The imageRegistry value
    */
   public static ImageRegistry getImageRegistry()
   {
      if (imageRegistry == null)
      {
         initializeImageRegistry();
      }
      return imageRegistry;
   }


   /**
    * Initialize the image registry by declaring all of the required graphics.
    * This involves creating JFace image descriptors describing how to
    * create/find the image should it be needed. The image is not actually
    * allocated until requested. Prefix conventions Wizard Banners WIZBAN_
    * Preference Banners PREF_BAN_ Property Page Banners PROPBAN_ Color toolbar
    * CTOOL_ Enable toolbar ETOOL_ Disable toolbar DTOOL_ Local enabled toolbar
    * ELCL_ Local Disable toolbar DLCL_ Object large OBJL_ Object small OBJS_
    * View VIEW_ Product images PROD_ Misc images MISC_ Where are the images?
    * The images (typically gifs) are found in the same location as this plugin
    * class. This may mean the same package directory as the package holding
    * this class. The images are declared using this.getClass() to ensure they
    * are looked up via this plugin class.
    *
    * @return   Description of the Return Value
    * @see      JFace's ImageRegistry
    */
   public static ImageRegistry initializeImageRegistry()
   {
      imageRegistry = new ImageRegistry(AbstractPlugin.getStandardDisplay());
      imageDescriptors = new HashMap(30);
      declareImages();
      return imageRegistry;
   }


   /** Declare all images */
   protected static void declareImages()
   {
      //Object
      declareRegistryImage(IDeployerUIConstants.IMG_OBJS_FILESYSTEM, OBJECT + "filesystem.gif");//$NON-NLS-1$
      declareRegistryImage(IDeployerUIConstants.IMG_OBJS_LOCAL_DEPLOYER, OBJECT + "local_deployer.gif");//$NON-NLS-1$

      //Overlay
      declareRegistryImage(IDeployerUIConstants.IMG_OVR_DEPLOYED, OVR + "deployed.gif");//$NON-NLS-1$
   }


   /**
    * Description of the Method
    *
    * @param iconPath                   Description of the Parameter
    * @return                           Description of the Return Value
    * @exception MalformedURLException  Description of the Exception
    */
   protected static URL makeIconFileURL(String iconPath)
      throws MalformedURLException
   {
      if (ICON_BASE_URL == null)
      {
         throw new MalformedURLException();
      }

      return new URL(ICON_BASE_URL, iconPath);
   }


   /**
    * Declare an Image in the registry table.
    *
    * @param key   The key to use when registering the image
    * @param path  The path where the image can be found. This path is relative
    *            to where this plugin class is found (i.e. typically the
    *            packages directory)
    */
   protected final static void declareRegistryImage(String key, String path)
   {
      ImageDescriptor desc = ImageDescriptor.getMissingImageDescriptor();
      try
      {
         desc = ImageDescriptor.createFromURL(makeIconFileURL(path));
      }
      catch (MalformedURLException me)
      {
         AbstractPlugin.log(me);
      }
      imageRegistry.put(key, desc);
      imageDescriptors.put(key, desc);
   }

   static
   {
      String pathSuffix = "icons/full/";//$NON-NLS-1$

      try
      {
         ICON_BASE_URL = new URL(DeployerUIPlugin.getDefault().getBundle().getEntry("/"), pathSuffix);//$NON-NLS-1$
      }
      catch (MalformedURLException e)
      {
         // do nothing
      }
   }
}

