package edu.ncsu.csc316.social.manager;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.social.data.Connection;
import edu.ncsu.csc316.social.data.Person;

/**
 * Test class for socialmediamanager
 * 
 * @author taeyoonkim
 *
 */
public class SocialMediaManagerTest {

	/**
	 * Test
	 * @throws FileNotFoundException if file is invalid
	 */
	@Test
	public void testLoadPeople() {
	    try {
	        SocialMediaManager manager = new SocialMediaManager("input/person-1.txt", "input/connection-1.txt");
	        Map<String, Person> people = manager.getPeople();
	        assertNotNull("The map of people should not be null", people);
	        assertFalse("The map of people should not be empty", people.isEmpty());
	        // Assuming you know the expected size or some expected values
	        assertEquals("The map should contain a specific number of people", 8, people.size());
	    } catch (FileNotFoundException e) {
	        fail("FileNotFoundException should not be thrown");
	    }
	}
	
	/**
	 * Test
	 * @throws FileNotFoundException if file is invalid
	 */
	@Test
	public void testLoadConnections() {
	    try {
	        SocialMediaManager manager = new SocialMediaManager("input/person-1.txt", "input/connection-2.txt");
	        Map<String, List<Connection>> connectionsByPerson = manager.getConnectionsByPerson();
	        //System.out.println(connectionsByPerson);
	        assertNotNull("The map of connections by person should not be null", connectionsByPerson);
	        assertFalse("The map of connections by person should not be empty", connectionsByPerson.isEmpty());
	        // Assuming you know the expected size or some expected values

	    } catch (FileNotFoundException e) {
	        fail("FileNotFoundException should not be thrown");
	    }
	}
	
	/**
	 * Test
	 * @throws FileNotFoundException if file is invalid
	 */
	@Test
	public void testLoadConnections2() {
	    try {
	        SocialMediaManager manager = new SocialMediaManager("input/person-1.txt", "input/connection-3.txt");
	        Map<String, List<Connection>> connectionsByPerson = manager.getConnectionsByPerson();
	        
	        //System.out.println(connectionsByPerson);
	        assertNotNull("The map of connections by person should not be null", connectionsByPerson);
	        assertFalse("The map of connections by person should not be empty", connectionsByPerson.isEmpty());
	        // Assuming you know the expected size or some expected values

	    } catch (FileNotFoundException e) {
	        fail("FileNotFoundException should not be thrown");
	    }
	}
	
	/**
	 * Test
	 * @throws FileNotFoundException if file is invalid
	 */
	@Test
	public void testLoadConnections3() {
	    try {
	        SocialMediaManager manager = new SocialMediaManager("input/person-1.txt", "input/connection-4.txt");
	        Map<String, List<Connection>> connectionsByPerson = manager.getConnectionsByPerson();
	        assertNotNull("The map of connections by person should not be null", connectionsByPerson);
	        assertFalse("The map of connections by person should not be empty", connectionsByPerson.isEmpty());
	        // Assuming you know the expected size or some expected values

	    } catch (FileNotFoundException e) {
	        fail("FileNotFoundException should not be thrown");
	    }
	}
	
	/**
	 * Test
	 * @throws FileNotFoundException if file is invalid
	 */
	@Test
	public void testLoadPlatforms() {
	    try {
	        SocialMediaManager manager = new SocialMediaManager("input/person-1.txt", "input/connection-2.txt");
	        Map<String, List<Connection>> connectionsByPlatform = manager.getConnectionsByPlatform();
	        assertNotNull("The map of connections by person should not be null", connectionsByPlatform);
	        assertFalse("The map of connections by person should not be empty", connectionsByPlatform.isEmpty());
	        // Assuming you know the expected size or some expected values

	    } catch (FileNotFoundException e) {
	        fail("FileNotFoundException should not be thrown");
	    }
	}
	

	
	


}
