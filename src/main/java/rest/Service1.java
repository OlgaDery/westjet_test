/**
 * 
 */
package rest;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import beans.Singleton;
import model.AddValues;

/**
 * @author Olga
 *
 */
@RestController
public class Service1 {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	

	@GetMapping(value ="/math/add")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity <String> addTwoParams (
			HttpServletRequest request,
			@RequestParam(value="n1", required=true) String param1, 
		    @RequestParam(value="n2", required=true) String param2) 
	{
		logger.info("enter addTwoParams");
		
		try {
			try {
				//How to get API keys
				String key = request.getHeader("authorization");
				
				if (Arrays.asList(Singleton.getKeys()).contains(key)) {
					try {
						logger.info("valid key has been provided");
						AddValues addValues = new AddValues (Integer.valueOf(param1), Integer.valueOf(param2));
						Integer result = addValues.getResult();
						return ResponseEntity.ok().body(String.valueOf(result));
					} catch (NumberFormatException e) {
						logger.info("not convertable to integers or no parameters");
						return ResponseEntity.unprocessableEntity().build();
					} 
					
				} else {
					logger.info("key has not been found");
					return ResponseEntity.status(401).build();
				}
			} catch (NullPointerException e) {
				logger.info("key has not been provided");
				return ResponseEntity.status(401).build();
			} finally {
				//((ConfigurableApplicationContext)context).close();
			}
			
			
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			return ResponseEntity.badRequest().build();
		} finally {
			logger.info("exit addTwoParams");
		}
		
	}
	
	@PostMapping(value ="/math/add", headers = "Content-Type=application/x-www-form-urlencoded")
	@ResponseBody
	
	public ResponseEntity <String> postTwoParams (
			HttpServletRequest request)//@RequestParam MultiValueMap <String, String> paramMap
	{
		//How to get API keys
			logger.info("enter postTwoParams");
			
			try {
				try {
					//How to get API keys
					String key = request.getHeader("authorization");
					logger.info(key);
					if (Arrays.asList(Singleton.getKeys()).contains(key)) {
						try {
							logger.info("valid key has been provided");
							Map <String, String[]> paramMap = request.getParameterMap();
							for (String k : paramMap.keySet()) {
								logger.info(k);
								
								for (String v : request.getParameterMap().get(k)) {
									logger.info(v);
								}
								
							}
							Integer value1 = Integer.valueOf(Arrays.asList(paramMap.get("n1")).get(0));
							Integer value2 = Integer.valueOf(Arrays.asList(paramMap.get("n2")).get(0));
							
							AddValues addValues = new AddValues (Integer.valueOf(value1), Integer.valueOf(value2));
			
							
							//Here we can call a method to add the value to a database/singleton
							
							return ResponseEntity.ok().body(String.valueOf(addValues.getResult()));
						} catch (NumberFormatException|NullPointerException e) {
							logger.info("not convertable to integers or no parameters");
							return ResponseEntity.unprocessableEntity().build();
						}
						
					} else {
						logger.info("key has not been found");
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
