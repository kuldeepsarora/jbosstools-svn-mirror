package org.hibernate.mediator.x.tool.hbm2x;

import java.io.StringWriter;
import java.util.Properties;

import org.hibernate.mediator.base.HObject;

public class HibernateConfigurationExporter extends Exporter {
	public static final String CL = "org.hibernate.tool.hbm2x.HibernateConfigurationExporter"; //$NON-NLS-1$

	protected HibernateConfigurationExporter(Object hibernateConfigurationExporter) {
		super(hibernateConfigurationExporter, CL);
	}
	
	public static HibernateConfigurationExporter newInstance() {
		return new HibernateConfigurationExporter(HObject.newInstance(CL));
	}

	public void setCustomProperties(Properties props) {
		invoke(mn(), props);
	}

	public void setOutput(StringWriter stringWriter) {
		invoke(mn(), stringWriter);
	}
}
