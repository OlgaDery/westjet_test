/**
 * 
 */
package beans;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Olga
 *
 */
@Component
public class Singleton {
	
	//This class contains the methods to store and access authentication keys 
	//for users (assuming that they were previously generated)
	
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	//This array represents a real-life data storage like file or DB
	private String [] keys = {"3.14"};
	
	private Set <String> keySet;
	
	public Singleton () {
		logger.info("enter Singleton ()");
	    //the authentication keys are being upload when the Singleton bean is  
		//instantiated on the app start up. It is instantiated in AppCongig class.
		uploadKeys();
		
		logger.info("exit Singleton ()");
	}
	
	public Set <String> getKeys() {
		//Method to access the key set from other classes (like Rest controllers)
		//to verify if the user is allowed to call the APIs
		logger.info("enter getKeys()");
		
		logger.info("exit getKeys()");
		return keySet;
	}
	
	private void uploadKeys () {
		//This method should be calling only once, on the app start up.
		logger.info("enter uploadKeys()");
		
		keySet = new HashSet<>();
		keySet.addAll(Arrays.asList(keys));
		
		logger.info("exit uploadKeys()");
		
	}


}
