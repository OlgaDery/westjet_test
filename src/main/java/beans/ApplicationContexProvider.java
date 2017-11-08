/**
 * 
 */
package beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Olga
 * This class is to store the ApplicationContext. The ApplicationContext
 * is being set on the application start up what is the feature of the framework
 * It is accessible via get method. As this class is declared as a bean in the 
 * AppConfig class, the public methods of this class are available after the class
 * is being injected.
 */

@Component
public class ApplicationContexProvider implements ApplicationContextAware {
	
	private static ApplicationContext context;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

    public ApplicationContext getApplicationContext() {
    	//getting the app context from other app components
    	logger.info("enter getApplicationContext");
    	
    	logger.info("exit getApplicationContext");
        return context;
    }


	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO this method is being called on the app start up
		
		logger.info("enter setApplicationContext");
		context = applicationContext;
		
		logger.info("exit setApplicationContext");

	}

}
