/**
 * 
 */
package rest;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import beans.ApplicationContexProvider;
import beans.Singleton;
import model.DateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Olga
 *
 */
@RestController
public class QueryTime {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

//Bean is injected by Spring
	@Autowired
	ApplicationContexProvider contextProvider;
	
	@GetMapping(value ="/time/now", produces = "application/json")
	
//Here we are declaring the origin of clients which are allowed to consume our resource.
//In real life, it may make sense to narrow the list of clients.
	@CrossOrigin(origins = "*", maxAge = 3600)
	
	public ResponseEntity <String> queryTime (HttpServletRequest request) 
	{
		try {

//Here we are searching for the "authorization" header which is supposed to contain the unique key
//associated with the client.
			try {
				String authKey = request.getHeader("authorization");
				String data;
				if (Arrays.asList(contextProvider.getApplicationContext().getBean(Singleton.class).getKeys()).contains(authKey)) {
						log.info("key : {}", authKey);
					
						try {
							
//Here we are creating the instance of the RestTemplateModified where we add 
//timeouts for the RequestFactory. If the connection times out, the client will receive 500 status.
							
							RestTemplate restTemplate = new RestTemplateModified();
							data = restTemplate.getForObject("http://api.timezonedb.com/v2/get-time-zone?key=K1C5NG4CRU55&format=json&by=zone&zone=America/Edmonton", 
									String.class);
						} catch (Exception e) {
							log.info(e.getMessage(), e);
							
							return ResponseEntity.status(500).build();
						}
		
//Parsing the json and creating a new one using gson library
						GsonUtils utils = new GsonUtils();
						DateTime dateTime = utils.processJson(data);

//Here the new json object is being created from the DateTime object. Than it is being 
//returned to client.						
						Gson gson = new GsonBuilder().create();
						String json = gson.toJson(dateTime);
						return ResponseEntity.ok().body(json);
					
					
				} else {
					log.info("key has not been found");
					return ResponseEntity.status(401).build();
					
				}
				
			} catch (NullPointerException e) {
//If there is no authentication header, return status 401 (unauthorized).
				log.info("key has not been provided");
				return ResponseEntity.status(401).build();
			}
			
		} catch (Exception e) {
			log.info(e.getMessage(), e);
			return ResponseEntity.badRequest().build();
		}
		
	}
	

}
