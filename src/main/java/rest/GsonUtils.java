/**
 * 
 */
package rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.JsonParser;
import model.DateTime;
import model.DateTimeIF;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author Olga
 *The util class helping to serialize/deserialize JSON.
 */

public class GsonUtils {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public GsonUtils () {
		
	}
	
	public DateTimeIF processJson (String data) {
		//Parsing the json and creating a new one using gson library
		
		JsonElement jelement = new JsonParser().parse(data);
		JsonObject  jobject = jelement.getAsJsonObject();

		log.info("size :{}", jobject.entrySet().size());
		
		DateTime dateTime = new DateTime();
		jobject.entrySet().forEach(obj -> 
		{
			 String key = obj.getKey();
			 if (key.equals("formatted")) {
				 String time = obj.getValue().getAsJsonPrimitive().getAsString();
				 try {
					 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					 LocalDateTime localDateTime = LocalDateTime.parse(time, formatter);

					dateTime.setTime(localDateTime);
				} catch (Exception e) {
					
					log.info(e.getMessage(), e);
				}  
				 
				 
			 } else if (key.equals("abbreviation")) {
				 String zone = obj.getValue().getAsJsonPrimitive().getAsString();
				 dateTime.setZone(zone);
			
			 }
			 
		});
		return dateTime;
		
	}
	

}
