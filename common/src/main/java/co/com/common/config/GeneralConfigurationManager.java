package co.com.common.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.text.MessageFormat;

import org.springframework.stereotype.Service;

@Service("generalConfigurationManager")
public class GeneralConfigurationManager {
	
	/**
	* properties container file
	*/
	private Properties properties = null;

	public final static String GENERAL_PROPERTIES = "generalConfiguration.properties";

	public GeneralConfigurationManager() {}
	  
	public GeneralConfigurationManager(Properties properties) {
		super();
		this.properties = properties;
	}

	public void loadProperties(String propertiesFileName) throws IOException {
	    InputStream in = GeneralConfigurationManager.class.getResourceAsStream("/" + propertiesFileName);
	    properties = new Properties();
	    properties.load(in);
	    in.close();
	}
	
	public void setProperty(String key, String value) {
	    properties.setProperty(key, value);
	}

	/**
	* Get the property in plain text
	*
	* @param propertyKey Identificador de la propiedad;
	* @return            Un {@link String} que contiene el valor de la propiedad.
	*/
	public String getProperty(String messageId, String... params) {
	    String rtMessage = properties.getProperty(messageId);
	    MessageFormat messageFormat = new MessageFormat(rtMessage);
	    rtMessage = messageFormat.format(params, new StringBuffer(), null).toString();
	    return rtMessage;
	}	
	
}
