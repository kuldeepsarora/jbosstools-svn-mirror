// Copyright 2012 Jeeeyul Lee, Seoul, Korea
// https://github.com/jeeeyul/pde-tools
//
// This module is multi-licensed and may be used under the terms
// of any of the following licenses:
//
// EPL, Eclipse Public License, V1.0 or later, http://www.eclipse.org/legal
// LGPL, GNU Lesser General Public License, V2.1 or later, http://www.gnu.org/licenses/lgpl.html
// GPL, GNU General Public License, V2 or later, http://www.gnu.org/licenses/gpl.html
// AL, Apache License, V2.0 or later, http://www.apache.org/licenses
// BSD, BSD License, http://www.opensource.org/licenses/bsd-license.php
// MIT, MIT License, http://www.opensource.org/licenses/MIT
//
// Please contact the author if you need another license.
// This module is provided "as is", without warranties of any kind.
package net.jeeeyul.eclipse.themes;

import java.io.File;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.jboss.tools.central.themes.Activator;
import org.osgi.framework.Bundle;

/*
 * Generated by PDE Tools.
 */
public class SharedImages{
	
	/**
	 * <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAYAAACNMs+9AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAadEVYdFNvZnR3YXJlAFBhaW50Lk5FVCB2My41LjEwMPRyoQAAAHlJREFUKFNjONvaKgPE9lAsygAFQL4okrgMA4hzID39//7k5P9Atj8Q80OxP0gMJAdSA1YIEtgZFvZ/b0ICSDAehEFskBjUALBCkBX+MIldkZH/QRhJI8gWiJOgVsWDFGwPCABjEBtqOj/M3cQpJNpqUjxDdPAQFeAABZ7MoR/tUJQAAAAASUVORK5CYII=">
	 * Image constant for icons/close-active.png
	 */
	public static final String CLOSE_ACTIVE = "icons/close-active.png";
	
	/**
	 * <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAYAAACNMs+9AAAABGdBTUEAALGPC/xhBQAAAAlwSFlzAAAOvwAADr8BOAVTJAAAABp0RVh0U29mdHdhcmUAUGFpbnQuTkVUIHYzLjUuMTAw9HKhAAAAZ0lEQVQoU2NgIAVUVlYmAvF1INaA6QOxgfgaEGfAzYIqmgWikRSCNILE+pAVgnSDBMGKkTSC+HBbkDXAFINpnM5HMhW7QqjDYW5CNhXFgwzIbkLyDNzNyG4rgFqLHDySULFoUoKaAQCCfF8Q7f0kfgAAAABJRU5ErkJggg==">
	 * Image constant for icons/close-normal.png
	 */
	public static final String CLOSE_NORMAL = "icons/close-normal.png";

	private static final ImageRegistry REGISTRY = new ImageRegistry(Display.getDefault());
	
	public static Image getImage(String key){
		Image result = REGISTRY.get(key);
		if(result == null){
			result = loadImage(key);
			REGISTRY.put(key, result);
		}
		return result;
	}
	
	public static ImageDescriptor getImageDescriptor(String key){
		ImageDescriptor result = REGISTRY.getDescriptor(key);
		if(result == null){
			result = loadImageDescriptor(key);
			REGISTRY.put(key, result);
		}
		return result;
	}
	
	private static Image loadImage(String key) {
		try {
			Bundle bundle = Activator.getDefault().getBundle();
			URL resource = null;
			
			if(bundle != null){
				resource = bundle.getResource(key);
			}else{
				resource = new File(key).toURI().toURL();	
			}
			
			Image image = new Image(null, resource.openStream());
			return image;
		} catch (Exception e) {
			e.printStackTrace();
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
		}
	}
	
	private static ImageDescriptor loadImageDescriptor(String key) {
		try {
			Bundle bundle = Activator.getDefault().getBundle();
			URL resource = null;
			
			if(bundle != null){
				resource = bundle.getResource(key);
			}else{
				resource = new File(key).toURI().toURL();	
			}
			
			ImageDescriptor descriptor = ImageDescriptor.createFromURL(resource);
			return descriptor;
		} catch (Exception e) {
			e.printStackTrace();
			return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_ERROR_TSK);
		}
	}
}
