/**
 * 
 */
package beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Olga
 *
 */
@Configuration
public class AppConfig {
	
	@Bean
	public Singleton singlenon () {
		return new Singleton();
		
	}
	
	@Bean
	public ApplicationContexProvider appContext () {
		return new ApplicationContexProvider();
		
	}

}
