package rest_westjet;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import beans.Singleton;
import model.AddValues;
import model.AddValuesIF;
import model.DateTime;
import model.DateTimeIF;
import rest.GsonUtils;

public class TestClasses {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test
    public void testEqualsAddValues() {
	//Assert that equals method works properly
    AddValuesIF av = new AddValues(3,4);
    AddValuesIF av1 = new AddValues(3,5);
    assertEquals(false, av.equals(av1));
    }
	
	@Test
    public void testEqualsDateTime() {
	//Assert that equals method works properly
    DateTimeIF dt1 = new DateTime();
    DateTimeIF dt2 = new DateTime();
    assertEquals(true, dt1.equals(dt2));
    }
	
	@Test
    public void assertNotNullDateTime() {
   //Asserting that the fields of the class are not null
		 
	DateTimeIF dt1 = new DateTime("MST", LocalDateTime.now());
	assertNotNull(dt1.getTime());	
	assertNotNull(dt1.getZone());	
    }
	
	@Test
    public void assertJSONParsing() {
	//Assert how JSON parser works
		GsonUtils utils = new GsonUtils();
		DateTimeIF dt = utils.processJson ("{\"status\":\"OK\",\"message\":\"no\",\"abbreviation\":\"MDT\",\"formatted\":\"2017-10-31 10:46:28\"}");
		assertEquals(dt.getZone(), "MDT");
    }
	
	@Test
    public void assertSinglton() {
   //Asserting that the dtaset with the authentication keys is available
	Singleton singlton = new Singleton();
	assertNotNull(singlton.getKeys());
    }
	
	
	
}
