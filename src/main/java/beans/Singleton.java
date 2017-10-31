/**
 * 
 */
package beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Olga
 *
 */
@Component
public class Singleton {
	
	//This is a simple representation of a real-life data storage where authentication keys for users 
	//(who are authorized to access APIs) are stored
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private String [] keys = {"3.14"};
	
	public String[] getKeys() {
		logger.info("enter getKeys()");
		
		logger.info("exit getKeys()");
		return keys;
	}

	
	
	

}
