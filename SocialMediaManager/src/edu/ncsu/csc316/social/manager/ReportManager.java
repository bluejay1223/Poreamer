package edu.ncsu.csc316.social.manager;

import java.io.FileNotFoundException;


import java.util.Date;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.social.data.Connection;
import edu.ncsu.csc316.social.data.Person;
import edu.ncsu.csc316.social.dsa.Algorithm;
import edu.ncsu.csc316.social.dsa.DSAFactory;
import edu.ncsu.csc316.social.dsa.DataStructure;

/**
 * Manages and generates reports for social media connections. This manager is capable of generating
 * detailed reports about connections between individuals on social media platforms, both by person and by platform.
 * Reports generated include detailed listings of connections for each person or platform, including
 * connection dates and the other parties involved in the connections.
 * 
 * @author Taeyoon Kim
 */
public class ReportManager {

    private SocialMediaManager manager;

    // Add any additional fields, as needed

    /**
     * Constructs a new ReportManager with specified data files using a default data structure type.
     * Initializes underlying data structures and loads people and connection data.
     * 
     * @param peopleFile the file path to the list of people
     * @param connectionFile the file path to the list of connections
     * @throws FileNotFoundException if any of the provided files cannot be found
     */
    public ReportManager(String peopleFile, String connectionFile) throws FileNotFoundException {
    	this(peopleFile, connectionFile, DataStructure.UNORDEREDLINKEDMAP);
    }

    /**
     * Constructs a new ReportManager with specified data files and a specific data structure type
     * for internal storage. Initializes underlying data structures and loads people and connection data.
     * 
     * @param peopleFile the file path to the list of people
     * @param connectionFile the file path to the list of connections
     * @param mapType the type of data structure to use for internal storage
     * @throws FileNotFoundException if any of the provided files cannot be found
     */
    public ReportManager(String peopleFile, String connectionFile, DataStructure mapType) throws FileNotFoundException {
        manager = new SocialMediaManager(peopleFile, connectionFile, mapType);
        DSAFactory.setListType(DataStructure.ARRAYBASEDLIST);
        DSAFactory.setComparisonSorterType(Algorithm.SELECTION_SORT);
        DSAFactory.setNonComparisonSorterType(Algorithm.QUICKSORT);
        DSAFactory.setMapType(mapType);
        // add any addional initialization code, as needed
    }

    /**
     * Generates a report of connections grouped by person. For each person in the social network,
     * this report lists all connections they have, including the platform of each connection and the date it was established.
     * 
     * @return A formatted string containing the report or error messages if data is incomplete.
     */
    public String getConnectionsByPerson() {
        Map<String, Person> people = manager.getPeople();
        Map<String, List<Connection>> connectionsByPerson = manager.getConnectionsByPerson();

        StringBuilder report = new StringBuilder();
        
        if (people == null || people.isEmpty()) {
        	report.append("No people information was provided.\n");
        	return report.toString();
        }
        
        if (connectionsByPerson == null || connectionsByPerson.isEmpty()) {
        	report.append("No connections exist in the social media network.\n");
        	return report.toString();
        }

        for (int i = 0; i < manager.keyArray.length; i = i + 2) {
        	
        	//System.out.println(connectionsByPerson.toString());
            String personId = manager.keyArray[i];
            Person person = people.get(personId);
            
            // Format the person's name
            String personName = person.getFirst() + " " + person.getLast();
            
            // Start the report entry for this person
            report.append("Connections for ").append(personName).append(" (").append(personId).append(") {\n");

            List<Connection> connections = connectionsByPerson.get(personId);
            if (connections == null || connections.isEmpty()) {
                report.append("   No connections exist\n");
            } else {
                // Iterate over the connections and append to the report
                for (Connection connection : connections) {
                    String otherPersonId = personId.equals(connection.getPeople()[0]) ? connection.getPeople()[1] : connection.getPeople()[0];
                    Person otherPerson = people.get(otherPersonId);
                    String otherPersonName = otherPerson.getFirst() + " " + otherPerson.getLast();
                    String platform = connection.getPlatform();
                    Date date = connection.getDate(); // Formatting the date as required

                    report.append("   ").append(otherPersonName).append(" (").append(otherPersonId)
                          .append(") on ").append(platform).append(" since ").append(date.toString()).append("\n");
                }
            }
            report.append("}\n");
            
            
            connectionsByPerson.remove(personId);
        }

        return report.toString();
    }

    /**
     * Generates a report of connections grouped by platform. For each platform, this report lists
     * all connections made, including the individuals involved in each connection and the date it was established.
     * 
     * @return A formatted string containing the report or error messages if data is incomplete.
     */
public String getConnectionsByPlatform() {
    	
    	Map<String, Person> people = manager.getPeople();
        Map<String, List<Connection>> connectionsByPlatform = manager.getConnectionsByPlatform();
        StringBuilder report = new StringBuilder();
        
        // Assuming you have a method to iterate through the map entries
        Iterable<Map.Entry<String, List<Connection>>> entries = connectionsByPlatform.entrySet();
        for (Map.Entry<String, List<Connection>> entry : entries) {
            String platform = entry.getKey();
            List<Connection> connections = entry.getValue();
            
            report.append("Connections on ").append(platform).append(" {\n");
            for (Connection connection : connections) {
                // Retrieve person details from the connection
                String person1Id = connection.getPeople()[0];
                String person2Id = connection.getPeople()[1];
                
                // Assuming manager provides a method to get person by ID
                Person person1 = people.get(person1Id);
                Person person2 = people.get(person2Id);
                
                int help = comparePerson(person1, person2);
                
                if (help < 0) {
                	// Format the connection details
                    report.append("   ").append(connection.getDate().toString())
                    	  .append(": ").append(person1.getFirst()).append(" ").append(person1.getLast())
		                  .append(" (").append(person1Id).append(") <--> ")
		                  .append(person2.getFirst()).append(" ").append(person2.getLast())
		                  .append(" (").append(person2Id).append(")\n");
                }
                else {
                	report.append("   ").append(connection.getDate().toString())
	                      .append(": ").append(person2.getFirst()).append(" ").append(person2.getLast())
	                      .append(" (").append(person2Id).append(") <--> ")
	                      .append(person1.getFirst()).append(" ").append(person1.getLast())
	                      .append(" (").append(person1Id).append(")\n");
                }
                
                
            }
            
            
            report.append("}\n");
            
            
        }

        return report.toString();
    }


    /**
     * Compares two Person objects for sorting. Comparison is based first on last name, then on first name,
     * and finally on ID if necessary to resolve ties.
     * 
     * @param p1 the first person to compare
     * @param p2 the second person to compare
     * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to,
     *         or greater than the second.
     */
    private int comparePerson(Person p1, Person p2) {
    	if (p1.getLast().compareTo(p2.getLast()) == 0) {
    		if (p1.getFirst().compareTo(p2.getFirst()) == 0) {
    			return p1.getId().compareTo(p2.getId());
    		}
    		else {
    			return p1.getFirst().compareTo(p2.getFirst());
    		}
    	}
    	else {
    		return p1.getLast().compareTo(p2.getLast());
    	}
    }
}