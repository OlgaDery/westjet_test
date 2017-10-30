/**
 * 
 */
package rest;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonParser;
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
	
	public ResponseEntity <String> queryTime () 
	{
		//How to get API keys
		RestTemplate restTemplate = new RestTemplate();
		try {
			
			String data = restTemplate.getForObject("http://api.timezonedb.com/v2/get-time-zone?key=K1C5NG4CRU55&format=json&by=zone&zone=America/Edmonton", 
					String.class);

			//Configure JsonPath to use Jackson for mapping
			JsonElement jelement = new JsonParser().parse(data);
			JsonObject  jobject = jelement.getAsJsonObject();
			Map<String, String> toReturn = new HashMap<String, String>();

			log.info("size :{}", jobject.entrySet().size());
			jobject.entrySet().forEach(obj -> 
			{
				 String key = obj.getKey();
				 if (key.equals("formatted")) {
					 String time = obj.getValue().getAsJsonPrimitive().getAsString();
					 toReturn.put("current time", time);
					 log.info("time : {}", time);
					 
				 } else if (key.equals("abbreviation")) {
					 String zone = obj.getValue().getAsJsonPrimitive().getAsString();
					 log.info("zone: ", zone);
					 toReturn.put("zone", zone);
				 }
				 
			});
			
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(toReturn);
			return ResponseEntity.ok().body(json);
			
		} catch (Exception e) {
			log.info(e.getMessage(), e);
			return ResponseEntity.badRequest().build();
		}
		
	}
	

}
