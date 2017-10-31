/**
 * 
 */
package rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import beans.ApplicationContexProvider;
import beans.Singleton;

/**
 * @author Olga
 *
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App {
	
	@Bean
	public Singleton singlenon () {
		return new Singleton();
		
	}
	
	@Bean
	public ApplicationContexProvider appContext () {
		return new ApplicationContexProvider();
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 SpringApplication.run(App.class, args);

	}

}
