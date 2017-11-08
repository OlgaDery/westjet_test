/**
 * 
 */
package rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author Olga
 * Creating the customized RestTemplate to set reasonable timeouts
 */
public class RestTemplateModified extends RestTemplate{
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public RestTemplateModified() {
		
		log.info("enter RestTemplateModified");
        if (getRequestFactory() instanceof SimpleClientHttpRequestFactory) {
            log.info("HttpUrlConnection is used");
            ((SimpleClientHttpRequestFactory) getRequestFactory()).setConnectTimeout(1000);
            ((SimpleClientHttpRequestFactory) getRequestFactory()).setReadTimeout(1000);
        } else if (getRequestFactory() instanceof HttpComponentsClientHttpRequestFactory) {
            log.info("HttpClient is used");
            ((HttpComponentsClientHttpRequestFactory) getRequestFactory()).setReadTimeout(1000);
            ((HttpComponentsClientHttpRequestFactory) getRequestFactory()).setConnectTimeout(1000);
        }
        log.info("exit RestTemplateModified");
    }


	

}
