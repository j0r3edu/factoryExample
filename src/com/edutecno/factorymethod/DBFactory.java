package com.edutecno.factorymethod;

import java.util.Properties;

import com.edutecno.factorymethodimpl.OracleDBAdapter;
import com.edutecno.util.PropertiesUtil;

public class DBFactory {
	private static final String DB_FACTORY_PROPERTY_URL = "com/edutecno/properties/DBFactory.properties";
	private static final String DEFAULT_DB_CLASS_PROP = "defaultDBClass";
	
	//ENUM
	public static IDBAdapter getDBAdapter(DBType tipoDb) {
		switch(tipoDb) {
		case Oracle:
			return new OracleDBAdapter();
		case MySQL:
			return new OracleDBAdapter();//deberia ser un adaptador mysql
		default:
			System.out.println("BD no soportada");
		}
		return null;
	}
	
	public static IDBAdapter getDefaultDBAdapter(){
		try {
			Properties prop = PropertiesUtil.loadProperty(DB_FACTORY_PROPERTY_URL);
			String defaultDBClass = prop.getProperty(DEFAULT_DB_CLASS_PROP);
			return (IDBAdapter) Class.forName(defaultDBClass).newInstance();
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
