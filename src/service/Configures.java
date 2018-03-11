package service;

import java.io.*;
import java.util.*;

public class Configures {
	public static final String def="/default.properties";
	public static final InputStream defins=Configures.class.getResourceAsStream(def);
	
	public Properties defaults;
	
	public Configures() {
		this.defaults=new Properties();
		try {
			defaults.load(defins);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
