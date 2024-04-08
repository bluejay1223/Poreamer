package edu.ncsu.csc316.social.data;

import java.util.Comparator;

/**
 * Comparator for connections in platform
 * 
 * @author taeyoonkim
 *
 */
public class ConnectionComparatorInPlatform implements Comparator<Connection> {

	@Override
	public int compare(Connection o1, Connection o2) {
		
		
		return o1.getDate().compareTo(o2.getDate());

		
		
	}

}