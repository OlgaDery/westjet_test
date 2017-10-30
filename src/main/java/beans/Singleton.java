/**
 * 
 */
package beans;

import org.springframework.stereotype.Component;

/**
 * @author Olga
 *
 */
@Component

public class Singleton {
	
	//This is a simple representation of a real-life data storage where authentication keys for users 
	//(who are authorized to access APIs) are stored
	
	public static String[] getKeys() {
		
		return keys;
	}

	private static final String [] keys = {"3.14"};
	
	
	

}
