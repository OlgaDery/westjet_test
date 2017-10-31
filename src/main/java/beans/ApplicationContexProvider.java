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
 *
 */
@Component
public class ApplicationContexProvider implements ApplicationContextAware {
	
	private static ApplicationContext context;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

    public ApplicationContext getApplicationContext() {
        return context;
    }


	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		
		logger.info("enter setApplicationContext");
		context = applicationContext;
		
		logger.info("exit setApplicationContext");

	}

}
