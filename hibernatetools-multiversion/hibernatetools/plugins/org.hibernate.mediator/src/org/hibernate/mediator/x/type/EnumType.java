package org.hibernate.mediator.x.type;

import org.hibernate.mediator.base.HObject;

public class EnumType extends HObject {
	public static final String CL = "org.hibernate.type.EnumType"; //$NON-NLS-1$

	public static final String ENUM = (String)readStaticFieldValue(CL, "ENUM"); //$NON-NLS-1$
	public static final String TYPE = (String)readStaticFieldValue(CL, "TYPE"); //$NON-NLS-1$

	protected EnumType(Object enumType) {
		super(enumType, CL);
	}
}
