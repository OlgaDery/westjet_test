/**
 * 
 */
package rest;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import beans.ApplicationContexProvider;
import beans.Singleton;
import model.AddValues;

/**
 * @author Olga
 *
 */
@RestController
public class Service1 {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired // Injected by Spring when bean is "ready"
	ApplicationContexProvider contextProvider;
//	@Autowired
//	private ApplicationContext context;

	@GetMapping(value ="/math/add")
	
	//Here we are declaring the origin of clients which are allowed to consume our resource.
	//In real life, it may make sense to narrow the list of clients.
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	
	public ResponseEntity <String> addTwoParams (
			HttpServletRequest request,
	//Here the parameters are declared as "required", though it is possible to set default values
	//to be used if the parameters are missing
			
			@RequestParam(value="n1", required=true) String param1, 
		    @RequestParam(value="n2", required=true) String param2) 
	{
		logger.info("enter addTwoParams");

		try {
			String hi = (contextProvider.getApplicationContext().getBean(Singleton.class).sayHi());//context.getBean(Singleton.class);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		//
		
		try {
			try {
	//Here we are searching for the "authorization" header which is supposed to contain the unique key
	//associated with the client.
				String key = request.getHeader("authorization");
	
	//Checking up our data set containing all the client keys (in real life it would be singleton set/list or cache). 
				if (Arrays.asList(Singleton.getKeys()).contains(key)) {
					try {
						logger.info("valid key has been provided");
						AddValues addValues = new AddValues (Integer.valueOf(param1), Integer.valueOf(param2));
						Integer result = addValues.getResult();
						
	//In the case of authentication key and all the parameters are correctly provided, we are returning
	//our calculated value with "ok" status.
						return ResponseEntity.ok().body(String.valueOf(result));
						
					} catch (NumberFormatException e) {
	//This exception is being thrown if the parameters are not convertible to integers. Return status "unprocessableEntity".
						logger.info("not convertable to integers or no parameters");
						return ResponseEntity.unprocessableEntity().build();
					} 
					
				} else {
	//If the authentication key being provided in the header has not been found, return status 401 (unauthorized).
					logger.info("key has not been found");
					return ResponseEntity.status(401).build();
				}
			} catch (NullPointerException e) {
				logger.info("key has not been provided");
	//If there is no authentication header, return status 401 (unauthorized).
				return ResponseEntity.status(401).build();
			} 
			
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			return ResponseEntity.badRequest().build();
		} finally {
			logger.info("exit addTwoParams");
		}
		
	}
	
	@PostMapping(value ="/math/add", consumes = {"application/x-www-form-urlencoded"}) //
	
	//Here we are declaring the origin of clients which are allowed to consume our resource.
	//In real life, it may make sense to narrow the list of clients.
	@CrossOrigin(origins = "*", maxAge = 3600)
	@ResponseBody
	
	public ResponseEntity <String> postTwoParams (
			HttpServletRequest request)
	{
		//How to get API keys
			logger.info("enter postTwoParams");
			
			try {
				try {
	//Here we are searching for the "authorization" header which is supposed to contain the unique key
	//associated with the client.
					String key = request.getHeader("authorization");
					logger.info(key);
				
	//Checking up our data set containing all the client keys (in real life it would be singleton set/list or cache). 
					if (Arrays.asList(Singleton.getKeys()).contains(key)) {
						try {
							logger.info("valid key has been provided");
							
	//Extracting the parameters from the request object
							Map <String, String[]> paramMap = request.getParameterMap();
							Integer value1 = Integer.valueOf(Arrays.asList(paramMap.get("n1")).get(0));
							Integer value2 = Integer.valueOf(Arrays.asList(paramMap.get("n2")).get(0));
							
							AddValues addValues = new AddValues (Integer.valueOf(value1), Integer.valueOf(value2));
							
	//If we created a new object and submitted it to the database, we would return "Created" status.
							
							return ResponseEntity.ok().body(String.valueOf(addValues.getResult()));
							
						} catch (NumberFormatException|NullPointerException e) {
							logger.info("not convertable to integers or no parameters");
	//This exception is being thrown if the parameters are not convertible to integers. Return status "unprocessableEntity".
							return ResponseEntity.unprocessableEntity().build();
						}
						
					} else {
						logger.info("key has not been found");
	//If the authentication key being provided in the header has not been found, return status 401 (unauthorized).
						return ResponseEntity.status(401).build();
					}
				} catch (NullPointerException e) {
					logger.info("key has not been provided");
					return ResponseEntity.status(401).build();
				} 
				
			} catch (Exception e) {
				logger.info(e.getMessage(), e);
				return ResponseEntity.badRequest().build();
			} finally {
				logger.info("exit postTwoParams");
			}
			
		
	}


}
