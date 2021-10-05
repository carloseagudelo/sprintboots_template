package co.com.common.messages;

import java.io.IOException;
import java.text.MessageFormat;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messages")
public class MessageUtils {
	
	@Autowired
	private MessageManager messageManager;

	public MessageUtils() {}
	
	public MessageUtils(MessageManager propertiesManager) {
		super();
		this.messageManager = propertiesManager;
	}
	
	@PostConstruct
	public void loadConfiguration(){
		try {
			messageManager.loadProperties(MessageManager.MESSAGES_PROPERTIES);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getMessage(String key) {
		return messageManager.getProperty(key);
	}
	
	public String getMessage2(String key, String i) {
		return messageManager.getProperty(key);
	}
	
	 /**
	   * Obtiene el mensaje como texto plano y parametrizado.
	   * 
	   * @param messageId clave del mensaje.
	   * @param params parámetros del mensaje.
	   * @return Un {@link String} que contiene el mensaje parametrizado.
	   */
	public String getMessage(String messageId, String... params) {
	    String rtMessage = messageManager.getProperty(messageId);
	    MessageFormat messageFormat = new MessageFormat(rtMessage);
	    rtMessage = messageFormat.format(params, new StringBuffer(), null).toString();
	    return rtMessage;
	}	

}
