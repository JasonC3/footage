package dao;

import java.io.*;
import java.util.*;

public class Configures {
	protected static final String def="/default.properties";
	protected static final InputStream defins=Configures.class.getResourceAsStream(def);
	
	public Properties defaults,settings;
	
	public Configures() {
		this.defaults=new Properties();
		try {
			defaults.load(defins);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
