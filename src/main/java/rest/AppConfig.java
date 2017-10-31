/**
 * 
 */package rest;

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
	
	@Bean
	public Singleton singlenon () {
		return new Singleton();
		
	}
	
	@Bean
	public ApplicationContexProvider appContext () {
		return new ApplicationContexProvider();
		
	}

}
