package org.hibernate.mediator.x.cfg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import org.hibernate.mediator.base.HObject;
import org.hibernate.mediator.x.cfg.reveng.ReverseEngineeringStrategy;
import org.hibernate.mediator.x.mapping.Table;

public class JDBCMetaDataConfiguration extends Configuration {
	public static final String CL = "org.hibernate.cfg.JDBCMetaDataConfiguration"; //$NON-NLS-1$

	protected JDBCMetaDataConfiguration(Object configuration) {
		super(configuration, CL);
	}
	
	public static JDBCMetaDataConfiguration newInstance() {
		return new JDBCMetaDataConfiguration(HObject.newInstance(CL));
	}

	public JDBCMetaDataConfiguration setProperties(Properties properties) {
		Object tmp = invoke(mn(), properties);
		return (tmp == Obj() ? this : new JDBCMetaDataConfiguration(tmp));
	}

	public void setPreferBasicCompositeIds(boolean flag) {
		invoke(mn(), flag);
	}

	public void setReverseEngineeringStrategy(ReverseEngineeringStrategy reverseEngineeringStrategy) {
		invoke(mn(), reverseEngineeringStrategy);
	}

	public void readFromJDBC() {
		invoke(mn());
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Table> getTableMappingsArr() {
		ArrayList<Table> arr = new ArrayList<Table>(); 
		Iterator it = (Iterator)invoke(mn());
		while (it.hasNext() ) {
			Object obj = it.next();
			if (obj != null) {
				arr.add(new Table(obj));
			}
		}
		return arr;
	}

	public Iterator<Table> getTableMappingsIt() {
		return getTableMappingsArr().iterator();
	}

}
