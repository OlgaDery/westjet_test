/**
 * 
 */
package rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Olga
 *This class contains the public static void main which is necessary to run
 *the whole pringBootApplication
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan

public class App {
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SpringApplication.run(App.class, args);

	}

}
