package co.com.common.messages;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.springframework.stereotype.Component;

@Component("messageManager")
public class MessageManager {
	/**
	   * Contenedor del archivo propiedades.
	   */
	  private Properties properties = null;
	  public final static String MESSAGES_PROPERTIES = "message.properties";
	  
	  public MessageManager(){}
	  public MessageManager(Properties properties) {
			super();
			this.properties = properties;
		}
	  public void loadProperties(String propertiesFileName) throws IOException {
		    InputStream in = MessageManager.class.getResourceAsStream("/" + propertiesFileName);
		    properties = new Properties();
		    properties.load(in);
		    in.close();
		  }
	  /**
	   * Obtiene la propiedad como texto plano
	   *
	   * @param propertyKey Identificador de la propiedad;
	   * @return            Un {@link String} que contiene el valor de la propiedad.
	   */
	  public String getProperty(String propertyKey) {
	    return properties.getProperty(propertyKey);
	  }

	  public void setProperty(String key, String value) {
	    properties.setProperty(key, value);
	  }	
}