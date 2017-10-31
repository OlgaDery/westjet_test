/**
 * 
 */
package rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonParser;

import beans.Singleton;
import model.DateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author Olga
 *
 */
@RestController
public class QueryTime {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping(value ="/time/now", produces = "application/json")
	
	public ResponseEntity <String> queryTime (HttpServletRequest request) 
	{
		try {

			//Getting the API key
			try {
				String authKey = request.getHeader("authorization");
				if (Arrays.asList(Singleton.getKeys()).contains(authKey)) {
					log.info("key : {}", authKey);
					
					try {
						RestTemplate restTemplate = new RestTemplateModified();
						String data = restTemplate.getForObject("http://api.timezonedb.com/v2/get-time-zone?key=K1C5NG4CRU55&format=json&by=zone&zone=America/Edmonton", 
								String.class);
		
						//Parsing the json and creating a new one using gson library
						
						JsonElement jelement = new JsonParser().parse(data);
						JsonObject  jobject = jelement.getAsJsonObject();
			//			Map<String, String> toReturn = new HashMap<String, String>();
		
						log.info("size :{}", jobject.entrySet().size());
						
						DateTime dateTime = new DateTime();
						jobject.entrySet().forEach(obj -> 
						{
							 String key = obj.getKey();
							 if (key.equals("formatted")) {
								 String time = obj.getValue().getAsJsonPrimitive().getAsString();
								 try {
									 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy HH:mm:ss a");
									 LocalDateTime localDateTime = LocalDateTime.parse(time, formatter);

									dateTime.setTime(localDateTime);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									log.info(e.getMessage(), e);
								}  
								 
								 
							 } else if (key.equals("abbreviation")) {
								 String zone = obj.getValue().getAsJsonPrimitive().getAsString();
								 log.info("zone: ", zone);
								 dateTime.setZone(zone);
							
							 }
							 
						});
						
						Gson gson = new GsonBuilder().create();
						String json = gson.toJson(dateTime);
						return ResponseEntity.ok().body(json);
						
					} catch (Exception e) {
						return ResponseEntity.status(500).build();
					}
					
					
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
