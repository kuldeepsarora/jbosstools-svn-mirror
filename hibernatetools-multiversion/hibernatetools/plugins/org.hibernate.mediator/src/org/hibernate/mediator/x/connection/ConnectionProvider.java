package org.hibernate.mediator.x.connection;

import org.hibernate.mediator.base.HObject;

public class ConnectionProvider extends HObject {
	public static final String CL = "org.hibernate.connection.ConnectionProvider"; //$NON-NLS-1$

	public ConnectionProvider(Object connectionProvider) {
		super(connectionProvider, CL);
	}

	public void close() {
		invoke(mn());
	}
}
