package edu.ncsu.csc316.social.manager;

import java.io.FileNotFoundException;


import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.sorter.Sorter;
import edu.ncsu.csc316.social.data.Connection;
import edu.ncsu.csc316.social.data.ConnectionComparator;
import edu.ncsu.csc316.social.data.ConnectionComparatorInPlatform;
import edu.ncsu.csc316.social.data.KeyComparator;
import edu.ncsu.csc316.social.data.Person;
import edu.ncsu.csc316.social.dsa.Algorithm;
import edu.ncsu.csc316.social.dsa.DSAFactory;
import edu.ncsu.csc316.social.dsa.DataStructure;
import edu.ncsu.csc316.social.io.InputReader;

/**
 * Manages social media connections and people using various data structures.
 * It allows for the loading of people and connection data from files, and
 * provides functionality to organize and retrieve these connections based on person
 * or platform.
 * 
 * @author Taeyoon Kim
 */
public class SocialMediaManager {
	
	/** Maps a person's username to their Person object */
    public Map<String, Person> mapPerson;
    
    /** List of people */
    public List<Person> personList;
    
    /** List of person usernames */
    public List<String> personUserName;
    
    /** List of connections between people */
    public List<Connection> connections;
    
    /** Array of keys for sorting */
    public String[] keyArray;
    
    /** Array of platform keys for sorting */
    public String[] keyArray2;
	
	
	
    /**
     * Initializes a new SocialMediaManager with the specified people and connection
     * files using a default data structure type for internal storage.
     * 
     * @param peopleFile the file path to the list of people
     * @param connectionFile the file path to the list of connections
     * @throws FileNotFoundException if the provided files cannot be found
     */
    public SocialMediaManager(String peopleFile, String connectionFile) throws FileNotFoundException {
        this(peopleFile, connectionFile, DataStructure.UNORDEREDLINKEDMAP);
    }
    
    
    /**
     * Initializes a new SocialMediaManager with the specified people and connection
     * files, and allows for specifying the type of Map to use for internal storage.
     * 
     * @param peopleFile the file path to the list of people
     * @param connectionFile the file path to the list of connections
     * @param mapType the type of Map to use for internal storage
     * @throws FileNotFoundException if the provided files cannot be found
     */
    public SocialMediaManager(String peopleFile, String connectionFile, DataStructure mapType)
            throws FileNotFoundException {
        DSAFactory.setListType(DataStructure.ARRAYBASEDLIST);
        DSAFactory.setComparisonSorterType(Algorithm.SELECTION_SORT);
        DSAFactory.setNonComparisonSorterType(Algorithm.QUICKSORT);
        DSAFactory.setMapType(mapType);
        // Add additional initialization code, as needed
        
        mapPerson = DSAFactory.getMap(null);
    	
    	personList = DSAFactory.getIndexedList();
    	
    	personUserName = DSAFactory.getIndexedList();
    	
    	try {
			personList = InputReader.readPersonData(peopleFile);
		} catch (FileNotFoundException e) {
			System.out.println("error");
			System.exit(1);
		}
    	
    	try {
			connections = InputReader.readConnectionData(connectionFile);
		} catch (FileNotFoundException e) {
			System.out.println("error2");
			System.exit(1);
		}
        
        
    }
    
    /**
     * Retrieves a map of people, mapping their usernames to their respective Person objects.
     * 
     * @return a Map of people or null if no people are loaded
     */
    public Map<String, Person> getPeople() {
    	
    	
    	if (personList == null || personUserName == null) {
    		return null;
    	}
    	for (int i = 0; i < personList.size(); i++) {
    		if (personList.get(i) != null) {
    			personUserName.addLast(personList.get(i).getId());
    		}
    		
    	}
    	
    	
    	for (int i = 0; i < personList.size(); i++) {
    		if (personUserName.get(i) != null && personList.get(i) != null) {
    			mapPerson.put(personUserName.get(i), personList.get(i));
    		}
    		
    	}
    	

    	
    	return mapPerson;
    }

    /**
     * Retrieves a map of connections by person, organizing connections in which
     * each person is involved.
     * 
     * @return a Map where each key is a person's username and the value is a list of connections
     */
    public Map<String, List<Connection>> getConnectionsByPerson() {
    	
    	Map<String, List<Connection>> map = DSAFactory.getMap(null);
    	
    	if (connections.isEmpty()) {
    		return null;
    	}
                
        
    	Map<String, Person> map2 = getPeople();
    	
    	if (map2.isEmpty()) {
    		System.out.println("error");
    	}
        
    	keyArray = new String[personUserName.size()];
    	
    	for (int i = 0; i < keyArray.length; i++) {
    		keyArray[i] = personUserName.removeFirst();
    	}

    	// Sort the keyArray
    	sortKey(keyArray);
        
        for (int j = 0; j < keyArray.length; j++) {
        	
        	
        	if (keyArray[j] != null) {
        		List<Connection> temp = DSAFactory.getIndexedList();
            	
            	for (int i = 0; i < connections.size(); i++) {
            		if (connections.get(i).getPeople()[0].equals(keyArray[j]) ||
            				connections.get(i).getPeople()[1].equals(keyArray[j])) {
            			temp.addLast(connections.get(i));
            		}
            	}
            	
            	Connection[] temp2 = new Connection[temp.size()];
            	
            	for (int i = 0; i < temp.size(); i++) {
            		temp2[i] = temp.get(i);
            	}
            	
            	sortConnections(temp2);
            	List<Connection> temp3 = DSAFactory.getIndexedList();
            	for (int i = 0; i < temp2.length; i++) {
            		temp3.addLast(temp2[i]);
            	}
            	
            	
            	map.put(keyArray[j], temp3);
        	}
        	
        	
        	
        	
        }
        
        
        
        return map;
        
        
        
        
    }
    
    
    
    /**
     * Retrieves a map of connections by platform, organizing connections based on
     * the platform they were made.
     * 
     * @return a Map where each key is a platform name and the value is a list of connections on that platform
     */
public Map<String, List<Connection>> getConnectionsByPlatform() {
	    
	    Map<String, List<Connection>> map = DSAFactory.getMap(null);
	    
	    int count = 0;
	    
	    int tp = 0;
	    
	    String[] keyArray = new String[connections.size() * 2];
	    
	    
	    for (int i = 0; i < connections.size(); i++) {
	    	for (int k = 0; k < keyArray.length; k++) {
	    		
	    		if (keyArray[k] == null) {
        			
        			break;
        		}
	    		if (keyArray[k].equals(connections.get(i).getPlatform())) {
	    			count = 1;
	    		}
	
	    	}
	    	
	    	if (count != 1) {
	    		keyArray[tp++] = connections.get(i).getPlatform(); 
	    		count = 0;
	    		
	    	}
	    	
	    }
	    
	    
	    
	    sortKey(keyArray);
	    
	    
	    
	    for (int j = 0; j < keyArray.length; j++) {
	    	
	    	if (keyArray[j] == null) {
	    		break;
	    	}
        	List<Connection> temp = DSAFactory.getIndexedList();
        	
        	for (int i = 0; i < connections.size(); i++) {
        		if (connections.get(i).getPlatform().equals(keyArray[j])) {
        			temp.addLast(connections.get(i));
        		}
        	}
        	
        	Connection[] temp2 = new Connection[temp.size()];
        	
        	for (int i = 0; i < temp.size(); i++) {
        		temp2[i] = temp.get(i);
        	}
        	
        	sortConnectionsInPlatform(temp2);
        	
        	List<Connection> temp3 = DSAFactory.getIndexedList();
        	for (int i = 0; i < temp2.length; i++) {
        		temp3.addLast(temp2[i]);
        	}
        	
        	
        	map.put(keyArray[j], temp3);
        	
        }
	    
        
        return map;
	    
	    
    
    
    
}

    /**
     * Sorts an array of keys (usernames or platform names) alphabetically.
     * 
     * @param keyList the array of keys to sort
     */
    private void sortKey(String[] keyList) {
        Sorter<String> sorter = DSAFactory.getComparisonSorter(new KeyComparator());
        sorter.sort(keyList);
	}
    
    /**
     * Sorts an array of Connection objects based on a predefined comparison criteria.
     * 
     * @param list the array of Connection objects to sort
     */
    private void sortConnections(Connection[] list) {
    	Sorter<Connection> sorter = DSAFactory.getComparisonSorter(new ConnectionComparator());
    	
    	
    	sorter.sort(list);
    }
    
    /**
     * Sorts an array of Connection objects based on a platform-specific comparison criteria.
     * 
     * @param list the array of Connection objects to sort
     */
    private void sortConnectionsInPlatform(Connection[] list) {
    	Sorter<Connection> sorter = DSAFactory.getComparisonSorter(new ConnectionComparatorInPlatform());
    	
    	
    	sorter.sort(list);
    }
    
    
	
}
