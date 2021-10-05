package co.com.common.config;


import java.io.IOException;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("generalConfiguration")
public class GeneralConfiguration {
	
	@Autowired GeneralConfigurationManager generalConfigurationManager; 
	
	public GeneralConfiguration(GeneralConfigurationManager generalConfigurationManager){
		super();
		this.generalConfigurationManager = generalConfigurationManager;
	}
	
	public GeneralConfiguration(){}
	
	@PostConstruct
	public void loadConfiguration(){
		try {
			generalConfigurationManager.loadProperties(GeneralConfigurationManager.GENERAL_PROPERTIES);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
