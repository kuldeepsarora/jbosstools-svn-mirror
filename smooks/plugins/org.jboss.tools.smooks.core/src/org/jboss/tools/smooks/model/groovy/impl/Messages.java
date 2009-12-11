package org.jboss.tools.smooks.model.groovy.impl;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.jboss.tools.smooks.model.groovy.impl.messages"; //$NON-NLS-1$
	public static String GroovyFactoryImpl_Error_Class_Not_Valid;
	public static String GroovyFactoryImpl_Error_Not_Valid_Classifier;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
