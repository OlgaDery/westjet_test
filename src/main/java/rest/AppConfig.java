/**
 * 
 */package rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import beans.ApplicationContexProvider;
import beans.Singleton;

/**
 * @author Olga
 *This class is for beans instantiation.
 */
@Configuration
public class AppConfig {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Bean
	public Singleton singlenon () {
		logger.info("Singleton bean initialized");
		return new Singleton();
		
	}
	
	@Bean
	public ApplicationContexProvider appContext () {
		logger.info("ApplicationContexProvider bean initialized");
		return new ApplicationContexProvider();
		
	}

}
